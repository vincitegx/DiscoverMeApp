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

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
})
export class SigninComponent implements OnInit {
  public form: FormGroup = new FormGroup({
    phoneNumber: new FormControl(''),
    password: new FormControl(''),
  });
  private signinRequest: SigninRequest;

  constructor(
    private auth: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.signinRequest = new SigninRequest('', '');
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  login() {
    if (this.form.valid) {
      this.signinRequest.setEmail(this.form.get('email')?.value);
      this.signinRequest.setPassword(this.form.get('password')?.value);
      this.auth.login(this.signinRequest).subscribe({
        next: (response: JwtResponse) => {
          console.log(response);
          this.router.navigateByUrl('home');
        },
        error: (error: HttpErrorResponse) => {
          alert(error.message);
        },
      });
    }
  }
}
