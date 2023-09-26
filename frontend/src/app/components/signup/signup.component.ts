import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtResponse } from 'src/app/dtos/jwtresponse';
import { LoginRequest } from 'src/app/dtos/loginrequest';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
      
  public form: FormGroup = new FormGroup({
    phoneNumber: new FormControl(''),
    password: new FormControl('')
  });
  private loginRequest: LoginRequest;

    constructor(private auth: AuthService, private router: Router, private formBuilder: FormBuilder){
      this.loginRequest = {
        "phoneNumber": "",
        "password": ""
      };
    }

    ngOnInit(): void{
      this.form = this.formBuilder.group({
        phoneNumber: ['', Validators.required],
        password: ['', Validators.required]
    });
    }

    login(){
      if(this.form.valid){
        this.loginRequest.phoneNumber = this.form.get('phoneNumber')?.value;
        this.loginRequest.password = this.form.get('password')?.value;
        this.auth.login(this.loginRequest).subscribe({
          next: (response: JwtResponse) =>{
            console.log(response);
            this.router.navigateByUrl('home'); 
          },
          error: (error: HttpErrorResponse) =>{
            alert(error.message);
          }
        });
      }
    }

    get f(): { [key: string]: AbstractControl } {
      return this.form.controls;
    }

}
