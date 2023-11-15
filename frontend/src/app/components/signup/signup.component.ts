import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { SignupRequest } from './signup-request';
import { BehaviorSubject } from 'rxjs';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  private readonly notifier: NotifierService;
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();
  public form: FormGroup;
  private signupRequest: SignupRequest;

  constructor(private auth: AuthService, private router: Router, private formBuilder: FormBuilder,
    notifierService: NotifierService) {
    this.signupRequest = new SignupRequest('', '', '');
    this.form= new FormGroup({
      stageName: new FormControl(''),
      email: new FormControl(''),
      password: new FormControl('')
    });
    this.notifier = notifierService;
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      stageName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern('^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$')]]
    });
  }

  signup() {
    if (this.form.valid) {
      this.isLoading.next(true); 
      this.signupRequest.setStageName(this.form.get('stageName')?.value);
      this.signupRequest.setEmail(this.form.get('email')?.value);
      this.signupRequest.setPassword(this.form.get('password')?.value);

      this.auth.signup(this.signupRequest).subscribe({
        next: (response: any) => {
          console.log(response);
          this.router.navigateByUrl('regsucces');
        },
        error: (error: HttpErrorResponse) => {
          this.notifier.notify('error', 'Registration Failed!');
        }
      });
      this.form.reset();
      this.isLoading.next(false); 
    }
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

}
