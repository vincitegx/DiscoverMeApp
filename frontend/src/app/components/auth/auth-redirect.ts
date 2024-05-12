import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
    selector: 'app-redirect',
    template: '',
})
export class RedirectComponent implements OnInit {
    constructor(private authService: AuthService, private router: Router) { }
    ngOnInit() {
        const queryParams = this.router.parseUrl(this.router.url).queryParams;
        if (queryParams['code'] && queryParams['authuser']) {
            this.authService.getToken(queryParams['code']).subscribe(
              () => {
                setTimeout(() => {
                  this.router.navigate(['/home']);
                }, 0);
              },
              (error) => {
                console.error('Error verifying account:', error);
                this.router.navigate(['/']);
              }
            );
          }
    }
}