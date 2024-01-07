import { Component, OnInit, Inject, OnDestroy } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { AuthService } from 'src/app/services/auth.service';
import { BehaviorSubject, Observable, Subscription, map, take } from 'rxjs';
import { UserDto } from 'src/app/dtos/userdto';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit, OnDestroy {
  isloggedIn$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  isLoggedInSub$: Subscription = new Subscription();
  logout$: Subscription = new Subscription();
  user$: BehaviorSubject<UserDto> = new BehaviorSubject<UserDto>({});
  constructor(
    @Inject(DOCUMENT) public document: Document,
    public auth: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnDestroy(): void {
    this.isLoggedInSub$.unsubscribe();
    this.logout$.unsubscribe();
  }

  ngOnInit() {
    this.isLoggedInSub$ = this.auth.isLoggedIn$().subscribe((loggedIn: boolean) => {
      this.isloggedIn$.next(loggedIn);
      if (loggedIn) {
        this.user$.next(this.auth.getUser());
      }
    });
  }

  logout() {
    this.logout$ = this.auth.logout$().subscribe(response => {
      this.router.navigateByUrl('/');
    });
  }

  containsNonAuthPath(): boolean {
    return !this.document.URL.includes('signin', 7) || !this.document.URL.includes('', 7);
  }

  isloggedIn(): Observable<boolean> {
    return this.auth.isLoggedIn$().pipe(
      take(1),
      map(isAuthenticated => {
        if (isAuthenticated) {
          return true;
        } else {
          return false;
        }
      })
    );
  }

  getUser(): UserDto | null {
    return this.auth.getUser();
  }
}
