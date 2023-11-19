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
import { UserstateService } from '../shared/services/userstate.service';
@Injectable({
  providedIn: 'root',
})
export class AuthGuard {
  constructor(public authService: AuthService, public router: Router,private userStateService: UserstateService) {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.userStateService.isLoggedIn.pipe(
      map((loggedIn: boolean) => {
        if (!loggedIn) {
          window.alert('Access Denied, Login is Required to Access This Page!');
          return this.router.createUrlTree(['/signin']);
        }
        return true;
      })
    );
  }
}
