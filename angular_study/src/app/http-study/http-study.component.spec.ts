import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HttpStudyComponent } from './http-study.component';

describe('HttpStudyComponent', () => {
  let component: HttpStudyComponent;
  let fixture: ComponentFixture<HttpStudyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HttpStudyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HttpStudyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
