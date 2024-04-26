import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'frontend';
  constructor(private authService: AuthService, private router: Router) {} 
  ngOnInit() {
    const queryParams = this.router.parseUrl(this.router.url).queryParams;
    if (queryParams['code'] && queryParams['authuser']) {
      this.authService.getToken(queryParams['code']).subscribe(() => {
        this.router.navigate(['/']);
      });
    }
  }
}
