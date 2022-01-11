import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditunitComponent } from './editunit/editunit.component';
import { LoginjwtComponent } from './loginjwt/loginjwt.component';
import { RegisterComponent } from './register/register.component';
import { SearchDeleteComponent } from './search-delete/search-delete.component';
import { AuthGuard } from './shared/auth.guard';
import { RoleGuard } from './shared/role.guard';
import { UnitComponent } from './unit/unit.component';

const routes: Routes = [
  { path: "", redirectTo: "user", pathMatch: "full" },
  { path: "user", component: SearchDeleteComponent, canActivate: [AuthGuard] },
  { path: "register", component: RegisterComponent, canActivate: [AuthGuard, RoleGuard] },
  { path: "editUnit/:id", component: EditunitComponent, canActivate: [AuthGuard, RoleGuard] },
  { path: "unit", component: UnitComponent, canActivate: [AuthGuard, RoleGuard] },
  { path: "login", component: LoginjwtComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
