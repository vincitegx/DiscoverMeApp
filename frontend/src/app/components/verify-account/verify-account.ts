import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { VerifiedMessageService } from 'src/app/shared/services/verified-message.service';

@Component({
  selector: 'app-verify',
  template: '', // No HTML template needed
})
export class VerifyComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router,
    private verifiedMessageService: VerifiedMessageService
  ) {}

  ngOnInit(): void {
    const token = this.route.snapshot.queryParams['token'];
    this.authService.verifyAccount(token).subscribe(
      () => {
        this.verifiedMessageService.setVerifiedMessage('Account has been verified, You can login!');
        this.router.navigate(['/signin']);
      },
      (error) => {
        console.error('Error verifying account:', error);
        this.router.navigate(['/error']);
      }
    );
  }
}