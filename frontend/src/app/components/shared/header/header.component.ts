import { Component, OnInit, Inject, OnDestroy } from '@angular/core';
// import { AuthService } from '@auth0/auth0-angular';
import { DOCUMENT } from '@angular/common';
import { AuthService } from 'src/app/services/auth.service';
import { BehaviorSubject, Observable, Subscription, of } from 'rxjs';
import { UserDto } from 'src/app/dtos/userdto';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit , OnDestroy {
  isloggedIn$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  isLoggedInSub$: Subscription = new Subscription();
  logout$: Subscription = new Subscription();
  user$: BehaviorSubject<UserDto> = new BehaviorSubject<UserDto>({});
  constructor(
    @Inject(DOCUMENT) public document: Document,
    public auth: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnDestroy(): void {
    this.isLoggedInSub$.unsubscribe();
    this.logout$.unsubscribe();
  }

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        if (params["code"] !== undefined && params["authuser"] !== undefined) {
          console.log("for google");
          this.auth.getToken(params["code"]).subscribe(result => {
            if (result != null) {
              this.user$.next(result.user);
              // this.router.navigateByUrl('home');
            } 
          });
        }
      }
    );
    this.isLoggedInSub$ = this.auth.isLoggedIn$().subscribe((loggedIn: boolean) => {
      this.isloggedIn$.next(loggedIn);

      if (loggedIn) {
        // If the user is logged in, fetch and update user information
        this.user$.next(this.auth.getUser());
      }
    });
    // const user = this.getUser();
    // if (user) {
    //   this.isloggedIn$.next(true);
    //   this.user$.next(user);
    // } else {
    //   this.isloggedIn$.next(false);
    // }
    // this.isLoggedInSub$ = this.auth.isLoggedIn$().subscribe((loggedIn: boolean) => {
    //   this.isloggedIn$.next(loggedIn);

    //   if (loggedIn) {
    //     this.user$.next(this.auth.getUser());
    //   }
    // });
  }

  // loginWithRedirect(): void {
  //   this.auth.loginWithRedirect();
  // }

  // logout() {
  //   this.auth.logout({
  //     logoutParams: { returnTo: this.document.location.origin },
  //   });
  // }

  logout() {
    this.logout$ = this.auth.logout$().subscribe(response=>{
      this.router.navigateByUrl('/');
    });
    
  }

  containsNonAuthPath(): boolean {
    return !this.document.URL.includes('signin', 7) || !this.document.URL.includes('',7);
  }

  isHomePath(): boolean {
    return this.document.location.href === 'https://localhost:4200/';
  }

  // shouldSetBackgroundColor(): boolean {
  //   return this.auth.isAuthenticated$ && !this.isHomePath();
  // }

  shouldSetBackgroundColor(): boolean {
    return !this.isHomePath();
  }

  isloggedIn():Observable<boolean>{
    return this.auth.isLoggedIn$();
  }

  getUser():UserDto | null{
    return this.auth.getUser();
  }
}
