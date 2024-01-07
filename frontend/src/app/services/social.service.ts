import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { Socials } from '../dtos/socials';

@Injectable({
  providedIn: 'root'
})
export class SocialService {

  private readonly apiServerUrl = environment['api-base-url'];
  constructor(private http: HttpClient) { }

  public getAllSocials(): Observable<Array<Socials>> {
    return this.http.get<Array<Socials>>(`${this.apiServerUrl}api/v1/projects/socials`);
  }

  isSocialConnected(name: string) {
    return true;
  }
}
