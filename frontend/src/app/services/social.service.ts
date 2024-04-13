import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, of } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Socials } from '../dtos/socials';
import { FBUserResponse } from '../components/profile/fb-user-response';
import { LocalStorageService } from 'ngx-webstorage';
import { UserSocials } from '../dtos/usersocial';

@Injectable({
  providedIn: 'root'
})
export class SocialService {

  private readonly apiServerUrl = environment['api-base-url'];
  constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService) { }

  public getAllSocials(): Observable<Array<Socials>> {
    return this.httpClient.get<Array<Socials>>(`${this.apiServerUrl}api/v1/socials`);
  }

  public getUserSocials(): Observable<Array<Socials>> {
    return this.httpClient.get<Array<UserSocials>>(`${this.apiServerUrl}api/v1/users/socials`);
  }

  isSocialConnected(name: string) {
    return true;
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
}
