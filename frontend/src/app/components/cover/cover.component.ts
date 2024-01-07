import { Component,OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-cover',
  templateUrl: './cover.component.html',
  styleUrls: ['./cover.component.css']
})
export class CoverComponent implements OnInit{

    constructor(public auth: AuthService){
      }

      ngOnInit(): void{}

      loginWithRedirect():void{
          this.auth.disconnectFacebook();
        }

}
