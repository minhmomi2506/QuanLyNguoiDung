import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommonService {
  public age: number = 10;
  constructor() { }
  public tangTuoi() {
    this.age++;
  }
}
