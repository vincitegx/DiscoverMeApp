import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoverComponent } from './components/cover/cover.component';
import { HomeComponent } from './components/home/home.component';
import { DefaultComponent } from './layouts/default/default.component';
import { SigninComponent } from './components/signin/signin.component';
import { SignupComponent } from './components/signup/signup.component';
// import { AuthGuard } from '@auth0/auth0-angular';
import { ProfileComponent } from './components/profile/profile.component';
import { SubmitProjectComponent } from './components/submit-project/submit-project.component';
import { AccountCreatedComponent } from './components/account-created/account-created.component';
import { VerifyComponent } from './components/verify-account/verify-account';
import { AuthGuard } from './services/auth.guard';
import { ProjectSubmissionGuard } from './services/project-submission.guard';
import { AuthRedirectGuard } from './services/auth-redirect.guard';

const routes: Routes = [
  {
    path: '',
    component: DefaultComponent,
    children: [
      {
        path: '',
        component: CoverComponent,
        canActivate: [AuthRedirectGuard]
      },
      {
        path: 'signin',
        component: SigninComponent,
        canActivate: [AuthRedirectGuard]
      },
      {
        path: 'signup',
        component: SignupComponent,
        canActivate: [AuthRedirectGuard]
      },
      {
        path: 'regsucces',
        component: AccountCreatedComponent,
        canActivate: [AuthRedirectGuard]
      },
      {
        path: 'verify',
        component: VerifyComponent,
        canActivate: [AuthRedirectGuard]
      },
      {
        path: 'home',
        component: HomeComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'project',
        component: SubmitProjectComponent,
        canActivate: [AuthGuard, ProjectSubmissionGuard]
      },
      {
        path: '**',
        redirectTo: '',
      }
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
