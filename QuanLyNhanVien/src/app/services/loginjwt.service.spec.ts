import { TestBed } from '@angular/core/testing';

import { LoginjwtService } from './loginjwt.service';

describe('LoginjwtService', () => {
  let service: LoginjwtService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoginjwtService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
