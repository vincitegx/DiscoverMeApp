import { EventEmitter, Injectable, Output } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { SigninRequest } from '../components/signin/signinrequest';
import { JwtResponse } from '../components/signin/jwtresponse';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { SignupRequest } from '../components/signup/signup-request';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly apiServerUrl = environment['api-base-url'];
  private jwtToken: string = '';
  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() authToken: EventEmitter<String> = new EventEmitter();
  private _isLoggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  refreshTokenSubject$ = new BehaviorSubject<any>(null);
  public isTokenRefreshingSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private httpClient: HttpClient) {}

  public isTokenRefreshing$(): Observable<boolean> {
    return this.isTokenRefreshingSubject.asObservable();
  }

  public setTokenRefreshing(isRefreshing: boolean): void {
    this.isTokenRefreshingSubject.next(isRefreshing);
  }

  refreshToken() {
    this.setTokenRefreshing(true);
    return this.httpClient.post<JwtResponse>('http://localhost:8080/api/auth/refresh/token',
      null)
      this.setTokenRefreshing(false);
      // .pipe(tap(response => {
      //   this.localStorage.clear('authenticationToken');
      //   this.localStorage.clear('expiresAt');

      //   this.localStorage.store('authenticationToken',
      //     response.authenticationToken);
      //   this.localStorage.store('expiresAt', response.expiresAt);
      // })
      // );
  }

  public login(signinRequest: SigninRequest): Observable<JwtResponse> {
    return this.httpClient
      .post<JwtResponse>(`${this.apiServerUrl}api/v1/auth/login`, signinRequest)
      .pipe(
        map((response) => {
          this._isLoggedIn.next(true);
          this.loggedIn.emit(true);
          this.jwtToken = response['authToken'];
          this.authToken.emit(response['authToken']);
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

  isLoggedIn():  Observable<boolean> {
    return this._isLoggedIn.asObservable();
  }

  verifyAccount(token:any):Observable<any>{
    return this.httpClient
      .get<any>(`${this.apiServerUrl}api/v1/users/verify?token=${token}`)
      .pipe(
        map((response) => {
          return response;
        })
      );
  }

  getJwtToken():string{
    return this.jwtToken;
  }

  logout(): void {
    // this.refreshTokenRequest.refreshToken = this.getRefreshToken();
    // this.refreshTokenRequest.user = this.getUser();
    let logoutResponse = this.httpClient.post<any>(`${this.apiServerUrl}/api/auth/logout`, null);
    this.loggedIn.next(false);
    // this.user.next(null);
  }
}
