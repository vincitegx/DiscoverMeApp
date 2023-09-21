import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DefaultComponent } from './default.component';
import { CoverComponent } from '../../components/cover/cover.component';
import { HomeComponent } from '../../components/home/home.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from 'src/app/components/shared/shared.module';


@NgModule({
  declarations: [
    DefaultComponent,
    CoverComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    HttpClientModule,
    SharedModule
  ]
})
export class DefaultModule { }
