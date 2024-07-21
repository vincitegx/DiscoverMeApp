import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { SharemodalComponent } from './sharemodal/sharemodal.component';



@NgModule({
  declarations: [
      HeaderComponent,
      SharemodalComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    HeaderComponent,
    SharemodalComponent
  ]
})
export class SharedModule { }
