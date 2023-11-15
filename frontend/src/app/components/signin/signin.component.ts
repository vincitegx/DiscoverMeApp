import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { JwtResponse } from 'src/app/components/signin/jwtresponse';
import { ActivatedRoute, Router } from '@angular/router';
import { SigninRequest } from 'src/app/components/signin/signinrequest';
import { NotifierService } from 'angular-notifier';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
})
export class SigninComponent implements OnInit {
  private readonly notifier: NotifierService;
  public form: FormGroup;
  private signinRequest: SigninRequest;
  private verifiedMessage: string;
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  constructor(
    private auth: AuthService,
    private router: Router,
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    notifierService: NotifierService
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
    this.activatedRoute.queryParams
      .subscribe(params => {
        if (params['verified'] !== undefined && params['verified'] === 'true') {
          this.verifiedMessage = 'Account has been verified, You can login!';
          this.notifier.notify('info', this.verifiedMessage);
        }
      });
  }

  login() {
    if (this.form.valid) {
      this.isLoading.next(true); 
      this.signinRequest.setEmail(this.form.get('email')?.value);
      this.signinRequest.setPassword(this.form.get('password')?.value);
      this.auth.login(this.signinRequest).subscribe({
        next: (response: JwtResponse) => {
          console.log(response);
          this.router.navigateByUrl('home');
          this.notifier.notify('success', 'Login Successful');
        },
        error: (error: HttpErrorResponse) => {
          this.notifier.notify('error', 'Login Failed');
        },
      });
      this.isLoading.next(false);
    }
  }
}
