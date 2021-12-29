import { TestBed } from '@angular/core/testing';

import { SearchDeleteService } from './search-delete.service';

describe('SearchDeleteService', () => {
  let service: SearchDeleteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchDeleteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
