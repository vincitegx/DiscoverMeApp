import { FacebookLoginProvider, SocialAuthService, SocialUser } from '@abacritt/angularx-social-login';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { AuthService } from 'src/app/services/auth.service';
declare const FB: any;
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  private readonly notifier: NotifierService;
  username:String;
  user:any;
  loggedIn:any;
  fbUrl: string = "";
  fbUsername:string;

  constructor(notifierService: NotifierService,
    private authService:AuthService, private route: ActivatedRoute) {
    this.username="";
    this.notifier = notifierService;
    this.fbUsername = this.authService.getFBUser();
  }
  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        if(params["code"] !== undefined){
          this.authService.getFBToken(params["code"]).subscribe(result => {
            if (result != null) {
              this.notifier.notify('success', "Facebook Account "+result.name+" has been connected");
            } 
          });
        }
      }
    );
    this.username = this.authService?.getUser()?.stageName ?? "";
    this.authService.get("auth/fb/url").subscribe((data: any) => {this.fbUrl = data.authURL});
  }

  fbAction(): void {
    if (this.fbUsername) {
      // If fbUsername exists, it means the user is connected, implement disconnection logic here
      // Call a method in authService to disconnect the Facebook account
      this.authService.disconnectFacebook().subscribe(() => {
        this.notifier.notify('success', 'Facebook Account has been disconnected');
        this.fbUsername = ''; // Reset the fbUsername
      });
    } else {
      // If fbUsername doesn't exist, it means the user is not connected, implement connection logic here
      window.location.href = this.fbUrl; // Redirect to Facebook authentication
    }
  }
}
