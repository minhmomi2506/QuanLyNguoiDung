import { Component, OnInit } from '@angular/core';
import { Role } from '../roles';
import { User } from '../user';
import * as $ from "jquery";
import { Unit } from '../units';
import { RegisterService } from '../services/register.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public date = new Date();
  role: Role = new Role(0,"");
  unit: Unit = new Unit(0,"","","");
  user: User = new User("", "", "", this.date, "", "",this.unit);
  roles: any;
  message: any;
  roleId: number = 1;
  unitId: number = 1;
  units: any;
  constructor(private service: RegisterService) { }

  ngOnInit(): void {
    // let resp = this.service.getAllRoles();
    // resp.subscribe((data) => this.roles = data);
    let resp = this.service.getAllUnits();
    resp.subscribe((data) => this.units = data);
    // $("#userRoleId").prop("selectedIndex", -1);
  }
  // public roleIdChange() {
  //   this.role.id = Number($("#userRoleId").val());
  //   if(this.role.id == 0){
  //   }
  // }

  public unitIdChange() {
    this.unit.id = Number($("#userUnitId").val());
    // alert(this.unitId);
  }

  public registerNow() {
    let resp = this.service.doRegistration(this.user);
    resp.subscribe((data) => this.message = data);
  }
}
