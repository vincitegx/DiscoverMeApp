import {
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
  UrlTree,
} from '@angular/router';
import { Observable, catchError, map, of } from 'rxjs';
import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { CalenderService } from './calender.service';
@Injectable({
  providedIn: 'root',
})
export class ProjectSubmissionGuard {
  constructor(private calenderService: CalenderService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.calenderService.getCurrentCalender().pipe(
      map(response => {
        console.log(response.status);
        if (response.status === 'SUBMISSION') {
          return true;
        } else {
          return this.router.createUrlTree(['/home']);
        }
      }),
      catchError(() => {
        return of(this.router.createUrlTree(['/home']));
      })
    );
  }
}

