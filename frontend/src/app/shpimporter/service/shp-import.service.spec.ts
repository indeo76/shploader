import { TestBed } from '@angular/core/testing';

import { ShpImportService } from './shp-import.service';

describe('ShpImportService', () => {
  let service: ShpImportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShpImportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
