import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { SignupRequest } from './signup-request';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  public form: FormGroup = new FormGroup({
    stageName: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl('')
  });
  private signupRequest: SignupRequest;


  constructor(private auth: AuthService, private router: Router, private formBuilder: FormBuilder) {
    this.signupRequest = new SignupRequest('', '', '');
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      stageName: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  signup() {
    if (this.form.valid) {
      this.signupRequest.setStageName(this.form.get('stageName')?.value);
      this.signupRequest.setEmail(this.form.get('email')?.value);
      this.signupRequest.setPassword(this.form.get('password')?.value);

      this.auth.signup(this.signupRequest).subscribe({
        next: (response: any) => {
          console.log(response);
          this.router.navigateByUrl('regsucces');
          this.form.reset();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message);
          this.form.reset();
        }
      });
    }
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

}
