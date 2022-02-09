import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-child-component',
  templateUrl: './child-component.component.html',
  styleUrls: ['./child-component.component.css']
})
export class ChildComponentComponent implements OnInit {

  @Input() allPosts: any;
  constructor() { }

  ngOnInit(): void {
    console.log("allPosts:" + this.allPosts);
  }

}
