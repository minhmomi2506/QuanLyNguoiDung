import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { data } from 'jquery';
import { Role } from '../roles';
import { LoginjwtService } from '../services/loginjwt.service';
import { Unit } from '../units';
import { User } from '../user';

@Component({
  selector: 'app-loginjwt',
  templateUrl: './loginjwt.component.html',
  styleUrls: ['./loginjwt.component.css']
})
export class LoginjwtComponent implements OnInit {

  token: any;
  msg: any;
  role: string = "";

  public date = new Date();
  // role: Role = new Role(0,"");
  unit: Unit = new Unit(0, "", "", "");
  user: User = new User("", "", "", this.date, "", "", this.unit);

  // authRequest:any={
  //   username:"",
  //   password:""
  // };

  // public usernameChange(){
  //   this.user.username = $("#username").val();
  // }

  // public passwordChange(){
  //   this.user.password = $("#password").val();
  // }

  constructor(private service: LoginjwtService, private router: Router) { }

  ngOnInit(): void {
    if (localStorage.getItem('token') == '') {
      this.getToken();
    }
    else{
      this.router.navigate(['user']);
    }
  }

  public getToken() {
    let resp = this.service.loginJWT(this.user);
    resp.subscribe((data) => {
      this.token = data;
      localStorage.setItem('token', this.token);
      if (this.token != "") {
        this.router.navigate(['user']);
      }
    });
  }
}
