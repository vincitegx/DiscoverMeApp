import { EventEmitter, Injectable, Output } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { LoginRequest } from '../dtos/loginrequest';
import { JwtResponse } from '../dtos/jwtresponse';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiServerUrl = environment['api-base-url'];
  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() authToken: EventEmitter<String> = new EventEmitter();

  constructor(private httpClient: HttpClient) { }

  public login(loginRequest: LoginRequest): Observable<JwtResponse>{
      return this.httpClient.post<JwtResponse>(`${this.apiServerUrl}api/v1/auth/login`, loginRequest)
      .pipe(
        map(
          response => {this.loggedIn.emit(true);
          this.authToken.emit(response['authToken']);
          return response;
      }));
  }
  isLoggedIn(): boolean {
    return true;
  }
}
