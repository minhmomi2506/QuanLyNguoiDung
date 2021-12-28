import { Component, OnInit } from '@angular/core';
import { LoginjwtService } from '../loginjwt.service';
import { Role } from '../roles';
import { Unit } from '../units';
import { User } from '../user';

@Component({
  selector: 'app-loginjwt',
  templateUrl: './loginjwt.component.html',
  styleUrls: ['./loginjwt.component.css']
})
export class LoginjwtComponent implements OnInit {

  msg: any;

  authRequest:any={
    username:"",
    password:""
  };

  // public usernameChange(){
  //   this.authRequest.username = $("#username").val();
  // }

  // public passwordChange(){
  //   this.authRequest.password = $("#password").val();
  // }

  constructor(private service: LoginjwtService) { }

  ngOnInit(): void {
    let resp = this.service.loginJWT(this.authRequest);
    resp.subscribe(data => this.msg = data);
    console.log(this.msg);
  }
}
