import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { Observable } from 'rxjs';
import { Socials } from '../dtos/socials';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private readonly apiServerUrl = environment['api-base-url'];
  constructor(private http: HttpClient) { }

  public getProjects(search : string, page: number): Observable<any> {
    return this.http.get<any>(`${this.apiServerUrl}api/v1/projects/approved?search=${search}&page=${page}`);
  }

  updateVoteStatus(projectId: number): Observable<void> {
    const url = `${this.apiServerUrl}api/v1/projects/${projectId}/vote`;
    return this.http.put<void>(url, {});
  }

  public addProject(formData: FormData): Observable<any> {
    return this.http.post<any>(`${this.apiServerUrl}api/v1/projects`, formData);
  }

  public getAllSocials(): Observable<Array<Socials>> {
    return this.http.get<Array<Socials>>(`${this.apiServerUrl}api/v1/projects/socials`);
  }

  public deleteProject(projectId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/employee/delete/${projectId}`);
  }
}
