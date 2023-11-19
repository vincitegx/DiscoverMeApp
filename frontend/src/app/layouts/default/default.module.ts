import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DefaultComponent } from './default.component';
import { CoverComponent } from '../../components/cover/cover.component';
import { HomeComponent } from '../../components/home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { SharedModule } from 'src/app/components/shared/shared.module';
import { SigninComponent } from 'src/app/components/signin/signin.component';
import { SignupComponent } from 'src/app/components/signup/signup.component';
import { VideoHoverDirective } from 'src/app/video-hover.directive';
import { ProfileComponent } from 'src/app/components/profile/profile.component';
import { SubmitProjectComponent } from 'src/app/components/submit-project/submit-project.component';
import { AccountCreatedComponent } from 'src/app/components/account-created/account-created.component';
import { VerifyComponent } from 'src/app/components/verify-account/verify-account';
import { HttpRequestInterceptor } from 'src/app/helpers/auth.interceptor';


@NgModule({
  declarations: [
    DefaultComponent,
    CoverComponent,
    HomeComponent,
    SigninComponent,
    SignupComponent,
    ProfileComponent,
    SubmitProjectComponent,
    AccountCreatedComponent,
    VerifyComponent,
    VideoHoverDirective
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    HttpClientModule,
    SharedModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpRequestInterceptor,
      multi: true
    }
  ]
})
export class DefaultModule { }
