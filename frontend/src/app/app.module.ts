import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from '@auth0/auth0-angular';
import { DefaultModule } from './layouts/default/default.module';
import { OAuthModule } from 'angular-oauth2-oidc';
import { NotifierModule } from 'angular-notifier';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpRequestInterceptor } from './helpers/auth.interceptor';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { FacebookLoginProvider, SocialAuthServiceConfig, SocialLoginModule } from '@abacritt/angularx-social-login';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DefaultModule,
    SocialLoginModule,
    NgxWebstorageModule.forRoot(),
    NotifierModule,
    OAuthModule.forRoot(),
    AuthModule.forRoot({
          domain: 'dev-us7x34wyw3ef5c4o.us.auth0.com',
          clientId: 'dQLxtpUczr0VdNoDZJiTzYsvw3ANWEyU',
          authorizationParams: {
            redirect_uri: window.location.origin
          }
        }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpRequestInterceptor,
      multi: true
    },
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: FacebookLoginProvider.PROVIDER_ID,
            provider: new FacebookLoginProvider('1688237841617847')
          }
        ],
        onError: (err) => {
          console.error(err);
        }
      } as SocialAuthServiceConfig,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
