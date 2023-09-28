import { Observable } from 'rxjs';
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

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
  constructor(public authService: AuthService) {}
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    req = req.clone({ withCredentials: true });
    if (req.url.indexOf('refresh') !== -1 || req.url.indexOf('login') !== -1) {
      return next.handle(req);
    }
    // const jwtToken = this.authService.getJwtToken();

    //     if (jwtToken) {
    //         return next.handle(this.addToken(req, jwtToken)).pipe(catchError(error => {
    //             if (error instanceof HttpErrorResponse
    //                 && error.status === 403) {
    //                 return this.handleAuthErrors(req, next);
    //             } else {
    //                 return throwError(error);
    //             }
    //         }));
    //     }
    return next.handle(req);
  }

//   private handleAuthErrors(req: HttpRequest<any>, next: HttpHandler)
//         : Observable<HttpEvent<any>> {
//         if (!this.isTokenRefreshing) {
//             this.isTokenRefreshing = true;
//             this.refreshTokenSubject.next(null);

//             return this.authService.refreshToken().pipe(
//                 switchMap((refreshTokenResponse: Loginresponsepayload) => {
//                     this.isTokenRefreshing = false;
//                     this.refreshTokenSubject
//                         .next(refreshTokenResponse.authenticationToken);
//                     return next.handle(this.addToken(req,
//                         refreshTokenResponse.authenticationToken));
//                 })
//             )
//         } else {
//             return this.refreshTokenSubject.pipe(
//                 filter(result => result !== null),
//                 take(1),
//                 switchMap((res) => {
//                     return next.handle(this.addToken(req,
//                         this.authService.getJwtToken()))
//                 })
//             );
//         }
//     }

//     addToken(req: HttpRequest<any>, jwtToken: any) {
//         return req.clone({
//             headers: req.headers.set('Authorization',
//                 'Bearer ' + jwtToken)
//         });
    // }
}
