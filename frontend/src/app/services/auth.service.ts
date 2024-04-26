import { EventEmitter, Injectable, Output } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpEvent } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { SigninRequest } from '../components/signin/signinrequest';
import { JwtResponse } from '../components/signin/jwtresponse';
import { BehaviorSubject, Observable, catchError, map, of, tap } from 'rxjs';
import { SignupRequest } from '../components/signup/signup-request';
import { LocalStorageService } from 'ngx-webstorage';
import { UserDto } from '../dtos/userdto';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly apiServerUrl: string;
  @Output() loggedIn: EventEmitter<boolean>;
  @Output() user: EventEmitter<UserDto>;
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  private userSubject = new BehaviorSubject<UserDto | null>(null);
  token: string = "";

  constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService) {
    this.apiServerUrl = environment['api-base-url'];
    this.loggedIn = new EventEmitter();
    this.user = new EventEmitter();
  }

  refreshToken(): Observable<JwtResponse> {
    let user = this.getUser();
    return this.httpClient
      .post<JwtResponse>(`${this.apiServerUrl}api/v1/auth/refresh/token`, user)
      .pipe(tap(response => {
        this.localStorageService.store('tkn', response.authToken);
      }))
  }

  public login(signinRequest: SigninRequest): Observable<JwtResponse> {
    return this.httpClient
      .post<JwtResponse>(`${this.apiServerUrl}api/v1/auth/login`, signinRequest)
      .pipe(
        tap((response: JwtResponse) => {
          this.localStorageService.store("tkn", response.authToken);
          this.localStorageService.store("user", response.user);
          this.userSubject.next(response.user);
          this.isAuthenticatedSubject.next(true);
          this.loggedIn.emit(true);
          this.user.emit(response.user);
        })
      );
  }

  getUserObservable(): Observable<UserDto | null> {
    return this.userSubject.asObservable();
  }

  get(url: string): any {
    return this.httpClient.get(`${this.apiServerUrl}${url}`);
  }

  getToken(code: string): Observable<JwtResponse> {
    this.isAuthenticatedSubject.next(true);
    return this.httpClient.post<JwtResponse>(`${this.apiServerUrl}auth/callback?code=${code}`, {observe: "response"})
      .pipe(map((response: JwtResponse) => {
        this.localStorageService.store("tkn", response.authToken);
          this.localStorageService.store("user", response.user);
          // this.loggedIn.emit(true);
          // this.user.emit(response.user);
          this.userSubject.next(response.user);
        this.isAuthenticatedSubject.next(true);
          return response;
      }));
  }

  public signup(signupRequest: SignupRequest): Observable<any> {
    return this.httpClient
      .post<any>(`${this.apiServerUrl}api/v1/auth/register/user`, signupRequest)
      .pipe(
        map((response) => {
          return response;
        })
      );
  }

  verifyAccount(token: any): Observable<any> {
    return this.httpClient
      .get<any>(`${this.apiServerUrl}api/v1/auth/verify?token=${token}`)
      .pipe(
        map((response) => {
          return response;
        })
      );
  }

  getJwtToken():string {
    return this.localStorageService.retrieve("tkn");
  }

  getUser(): UserDto {
    return this.localStorageService.retrieve('user');
  }

  getFBUser(): string {
    return this.localStorageService.retrieve('fb-user');
  }
  getIGUser(): string {
    return this.localStorageService.retrieve('ig-user');
  }

  updateProfile(userName: string):Observable<UserDto>{
    return this.httpClient
      .put<UserDto>(`${this.apiServerUrl}api/v1/users/profile?userName=${userName}`, {})
      .pipe(
        map((response: UserDto) => {
          if (response) {
            this.localStorageService.store("user", response);
            this.userSubject.next(response);
          }
          return response;
      })
      );
  }

  isLoggedIn$(): Observable<boolean> {
    this.isAuthenticatedSubject.next(this.getJwtToken() != null && this.getUser() != null);
    return this.isAuthenticatedSubject.asObservable();
  }

  logout$(): Observable<HttpEvent<any>> {
    let user = this.getUser();
    return this.httpClient.post<any>(`${this.apiServerUrl}api/v1/auth/logout`, user).pipe(map(res => {
      if (res) {
        this.localStorageService.clear("tkn");
        this.localStorageService.clear("user");
        this.userSubject.next(null);
          this.isAuthenticatedSubject.next(false);
        this.loggedIn.next(false);
        this.user.next({});
      }
      return res;
    }),catchError((error:HttpErrorResponse)=>{
      tap(console.log)
      return new Observable<HttpEvent<any>>();
    })
    );
  }
}
