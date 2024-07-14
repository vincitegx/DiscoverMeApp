import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { Observable } from 'rxjs';
import { Socials } from '../dtos/socials';
import { Project } from '../dtos/project';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private readonly apiServerUrl = environment['api-base-url'];
  constructor(private http: HttpClient) { }

  public getProjects(search : string, page: number, sort:string): Observable<any> {
    return this.http.get<any>(`${this.apiServerUrl}api/v1/projects?search=${search}&page=${page}&sort=${sort}`);
  }

  public getUserProjects(search : string, page: number): Observable<any> {
    return this.http.get<any>(`${this.apiServerUrl}api/v1/projects/user?search=${search}&page=${page}`);
  }

  updateSupportStatus(projectId: number): Observable<void> {
    const url = `${this.apiServerUrl}api/v1/projects/${projectId}/support`;
    return this.http.put<void>(url, {});
  }

  toggleReaction(projectId: number): Observable<Project> {
    const url = `${this.apiServerUrl}api/v1/projects/${projectId}/react`;
    return this.http.put<Project>(url, {});
  }

  public addProject(formData: FormData): Observable<any> {
    return this.http.post<any>(`${this.apiServerUrl}api/v1/projects`, formData);
  }

  public deleteProject(projectId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/employee/delete/${projectId}`);
  }

  public isProjectLimitExceeded(): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiServerUrl}api/v1/projects/limit`);
  }
}
