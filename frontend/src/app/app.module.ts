import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from '@auth0/auth0-angular';
import { environment } from '../environments/environment';
import { LoginButtonComponent } from './components/login-button/login-button.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { CoverComponent } from './components/cover/cover.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginButtonComponent,
    HeaderComponent,
    CoverComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
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
