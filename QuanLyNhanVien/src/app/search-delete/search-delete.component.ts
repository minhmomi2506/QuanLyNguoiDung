import { Component, OnInit } from '@angular/core';
import { SearchDeleteService } from '../search-delete.service';
import * as $ from "jquery";
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-delete',
  templateUrl: './search-delete.component.html',
  styleUrls: ['./search-delete.component.css']
})
export class SearchDeleteComponent implements OnInit {

  users: any;
  str: any;


  constructor(private service: SearchDeleteService, private router: Router) { }

  ngOnInit(): void {
    let resp = this.service.getAllUsers();
    resp.subscribe((data) => this.users = data);
  }

  public findUserByStr() {
    if(this.str != ''){
      let resp = this.service.getAllUsersByStr(this.str);
      resp.subscribe((data) => this.users = data);
    }
    else{
      this.ngOnInit();
    }
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

  public editUser(userId: any){
    this.router.navigate(["editUserInfo"], userId);
  }

}
