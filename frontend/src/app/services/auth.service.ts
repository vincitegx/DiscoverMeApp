import { EventEmitter, Injectable, Output } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpEvent, HttpHeaders, HttpResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { SigninRequest } from '../components/signin/signinrequest';
import { JwtResponse } from '../components/signin/jwtresponse';
import { BehaviorSubject, Observable, catchError, map, of, tap } from 'rxjs';
import { SignupRequest } from '../components/signup/signup-request';
import { LocalStorageService } from 'ngx-webstorage';
import { UserDto } from '../dtos/userdto';
import { FBUserResponse } from '../components/profile/fb-user-response';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly apiServerUrl: string;
  @Output() loggedIn: EventEmitter<boolean>;
  @Output() user: EventEmitter<UserDto>;
  private refreshTokenSubject: BehaviorSubject<JwtResponse>;
  private isTokenRefreshingSubject: BehaviorSubject<boolean>;
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  token: string = "";

  constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService) {
    this.apiServerUrl = environment['api-base-url'];
    this.loggedIn = new EventEmitter();
    this.user = new EventEmitter();
    this.refreshTokenSubject = new BehaviorSubject<JwtResponse>({ "authToken": '', "user": {} });
    this.isTokenRefreshingSubject = new BehaviorSubject<boolean>(false);
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
        map((response: JwtResponse) => {
          this.localStorageService.store("tkn", response.authToken);
          this.localStorageService.store("user", response.user);
          this.loggedIn.emit(true);
          this.user.emit(response.user);
          return response;
        })
      );
  }

  get(url: string): any {
    return this.httpClient.get(`${this.apiServerUrl}${url}`);
  }

  getToken(code: string): Observable<JwtResponse> {
    return this.httpClient.post<JwtResponse>(`${this.apiServerUrl}auth/callback?code=${code}`, {observe: "response"})
      .pipe(map((response: JwtResponse) => {
        this.localStorageService.store("tkn", response.authToken);
          this.localStorageService.store("user", response.user);
          this.loggedIn.emit(true);
          this.user.emit(response.user);
          return response;
      }));
  }

  getFBToken(code: string): Observable<FBUserResponse> {
    return this.httpClient.post<FBUserResponse>(`${this.apiServerUrl}auth/fb/callback?code=${code}`, {observe: "response"})
      .pipe(map((response: FBUserResponse) => {
        this.localStorageService.store("fb-user", response.name);
          return response;
      }));
  }

  disconnectFacebook(): Observable<boolean> {
    this.localStorageService.clear("fb-user");
    return of(true);
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

  isLoggedIn$(): Observable<boolean> {
    this.isAuthenticatedSubject.next(this.getJwtToken() != null);
    return this.isAuthenticatedSubject.asObservable();
  }

  logout$(): Observable<HttpEvent<any>> {
    let user = this.getUser();
    return this.httpClient.post<any>(`${this.apiServerUrl}api/v1/auth/logout`, user).pipe(map(res => {
      if (res) {
        this.localStorageService.clear("tkn");
        this.localStorageService.clear("user");
        this.loggedIn.next(false);
        this.user.next({});
      }
      return res;
    }),catchError((error:HttpErrorResponse)=>{
      return new Observable<HttpEvent<any>>();
    })
    );
  }
}
