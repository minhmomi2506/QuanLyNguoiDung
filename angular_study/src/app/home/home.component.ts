import { Component, OnInit } from '@angular/core';
import { CommonService } from '../services/common.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public name: string = 'Minh';
  public age: number = 18;
  public vehicles: Array<string> = ['Toyota', 'Honda'];
  constructor(private commonService: CommonService) { 
    this.age = this.commonService.age;
  }

  ngOnInit(): void {
  }

  public increaseAge() {
    this.commonService.tangTuoi();
    this.age = this.commonService.age;
  }

}
