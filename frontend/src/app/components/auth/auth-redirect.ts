import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-redirect',
  template: `
    <div *ngIf="loading" id="preloader1">
      <div class="loader1"></div>
    </div>
  `,
  styles: [`
    #preloader1 {
      position: fixed;
      width: 100%;
      height: 100%;
      background: rgba(255, 255, 255, 0.8);
      z-index: 9999;
      display: flex;
      justify-content: center;
      align-items: center;
    }
    .loader1 {
      width: 40px;
      height: 40px;
      border-radius: 60px;
      animation: loader 0.8s linear infinite;
      -webkit-animation: loader 0.8s linear infinite;
    }
    @keyframes loader1 {
      0% {
        -webkit-transform: rotate(0deg);
        transform: rotate(0deg);
        border: 4px solid #45fe34;
        border-left-color: transparent;
      }
      50% {
        -webkit-transform: rotate(180deg);
        transform: rotate(180deg);
        border: 4px solid #673ab7;
        border-left-color: transparent;
      }
      100% {
        -webkit-transform: rotate(360deg);
        transform: rotate(360deg);
        border: 4px solid #45fe34;
        border-left-color: transparent;
      }
    }
    @-webkit-keyframes loader1 {
      0% {
        -webkit-transform: rotate(0deg);
        border: 4px solid #45fe34;
        border-left-color: transparent;
      }
      50% {
        -webkit-transform: rotate(180deg);
        border: 4px solid #673ab7;
        border-left-color: transparent;
      }
      100% {
        -webkit-transform: rotate(360deg);
        border: 4px solid #45fe34;
        border-left-color: transparent;
      }
    }
  `]
})
export class RedirectComponent implements OnInit {
  loading = true;
  constructor(private authService: AuthService, private router: Router) { }
  ngOnInit() {
    const queryParams = this.router.parseUrl(this.router.url).queryParams;
    if (queryParams['code'] && queryParams['authuser']) {
      this.authService.getToken(queryParams['code']).subscribe(
        () => {
          setTimeout(() => {
            this.loading = false;
            this.router.navigate(['/home']);
          }, 0);
        },
        (error) => {
          console.error('Error verifying account:', error);
          this.loading = false;
          this.router.navigate(['/']);
        }
      );
    }
  }
}