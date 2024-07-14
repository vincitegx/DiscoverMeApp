import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DefaultModule } from './layouts/default/default.module';
import { NotifierModule } from 'angular-notifier';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HttpRequestInterceptor } from './helpers/auth.interceptor';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { DefaultComponent } from './layouts/default/default.component';
import { CoverComponent } from './components/cover/cover.component';
import { HomeComponent } from './components/home/home.component';
import { SigninComponent } from './components/signin/signin.component';
import { SignupComponent } from './components/signup/signup.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SubmitProjectComponent } from './components/submit-project/submit-project.component';
import { AccountCreatedComponent } from './components/account-created/account-created.component';
import { VerifyComponent } from './components/verify-account/verify-account';
import { ProjectLinkComponent } from './components/project-link/project-link.component';
import { GoogleBtnComponent } from './components/google-btn/google-btn.component';
import { VideoHoverDirective } from './video-hover.directive';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from './components/shared/shared.module';
import { RedirectComponent } from './components/auth/auth-redirect';
import { ProjectsComponent } from './components/projects/projects.component';
@NgModule({
  declarations: [
    AppComponent,
    DefaultComponent,
    CoverComponent,
    HomeComponent,
    SigninComponent,
    SignupComponent,
    ProfileComponent,
    SubmitProjectComponent,
    AccountCreatedComponent,
    VerifyComponent,
    RedirectComponent,
    ProjectLinkComponent,
    GoogleBtnComponent,
    VideoHoverDirective,
    ProjectsComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    HttpClientModule,
    SharedModule,
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    // DefaultModule,
    NgxWebstorageModule.forRoot(),
    NotifierModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpRequestInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
