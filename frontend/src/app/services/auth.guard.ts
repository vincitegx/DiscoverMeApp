import { Injectable } from '@angular/core';
import { CanActivateFn } from '@angular/router';

import {
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
  UrlTree,
} from '@angular/router';
import { Observable, map } from 'rxjs';
import { AuthService } from './auth.service';
import { NotifierService } from 'angular-notifier';
@Injectable({
  providedIn: 'root',
})
export class AuthGuard {
  constructor(public authService: AuthService, public router: Router,notifierService: NotifierService) {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const isAuthenticated = this.authService.isLoggedIn$();
    if (isAuthenticated) {
      return true;
    } else {
      return this.router.createUrlTree(['/signin']);
    }
  }
}
