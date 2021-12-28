import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { data } from 'jquery';
import { EditUserHistory } from '../edituserhistory';
import { SearchDeleteService } from '../search-delete.service';
import { User } from '../user';

@Component({
  selector: 'app-edituser',
  templateUrl: './edituser.component.html',
  styleUrls: ['./edituser.component.css']
})
export class EdituserComponent implements OnInit {
  public date = new Date();
  editUserHistory: EditUserHistory = new EditUserHistory("", "", "",this.date);
  id: number = 0;
  user: any;
  msg: any;
  constructor(private service: SearchDeleteService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get("id"));
    let resp = this.service.findUserById(this.id);
    resp.subscribe((data) => {
      this.user = data;
      $("#fullName").val(this.user.fullName);
      $("#description").val(this.user.description);
      $("#dateOfBirth").val(this.user.dateOfBirth);
      $("#address").val(this.user.address);
      this.editUserHistory.userFullNameEdit = this.user.fullName;
      this.editUserHistory.userDescriptionEdit = this.user.description;
      this.editUserHistory.userAddressEdit = this.user.address;
      this.editUserHistory.userDateOfBirthEdit = this.user.dateOfBirth;
    });
  }


  public editUser() {
    let resp = this.service.editUser(this.editUserHistory, this.id);
    resp.subscribe((data) => {
      this.msg = data;
      this.ngOnInit();
    });
  }


}
