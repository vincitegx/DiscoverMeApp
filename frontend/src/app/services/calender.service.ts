import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { CalenderResponse } from '../dtos/calender';

@Injectable({
  providedIn: 'root'
})
export class CalenderService {
  private readonly apiServerUrl = environment['api-base-url'];
  constructor(private http: HttpClient) { }

  public getCurrentCalender(): Observable<CalenderResponse> {
    return this.http.get<CalenderResponse>(`${this.apiServerUrl}api/v1/projects/calenders`);
  }
}
