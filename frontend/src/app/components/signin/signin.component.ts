import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { JwtResponse } from 'src/app/components/signin/jwtresponse';
import { Router } from '@angular/router';
import { SigninRequest } from 'src/app/components/signin/signinrequest';
import { NotifierService } from 'angular-notifier';
import { BehaviorSubject } from 'rxjs';
import { VerifiedMessageService } from 'src/app/shared/services/verified-message.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
})
export class SigninComponent implements OnInit {
  private readonly notifier: NotifierService;
  url: string = "";
  public form: FormGroup;
  private signinRequest: SigninRequest;
  private verifiedMessage: string;
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();
  isloggedIn$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(
    private auth: AuthService,
    private router: Router,
    private formBuilder: FormBuilder,
    notifierService: NotifierService,
    private verifiedMessageService: VerifiedMessageService
  ) {
    this.signinRequest = new SigninRequest('', '');
    this.verifiedMessage = "";
    this.form = new FormGroup({
      phoneNumber: new FormControl(''),
      password: new FormControl(''),
    });
    this.notifier = notifierService;
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
    this.verifiedMessageService.verifiedMessage$.subscribe((message) => {
      this.verifiedMessage = message;
      if (message) {
        this.notifier.notify('success', message);
      }
    });
  }

  login() {
    this.form.markAllAsTouched();
    if (this.form.valid) {
      this.isLoading.next(true); 
      this.signinRequest.setEmail(this.form.get('email')?.value);
      this.signinRequest.setPassword(this.form.get('password')?.value);
      this.auth.login(this.signinRequest).subscribe({
        next: (response: JwtResponse) => {
          this.isLoading.next(false);
          if(response.user.role == "ADMIN"){
            this.router.navigateByUrl('home');
          }else{
            this.router.navigateByUrl('home');
          }
          this.notifier.notify('success', 'Welcome, '+ response.user.userName);
          this.form.reset();
        },
        error: (error: HttpErrorResponse) => {
          this.isLoading.next(false);
          if (error.status === 400) {
            this.notifier.notify('error', 'Invalid username or password');
            this.form.reset();
          } else {
            this.notifier.notify('error', 'An unexpected error occurred !');
            this.form.reset();
          }
        },
      });
    }else{
      this.notifier.notify('error', 'Invalid Login');
    }
  }
}
