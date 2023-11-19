import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { SignupRequest } from './signup-request';
import { BehaviorSubject } from 'rxjs';
import { NotifierService } from 'angular-notifier';
import { debounceTime } from 'rxjs/operators';

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
    this.notifier = notifierService;
    this.form = this.formBuilder.group({
      stageName: ['', { validators: [Validators.required], updateOn: 'blur' }],
      email: ['', { validators: [Validators.required, Validators.email], updateOn: 'blur' }],
      password: ['', {
        validators: [
          Validators.required,
          Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/),
        ],
        updateOn: 'blur',
      }]
    });
  }

  ngOnInit(): void {
    this.subscribeToFormControlChanges();
  }

  private subscribeToFormControlChanges(): void {
    Object.keys(this.form.controls).forEach((controlName) => {
      const control = this.form.get(controlName);
      if (control) {
        control.valueChanges.pipe(debounceTime(300)).subscribe(() => {
          this.validateControl(controlName);
        });
      }
    });
  }

  private validateForm(): void {
    let isFormValid = true;

  for (const controlName of Object.keys(this.form.controls)) {
    const control = this.form.get(controlName);

    if (control && control.invalid && (control.touched || control.dirty)) {
      const errorMessage = this.getErrorMessage(controlName);
      isFormValid = false;
      this.notifier.notify('error', errorMessage);
      break; // Stop processing after the first invalid control
    }
  }
  }

  private validateControl(controlName: string): void {
    const control = this.form.get(controlName);
    if (control && control.invalid && (control.touched || control.dirty)) {
      const errorMessage = this.getErrorMessage(controlName);
      this.notifier.notify('error', errorMessage);
    }
  }

  private getErrorMessage(controlName: string): string {
    const control = this.form.get(controlName);

    if (control?.hasError('required')) {
      if(controlName == 'stageName'){
        controlName = 'Stage Name';
      }
      return `${controlName} is required.`;
    } else if (control?.hasError('email')) {
      return 'Invalid email address.';
    } else if (control?.hasError('pattern')) {
      return 'Password should contain at least 8 characters, including uppercase, lowercase, a number, and a symbol.';
    }

    return '';
  }


  signup() {
    this.form.markAllAsTouched();
    if (this.form.valid) {
      this.isLoading.next(true); 
      this.signupRequest.setStageName(this.form.get('stageName')?.value);
      this.signupRequest.setEmail(this.form.get('email')?.value);
      this.signupRequest.setPassword(this.form.get('password')?.value);

      this.auth.signup(this.signupRequest).subscribe({
        next: (response: any) => {
          console.log(response);
          this.isLoading.next(false); 
          this.router.navigateByUrl('regsucces');
          this.form.reset();
        },
        error: (error: HttpErrorResponse) => {
          this.isLoading.next(false); 
          this.notifier.notify('error', 'Registration Failed!');
          this.form.reset();
        }
      });  
    }
    // else{
    //   this.notifier.notify('error', 'Invalid Registration :(');
    // }
  }
}
