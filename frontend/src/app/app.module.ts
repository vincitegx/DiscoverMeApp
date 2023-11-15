import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from '@auth0/auth0-angular';
import { DefaultModule } from './layouts/default/default.module';
import { OAuthModule } from 'angular-oauth2-oidc';
import { NotifierModule } from 'angular-notifier';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DefaultModule,
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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
