import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, of } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Socials } from '../dtos/socials';
import { FBUserResponse } from '../components/profile/fb-user-response';
import { LocalStorageService } from 'ngx-webstorage';
import { UserSocials } from '../dtos/usersocial';
import { IGUserResponse } from '../components/profile/ig-user-response';
import { AuthService } from './auth.service';
import { UserDto } from '../dtos/userdto';

@Injectable({
  providedIn: 'root'
})
export class SocialService {

  private readonly apiServerUrl = environment['api-base-url'];
  constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService,
    private authService: AuthService) { }

  public getAllSocials(): Observable<Array<Socials>> {
    return this.httpClient.get<Array<Socials>>(`${this.apiServerUrl}api/v1/socials`);
  }

  public getUserSocials(): Observable<Array<Socials>> {
    return this.httpClient.get<Array<UserSocials>>(`${this.apiServerUrl}api/v1/users/socials`);
  }

  isSocialConnected(name: string): boolean {
    const user = this.authService.getUser();
    if (user && user.socials) {
      return user.socials.some(social => social.social === name);
    }
    return false;
  }

  getFBToken(code: string): Observable<UserDto> {
    return this.httpClient.post<UserDto>(`${this.apiServerUrl}auth/fb/callback?code=${code}`, {observe: "response"})
      .pipe(map((response: UserDto) => {
        if(response){
          this.localStorageService.store('user', response);
        }
          return response;
      }));
  }

  getIGToken(code: string): Observable<UserDto> {
    return this.httpClient.post<UserDto>(`${this.apiServerUrl}auth/ig/callback?code=${code}`, {observe: "response"})
      .pipe(map((response: UserDto) => {
        if(response){
          this.localStorageService.store('user', response);
        }
          return response;
      }));
  }

  disconnectFacebook(): Observable<UserDto> {
    return this.httpClient.put<UserDto>(`${this.apiServerUrl}auth/fb/disconnect`, {observe: "response"})
      .pipe(map((response: UserDto) => {
        if(response){
          this.localStorageService.store('user', response);
        }
          return response;
      }));
  }

  disconnectInstagram(): Observable<UserDto> {
    return this.httpClient.put<UserDto>(`${this.apiServerUrl}auth/ig/disconnect`, {observe: "response"})
      .pipe(map((response: UserDto) => {
        if(response){
          this.localStorageService.store('user', response);
        }
          return response;
      }));
  }


  getFBUser(): string {
    return this.localStorageService.retrieve('fb-user');
  }
  getIGUser(): string {
    return this.localStorageService.retrieve('ig-user');
  }

}
