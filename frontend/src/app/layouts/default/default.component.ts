import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.css']
})
export class DefaultComponent implements OnInit{
  constructor(private authService: AuthService, private router: Router) {}
  ngOnInit() {
    // const queryParams = this.router.parseUrl(this.router.url).queryParams;
    // if (queryParams['code'] && queryParams['authuser']) {
    //   this.authService.getToken(queryParams['code']).subscribe(() => {
    //     this.router.navigate(['/']);
    //   });
    // }
  }
}
