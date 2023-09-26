import { EventEmitter, Injectable, Output } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment.development';
import { LoginRequest } from '../dtos/loginrequest';
import { JwtResponse } from '../dtos/jwtresponse';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiServerUrl = environment['api-base-url'];
  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();

  constructor(private httpClient: HttpClient) { }

  public login(loginRequest: LoginRequest): Observable<JwtResponse>{
      return this.httpClient.post<JwtResponse>(`${this.apiServerUrl}api/v1/auth/login`, loginRequest);
  }
}
