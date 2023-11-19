import { BehaviorSubject, Observable, catchError, filter, switchMap, take, throwError } from 'rxjs';
import {
  HTTP_INTERCEPTORS,
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { JwtResponse } from '../components/signin/jwtresponse';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  isTokenRefreshing = false;
  refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject(null);
  constructor(public authService: AuthService) { }

  intercept(req: HttpRequest<any>,next: HttpHandler): Observable<HttpEvent<any>> {
    const jwtToken = this.authService.getJwtToken();
    if (jwtToken) {
      req = this.addToken(req, jwtToken);
    }
    req = req.clone({ withCredentials: true });

    if (req.url.indexOf('refresh') !== -1 || req.url.indexOf('login') !== -1) {
      return next.handle(req);
    }

    return next.handle(req).pipe(
      catchError((error) => {
        if (error instanceof HttpErrorResponse && error.status === 403) {
          return this.handleAuthErrors(req, next);
        } else {
          return throwError(error);
        }
      })
    );
  }

  private handleAuthErrors(req: HttpRequest<any>,next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.authService.isTokenRefreshing$) {
      this.authService.isTokenRefreshingSubject.next(true);
      this.authService.refreshTokenSubject$.next(null);

      return this.authService.refreshToken().pipe(
        switchMap((refreshTokenResponse: any) => {
          this.authService.isTokenRefreshingSubject.next(false);
          
          this.authService.refreshTokenSubject$.next(
            refreshTokenResponse.authenticationToken
          );
          return next.handle(
            this.addToken(req, refreshTokenResponse.authenticationToken)
          );
        }),
        catchError((error) => {
          // Handle refresh token failure (e.g., redirect to login page)
          this.authService.logout(); // Example: Log the user out
          return throwError(error);
        })
      );
    } else {
      // Wait for token refreshing to complete and then retry the request
      return this.authService.refreshTokenSubject$.pipe(
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
