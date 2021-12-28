import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { data } from 'jquery';
import { UnitServiceService } from '../unit-service.service';
import { Unit } from '../units';

@Component({
  selector: 'app-unit',
  templateUrl: './unit.component.html',
  styleUrls: ['./unit.component.css']
})
export class UnitComponent implements OnInit {
  unit1: Unit = new Unit(0, "", "", "");
  unit: Unit = new Unit(0, "", "", "");
  units: any;
  str: any;
  msg: any;

  constructor(private service: UnitServiceService, private dialog: MatDialog) {

  }

  ngOnInit(): void {
    let resp = this.service.getAllUnits();
    resp.subscribe((data) => {
      this.units = data;
    })

  }

  public unitIdChange() {
    this.unit1.id = Number($("#userUnitId").val());
    // alert(this.unitId);
  }

  public addUnit() {
    let resp = this.service.addUnit(this.unit,this.unit1.id);
    resp.subscribe((data) => {
      this.msg = data;
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
