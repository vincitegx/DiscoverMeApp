import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { AuthService } from 'src/app/services/auth.service';
import { SocialService } from 'src/app/services/social.service';
import { Observable } from 'rxjs';
import { SessionStorageService } from 'ngx-webstorage';
import { UserSocials } from 'src/app/dtos/usersocial';
declare const FB: any;
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  private readonly notifier: NotifierService;
  username: String;
  user: any;
  loggedIn: any;
  fbUrl: string = "";
  fbUsername: string;
  igUrl: string = "";
  igUsername: string;

  constructor(notifierService: NotifierService,
    private authService: AuthService, private route: ActivatedRoute, private socialService: SocialService,
    private router: Router, private sessionStorage: SessionStorageService) {
    this.username = "";
    this.notifier = notifierService;
    this.fbUsername = "";
    this.igUsername = "";
  }
  ngOnInit(): void {
    this.loadUserSocials();
    const currentPlatform = sessionStorage.getItem('currentPlatform');
    this.route.queryParams
      .subscribe(params => {
        if (params["code"] !== undefined && currentPlatform !== null) {
          if (currentPlatform === 'Facebook') {
            this.connectSocialAccount(this.socialService.getFBToken(params["code"]), 'Facebook');
          } else if (currentPlatform === 'Instagram') {
            this.connectSocialAccount(this.socialService.getIGToken(params["code"]), 'Instagram');
          }
          this.sessionStorage.clear('currentPlatform');
        }
      });
    this.username = this.authService?.getUser()?.userName ?? "";
    this.authService.get("auth/fb/url").subscribe((data: any) => { this.fbUrl = data.authURL });
    this.authService.get("auth/ig/url").subscribe((data: any) => { this.igUrl = data.authURL });
  }

  fbAction(): void {
    this.socialAction('Facebook', this.fbUsername, this.socialService.disconnectFacebook.bind(this.socialService), this.fbUrl);
  }

  igAction(): void {
    this.socialAction('Instagram', this.igUsername, this.socialService.disconnectInstagram.bind(this.socialService), this.igUrl);
  }

  loadUserSocials() {
    this.socialService.getUserSocials().subscribe(
      (userSocials: UserSocials[]) => {
        userSocials.forEach(social => {
          switch (social.social) {
            case 'FACEBOOK':
              this.fbUsername = social.socialUserName || '';
              break;
            case 'INSTAGRAM':
              this.igUsername = social.socialUserName || '';
              break;
            // Add cases for other social platforms if needed
          }
        });
      },
      (error) => {
        console.error('Error loading user socials:', error);
      }
    );
  }

  socialAction(platform: string, username: string, disconnectFunction: () => Observable<any>, authUrl: string): void {
    const socialPlatformProp: keyof ProfileComponent = platform.toLowerCase() + 'Username' as keyof ProfileComponent;
    if (username) {
      disconnectFunction().subscribe(() => {
        if(username === 'Facebook'){
          this.socialService.disconnectFacebook().subscribe(res=>{
            if(res){
              this.notifier.notify('success', `${platform} Account has been disconnected`);
              this[socialPlatformProp] = '';
            }else{
              this.notifier.notify('error', `${platform} Account was not disconnected`);
            }
          })  
        }else{
          this.socialService.disconnectInstagram().subscribe(res=>{
            if(res){
              this.notifier.notify('success', `${platform} Account has been disconnected`);
              this[socialPlatformProp] = '';
            }else{
              this.notifier.notify('error', `${platform} Account was not disconnected`);
            }
          })
        }
        
      });
    } else {
      sessionStorage.setItem('currentPlatform', platform);
      window.location.href = authUrl;
    }
  }

  connectSocialAccount(tokenObservable: Observable<any>, platform: string): void {
    tokenObservable.subscribe(result => {
      if (result != null) {
        this.router.navigateByUrl('profile');
        this.notifier.notify('success', `${platform} Account ${result.name} has been connected`);
      }
    });
  }

  editProfile(profileForm: NgForm) {
    if (profileForm.valid) {
      this.authService.updateProfile(profileForm.value);
      this.router.navigateByUrl('home');
      this.notifier.notify('success', "Profile updated successfully");
    } else {
      this.notifier.notify('error', 'User name not valid');
    }
  }
}
