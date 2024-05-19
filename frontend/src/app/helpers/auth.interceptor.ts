import { BehaviorSubject, Observable, catchError, filter, switchMap, take, tap, throwError } from 'rxjs';
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
import { JwtResponse } from '../components/signin/jwtresponse';

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
        console.log(error);
        if (error instanceof HttpErrorResponse) {
          if (error.status === 401) {
            console.log("401 Unauthorized");
            return this.handleAuthErrors(req, next);
          }
          if (error.status === 403) {
            console.log("403 Forbidden");
            return this.handleLogoutAuthErrors(req, next);
          }
        }
        return throwError(error);
      })
    );
  }
  handleLogoutAuthErrors(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.authService.logout$().subscribe(() => {
      this.router.navigate(['/']);
      this.notifier.notify('info', "Please sign in");
    });
    return next.handle(req);
  }

  private handleAuthErrors(req: HttpRequest<any>,next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.isTokenRefreshing) {
      this.isTokenRefreshing = true;
      this.refreshTokenSubject.next(null);

      return this.authService.refreshToken().pipe(
        tap(),
        switchMap((refreshTokenResponse: JwtResponse) => {
          this.isTokenRefreshing= false;
          
          this.refreshTokenSubject.next(
            refreshTokenResponse.authToken
          );
          return next.handle(
            this.addToken(req, refreshTokenResponse.authToken)
          );
        }),
        catchError((error) => {
          console.error("Error refreshing token:", error);
          return this.handleLogoutAuthErrors(req, next);
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
