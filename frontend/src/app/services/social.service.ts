import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class SocialService {

  private readonly apiServerUrl = environment['api-base-url'];
  constructor(private http: HttpClient) { }

  public getProjects(): Observable<any> {
    return this.http.get<any>(`${this.apiServerUrl}api/v1/projects/approved`);
  }

  // public login(signinRequest: SigninRequest): Observable<JwtResponse> {
  //   return this.httpClient
  //     .post<JwtResponse>(`${this.apiServerUrl}api/v1/auth/login`, signinRequest)
  //     .pipe(
  //       map((response: JwtResponse) => {
  //         this.localStorageService.store("tkn", response.authToken);
  //         this.localStorageService.store("user", response.user);
  //         this.loggedIn.emit(true);
  //         this.user.emit(response.user);
  //         return response;
  //       })
  //     );
  // }
}
