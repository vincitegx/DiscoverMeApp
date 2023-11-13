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
  private apiServerUrl = environment['api-base-url'];
  private jwtToken: String = '';
  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() authToken: EventEmitter<String> = new EventEmitter();
  private _isLoggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private httpClient: HttpClient) {}

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

  getJwtToken(){
    return this.jwtToken;
  }
}
