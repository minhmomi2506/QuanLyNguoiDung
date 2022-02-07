import { Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[appBold]'
})
export class BoldDirective {

  constructor(private elementRef: ElementRef) { 
    elementRef.nativeElement.style.color = 'yellow';
    elementRef.nativeElement.style.background = 'green';
  }

}
