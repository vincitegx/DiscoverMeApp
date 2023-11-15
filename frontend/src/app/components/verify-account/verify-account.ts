import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-verify',
  template: '', // No HTML template needed
})
export class VerifyComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Get the token from the URL
    const token = this.route.snapshot.queryParams['token'];
    console.log(token);
    // Call the backend API to verify the account
    this.authService.verifyAccount(token).subscribe(
      () => {
        // Account activation successful
        // Redirect to the login page
        this.router.navigate(['/signin'], { queryParams: { verified: 'true' }});
      },
      (error) => {
        console.error('Error verifying account:', error);
        // Redirect to an error page or handle the error as needed
        this.router.navigate(['/error']);
      }
    );
  }
}