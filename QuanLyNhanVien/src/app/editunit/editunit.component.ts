import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { data } from 'jquery';
import { EditUnitHistory } from '../editunithistory';
import { UnitServiceService } from '../services/unit-service.service';
import { Unit } from '../units';

@Component({
  selector: 'app-editunit',
  templateUrl: './editunit.component.html',
  styleUrls: ['./editunit.component.css']
})
export class EditunitComponent implements OnInit {

  unit: any;
  editUnitHistory: EditUnitHistory = new EditUnitHistory("","","");
  id: number = 0;
  msg: any;
  constructor(private service: UnitServiceService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get("id"));
    let resp = this.service.findUnitById(this.id);
    resp.subscribe((data) => {
      this.unit = data;
      $("#unitId").val(this.unit.unitId);
      $("#unitName").val(this.unit.unitName);
      $("#description").val(this.unit.description);
      this.editUnitHistory.unitIdEdit = this.unit.unitId;
      this.editUnitHistory.unitNameEdit = this.unit.unitName;
      this.editUnitHistory.unitDescriptionEdit = this.unit.description;
      console.log(this.editUnitHistory);
    });
  }

  public editUnit(){
    let resp = this.service.editUnit(this.editUnitHistory, this.id);
    resp.subscribe((data) =>{
      this.msg = data;
      this.ngOnInit();
    })
  }

}
