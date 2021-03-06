import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { data } from 'jquery';
import { UnitServiceService } from '../services/unit-service.service';
import { Unit } from '../units';

@Component({
  selector: 'app-unit',
  templateUrl: './unit.component.html',
  styleUrls: ['./unit.component.css']
})
export class UnitComponent implements OnInit {
  unit1: Unit = new Unit(0, "", "", "");
  unit: Unit = new Unit(0, "", "", "");
  fatherUnitEdit: Unit = new Unit(0, "", "", "");
  unit2: any;
  units: any;
  units2: any;
  str: any;
  msg: any;
  id: number = 0;

  constructor(private service: UnitServiceService, private dialog: MatDialog, private router: Router) {

  }

  ngOnInit(): void {
    if (localStorage.getItem('token') != null) {
      let resp = this.service.getAllUnits();
      resp.subscribe((data) => {
        this.units = data;
      });
    }
    else {
      this.router.navigate(['login']);
    }
  }

  public unitIdChange() {
    this.unit1.id = Number($("#userUnitId").val());
  }

  public addUnit() {
    let resp = this.service.addUnit(this.unit, this.unit1.id);
    resp.subscribe((data) => {
      this.msg = data;
      this.ngOnInit();
    })
  }

  public findUnitById(id: number) {
    this.id = id;
    let resp = this.service.findUnitById(this.id);
    resp.subscribe((data) => {
      this.unit2 = data;
      $("#unitIdEdit" + this.id).val(this.unit2.unitId);
      $("#unitNameEdit" + this.id).val(this.unit2.unitName);
      $("#unitDescriptionEdit" + this.id).val(this.unit2.description);
      // this.fatherUnit.id = this.unit2.fatherUnit.id;
      this.fatherUnitEdit.id = this.unit2.fatherUnit?.id;
    });
    let resp1 = this.service.getAllExcept1(this.id);
    resp1.subscribe((data) => {
      this.units2 = data;
    });
  }

  public fatherUnitChange(){
    this.fatherUnitEdit.id = Number($("#unitFatherUnit"+this.id).val());
  }

  public editUnit() {
    let resp = this.service.editUnit(this.unit2, this.id, this.fatherUnitEdit.id);
    resp.subscribe((data)=>{
      this.msg = data;
      console.log(this.msg);
      this.ngOnInit();
    })
  }

  public deleteUnit(id: number) {
    var result = confirm("Delete?");
    if (result) {
      let resp = this.service.deleteUnit(id);
      resp.subscribe((data) => this.units = data);
    }
  }

}
