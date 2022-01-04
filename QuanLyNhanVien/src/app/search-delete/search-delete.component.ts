import { Component, OnInit } from '@angular/core';
import * as $ from "jquery";
import { MatDialog } from '@angular/material/dialog';
import { CanActivate, Route, Router } from '@angular/router';
import { SearchDeleteService } from '../services/search-delete.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LoginjwtService } from '../services/loginjwt.service';

@Component({
  selector: 'app-search-delete',
  templateUrl: './search-delete.component.html',
  styleUrls: ['./search-delete.component.css']
})
export class SearchDeleteComponent implements OnInit{

  users: any;
  str: any;
  token: any;
  role: any;
  constructor(private service: SearchDeleteService, private router: Router,private loginService:LoginjwtService) { }

  ngOnInit(): void {
    if(localStorage.getItem('token') != ''){
    let resp = this.service.getAllUsers();
    resp.subscribe((data) => this.users = data);
    this.token = localStorage.getItem('token');
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(this.token);
    this.role = decodedToken.role;
    }
    else{
      this.router.navigate(['login']);
    }
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

  public logout() {
    localStorage.clear();
    this.router.navigate(['login']);
  }

  public deleteUser(id: number) {
    var result = confirm("Delete?");
    if (result) {
      let resp = this.service.deleteUser(id);
      resp.subscribe((data) => this.users = data);
    }
  }
  public register() {
    this.router.navigate(['register']);
  }

}
