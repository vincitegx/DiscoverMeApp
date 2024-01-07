import {
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
  UrlTree,
} from '@angular/router';
import { Observable, catchError, map, of } from 'rxjs';
import { Injectable } from '@angular/core';
import { ProjectService } from './project.service';
@Injectable({
  providedIn: 'root',
})
export class ProjectSubmissionGuard {
  constructor(private projectService: ProjectService, private router: Router) {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.projectService.isProjectLimitExceeded().pipe(
      map(isLimitExceeded => {
        if (isLimitExceeded) {
          return this.router.createUrlTree(['/']);
        } else {
          return true;
        }
      }),
      catchError(() => {
        return of(this.router.createUrlTree(['/']));
      })
    );
  }
}

