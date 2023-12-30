import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-google-btn',
  templateUrl: './google-btn.component.html',
  styleUrls: ['./google-btn.component.css']
})
export class GoogleBtnComponent implements OnInit{
  url: string = "";

  constructor(private auth: AuthService) {  }

  ngOnInit(): void {
    this.auth.get("auth/url").subscribe((data: any) => {this.url = data.authURL});
  }
}
