import { Component, OnInit, Inject } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  constructor(
    @Inject(DOCUMENT) public document: Document,
    public auth: AuthService
  ) {}

  ngOnInit(): void {
  }

  loginWithRedirect(): void {
    this.auth.loginWithRedirect();
  }

  logout() {
    this.auth.logout({
      logoutParams: { returnTo: this.document.location.origin },
    });
  }

  containsLoginPath(): boolean {
    return this.document.URL.includes('signin', 7);
  }

  isHomePath(): boolean {
    return this.document.location.href === 'http://localhost:4200/';
  }

  shouldSetBackgroundColor(): boolean {
    return this.auth.isAuthenticated$ && !this.isHomePath();
  }
}
