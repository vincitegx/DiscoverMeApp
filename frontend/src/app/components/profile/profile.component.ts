import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
declare const FB: any;
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  username:String;


  constructor(private authService:AuthService) {
    this.username="";
    // Load the Facebook SDK asynchronously
    (window as any).fbAsyncInit = function () {
      FB.init({
        appId: '1688237841617847',
        cookie: true,
        xfbml: true,
        version: 'v18.0'
      });
    };

    // Load the Facebook SDK script
    (function (d, s, id) {
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) { return; }
      js = <HTMLScriptElement>d.createElement(s); js.id = id;
      js.src = "https://connect.facebook.net/en_US/sdk.js";
      fjs.parentNode?.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
  }
  ngOnInit(): void {
    this.username = this.authService?.getUser()?.stageName ?? "";
  }

  loginWithFacebook(): void {
    // Initiate Facebook login process
    FB.login((response: any) => {
      if (response.authResponse) {
        // User logged in successfully, response.authResponse contains access token
        const accessToken = response.authResponse.accessToken;
        // Send the access token to your backend for further processing
        this.sendAccessTokenToBackend(accessToken);
      } else {
        // User cancelled login or did not authorize the app
        console.log('User cancelled login or did not authorize.');
      }
    }, { scope: 'email,user_friends' }); // Specify the required permissions
  }

  sendAccessTokenToBackend(accessToken: string): void {
    // Send the access token to your backend API using HttpClient
    // Example:
    // this.http.post('YOUR_BACKEND_API_ENDPOINT', { accessToken: accessToken })
    //   .subscribe(response => {
    //     console.log('Backend API response:', response);
    //   });
  }

}
