import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import {MatDialogModule} from '@angular/material/dialog';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchDeleteComponent } from './search-delete/search-delete.component';
import { RegisterComponent } from './register/register.component';
import { UnitComponent } from './unit/unit.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { EdituserComponent } from './edituser/edituser.component';
import { EditunitComponent } from './editunit/editunit.component';
import { LoginjwtComponent } from './loginjwt/loginjwt.component';
import { RegisterService } from './services/register.service';
import { SearchDeleteService } from './services/search-delete.service';
@NgModule({
  declarations: [
    AppComponent,
    SearchDeleteComponent,
    RegisterComponent,
    UnitComponent,
    EdituserComponent,
    EditunitComponent,
    LoginjwtComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatDialogModule,
    NgbModule,
  ],
  providers: [RegisterService,SearchDeleteService],
  bootstrap: [AppComponent]
})
export class AppModule { }
