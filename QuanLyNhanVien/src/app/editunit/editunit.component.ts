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
  units: any;
  // fatherUnit: Unit = new Unit(0,"","","");
  editUnitHistory: EditUnitHistory = new EditUnitHistory("", "", "", "");
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
    });
    let resp1 = this.service.getAllExcept1(this.id);
    resp1.subscribe((data) => {
      this.units = data;
    });
  }

  // public editUnit() {
  //   let resp = this.service.editUnit(this.editUnitHistory, this.id);
  //   resp.subscribe((data) =>{
  //     this.msg = data;
  //     this.ngOnInit();
  //   })
  //   // console.log(this.fatherUnitName);
  //   // console.log(JSON.stringify(this.editUnit));
  // }

  public unitIdChange() {
    this.editUnitHistory.unitFather = $("#userUnitId option:selected").text();
  }

}
