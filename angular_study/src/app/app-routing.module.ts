import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { AuthGuard } from './auth/auth.guard';
import { ChildComponentComponent } from './child-component/child-component.component';
import { FormComponent } from './form/form.component';
import { HomeComponent } from './home/home.component';
import { HttpStudyComponent } from './http-study/http-study.component';
import { ReactiveFormComponent } from './reactive-form/reactive-form.component';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: "home", component: HomeComponent, canActivate: [AuthGuard] },
  { path: "about", component: AboutComponent, canActivate: [AuthGuard] },
  { path: "form", component: FormComponent, canActivate: [AuthGuard] },
  { path: "reactiveForm", component: ReactiveFormComponent, canActivate: [AuthGuard] },
  { path: "httpStudy", component: HttpStudyComponent, canActivate: [AuthGuard] },
  { path: "child", component: ChildComponentComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
