import { EventEmitter, Injectable, Output } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { SigninRequest } from '../components/signin/signinrequest';
import { JwtResponse } from '../components/signin/jwtresponse';
import { BehaviorSubject, Observable, map, of, tap } from 'rxjs';
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
  private refreshTokenSubject: BehaviorSubject<JwtResponse>;
  private isTokenRefreshingSubject: BehaviorSubject<boolean>;

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

  isLoggedIn$(): Observable<boolean> {
    return of(this.getJwtToken() != null);
  }

  logout$(): Observable<any> {
    let user = this.getUser();
    return this.httpClient.post<any>(`${this.apiServerUrl}api/v1/auth/logout`, user).pipe(map(res => {
      if (res) {
        this.localStorageService.clear("tkn");
        this.localStorageService.clear("user");
        this.loggedIn.next(false);
        this.user.next({});
      }
    })
    );
  }
}
