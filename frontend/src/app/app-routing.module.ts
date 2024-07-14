import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SigninComponent } from './components/signin/signin.component';
import { SignupComponent } from './components/signup/signup.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SubmitProjectComponent } from './components/submit-project/submit-project.component';
import { AccountCreatedComponent } from './components/account-created/account-created.component';
import { VerifyComponent } from './components/verify-account/verify-account';
import { AuthGuard } from './services/auth.guard';
import { ProjectSubmissionGuard } from './services/project-submission.guard';
import { AuthRedirectGuard } from './services/auth-redirect.guard';
import { ProjectLinkComponent } from './components/project-link/project-link.component';
import { AppComponent } from './app.component';
import { RedirectComponent } from './components/auth/auth-redirect';
import { ProjectsComponent } from './components/projects/projects.component';

const routes: Routes = [
  {
    path: '',
    component: AppComponent,
    children: [
      {
        path: '',
        component: SigninComponent,
        canActivate: [AuthRedirectGuard]
      },
      {
        path: 'home',
        component: HomeComponent,
        canActivate: [AuthGuard]
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
        path: 'auth/redirect',
        component: RedirectComponent,
        canActivate: [AuthRedirectGuard],
      },
      {
        path: 'verify',
        component: VerifyComponent,
        canActivate: [AuthRedirectGuard]
      },
      {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'project',
        component: SubmitProjectComponent,
        canActivate: [AuthGuard, ProjectSubmissionGuard],
        children: [
          {
            path: 'link',
            component: ProjectLinkComponent,
            canActivate: [AuthGuard, ProjectSubmissionGuard],
          }
        ]
      },
      {
        path: 'projects',
        component: ProjectsComponent,
        canActivate: [AuthGuard]
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
