import { Component, OnInit } from '@angular/core';
import * as $ from "jquery";
import { MatDialog } from '@angular/material/dialog';
import { CanActivate, Route, Router } from '@angular/router';
import { SearchDeleteService } from '../services/search-delete.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LoginjwtService } from '../services/loginjwt.service';
import { data } from 'jquery';
import { NumberFormatStyle } from '@angular/common';
import { EditUnitHistory } from '../editunithistory';
import { Unit } from '../units';
import { EditUserHistory } from '../edituserhistory';
import { User } from '../user';

@Component({
  selector: 'app-search-delete',
  templateUrl: './search-delete.component.html',
  styleUrls: ['./search-delete.component.css']
})
export class SearchDeleteComponent implements OnInit {

  users: any;
  str: any;
  token: any;
  roles: any = [];
  units: any;
  id: number = 0;
  msg: any;
  public date = new Date();
  unit1: Unit = new Unit(0, "", "", "");
  user1: User = new User("", "", "", this.date, "", "", this.unit1);
  user2: any;
  constructor(private service: SearchDeleteService, private router: Router) { }

  ngOnInit(): void {
    let resp = this.service.getAllUsers();
    resp.subscribe((data) => {
      this.users = data;
    });
    this.token = localStorage.getItem('token');
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(this.token);
    localStorage.setItem('roles', JSON.stringify(decodedToken.roles));
    this.roles = JSON.parse(localStorage.getItem('roles') || '{}');
  }

  public logout() {
    localStorage.clear();
    this.router.navigate(['login']);
  }

  public register() {
    this.router.navigate(['register']);
  }

  public unit() {
    this.router.navigate(['unit']);
  }

  public getRoles() {
    for (var i = 0, len = this.roles.length; i < len; i++) {
      if (this.roles[i].roleName === 'ROLE_ADMIN') {
        return true;
      }
    }
    return false;
  }

  public findUserByStr() {
    if (this.str != '') {
      let resp = this.service.getAllUsersByStr(this.str);
      resp.subscribe((data) => this.users = data);
    }
    else {
      this.ngOnInit();
    }
  }

  public findUserById(id: number) {
    this.id = id;
    let resp1 = this.service.getAllExcept1(this.id);
    resp1.subscribe((data) => {
      this.units = data;
    });
    let resp2 = this.service.findUserById(this.id);
    resp2.subscribe((data) => {
      this.user2 = data;
      $("#idEdit"+this.id).val(this.user2.id);
      $("#fullNameEdit"+this.id).val(this.user2.fullName);
      $("#descriptionEdit"+this.id).val(this.user2.description);
      $("#dateOfBirthEdit"+this.id).val(this.user2.dateOfBirth);
      $("#addressEdit"+this.id).val(this.user2.address);
      this.user1.fullName = this.user2.fullName;
      this.user1.description = this.user2.description;
      this.user1.dateOfBirth = this.user2.dateOfBirth;
      this.user1.address = this.user2.address;
      this.unit1.id = this.user2.unit.id;
      this.unit1.unitName = this.user2.unit.unitName;
    })
  }

  public editUser() {
    let resp = this.service.editUser(this.user1, this.id);
    resp.subscribe((data) => {
      this.msg = data;
      console.log(this.msg);
      this.ngOnInit();
    });
  }

  public deleteUser(id: number) {
    var result = confirm("Delete?");
    if (result) {
      let resp = this.service.deleteUser(id);
      resp.subscribe((data) => this.users = data);
    }
  }

  public unitChange() {
    this.unit1.id = Number($("#userUnit" + this.id).val());
    console.log(this.unit1.id);
    this.unit1.unitName = $("#userUnit" + this.id + " option:selected").text();
    console.log(this.unit1.unitName);
  }

}
