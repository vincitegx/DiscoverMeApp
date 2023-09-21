import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoverComponent } from './components/cover/cover.component';
import { HomeComponent } from './components/home/home.component';
import { DefaultComponent } from './layouts/default/default.component';

const routes: Routes = [
  {
    path:'',
    component:DefaultComponent,
    children: [
       {
         path:'',
         component:CoverComponent,
       },
       {
         path:'home',
         component:HomeComponent,
       }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
