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
import { JwtResponse } from 'src/app/dtos/jwtresponse';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginRequest } from 'src/app/dtos/loginrequest';

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
  private loginRequest: LoginRequest;

  constructor(
    private auth: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.loginRequest = {
      phoneNumber: '',
      password: '',
    };
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      phoneNumber: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  login() {
    if (this.form.valid) {
      this.loginRequest.phoneNumber = this.form.get('phoneNumber')?.value;
      this.loginRequest.password = this.form.get('password')?.value;
      this.auth.login(this.loginRequest).subscribe({
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
