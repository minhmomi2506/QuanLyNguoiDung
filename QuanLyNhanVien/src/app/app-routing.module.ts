import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditunitComponent } from './editunit/editunit.component';
import { EdituserComponent } from './edituser/edituser.component';
import { LoginjwtComponent } from './loginjwt/loginjwt.component';
import { RegisterComponent } from './register/register.component';
import { SearchDeleteComponent } from './search-delete/search-delete.component';
import { UnitComponent } from './unit/unit.component';

const routes: Routes = [
  {path:"",redirectTo:"user",pathMatch:"full"},
  {path:"user",component:SearchDeleteComponent},
  {path:"register",component:RegisterComponent},
  {path:"editUserInfo/:id",component:EdituserComponent},
  {path:"editUnit/:id",component:EditunitComponent},
  {path:"unit",component:UnitComponent},
  {path:"login",component:LoginjwtComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
