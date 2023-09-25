import { Component ,  OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  @Input() currentPage: number = 1;
    @Input() totalPages: number = 10;

    constructor() {}

    ngOnInit(): void {}

    onPageChange(page: number): void {
      console.log(`Page changed to ${page}`);
    }
}
