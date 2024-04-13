import { BehaviorSubject, Observable, catchError, filter, switchMap, take, throwError } from 'rxjs';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  isTokenRefreshing = false;
  private readonly notifier: NotifierService;
  refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject(null);
  constructor(public authService: AuthService, private router: Router,
    notifierService: NotifierService
  ) { 
    this.notifier = notifierService;
  }

  intercept(req: HttpRequest<any>,next: HttpHandler): Observable<HttpEvent<any>> {
    const jwtToken = this.authService.getJwtToken();
    if (jwtToken) {
      req = this.addToken(req, jwtToken);
    }
    req = req.clone({ withCredentials: true });
    if (req.url.indexOf('refresh') !== -1 || req.url.indexOf('login') !== -1 || req.url.indexOf('logout') !== -1) {
      return next.handle(req);
    }

    return next.handle(req).pipe(
      catchError((error) => {
        if (error instanceof HttpErrorResponse && error.status === 401) {
          console.log("401")
          return this.handleAuthErrors(req, next);
        }
        // else if (error instanceof HttpErrorResponse && error.status === 400) {
        //   console.log("400")
        //   return this.handleLogoutAuthErrors(req, next);
        // }else {
        //   return throwError(error);
        // }
        return throwError(error);
      })
    );
  }
  handleLogoutAuthErrors(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.authService.logout$().subscribe(() => {
      this.router.navigate(['/signin']);
      this.notifier.notify('info', "Please sign in again");
    });
    return next.handle(req);
  }

  private handleAuthErrors(req: HttpRequest<any>,next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.isTokenRefreshing) {
      this.isTokenRefreshing = true;
      this.refreshTokenSubject.next(null);

      return this.authService.refreshToken().pipe(
        switchMap((refreshTokenResponse: any) => {
          this.isTokenRefreshing= false;
          
          this.refreshTokenSubject.next(
            refreshTokenResponse.authenticationToken
          );
          return next.handle(
            this.addToken(req, refreshTokenResponse.authenticationToken)
          );
        }),
        catchError((error) => {
          // if (error instanceof HttpErrorResponse && error.status === 403) {
          //   console.log("403")
          //   return this.handleLogoutAuthErrors(req, next);
          // }
          return this.handleLogoutAuthErrors(req, next);
          // this.authService.logout$();
          // return throwError(error);
        })
      );
    } else {
      return this.refreshTokenSubject.pipe(
        filter((result) => result !== null),
        take(1),
        switchMap((token) => {
          return next.handle(this.addToken(req, token));
        })
      );
    }
  }

  private addToken(req: HttpRequest<any>, jwtToken: string): HttpRequest<any> {
    return req.clone({
      setHeaders: {
        Authorization: `Bearer ${jwtToken}`,
      },
    });
  }
}
