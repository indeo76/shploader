import { TestBed } from '@angular/core/testing';

import { ShpAnalizerService } from './shp-analizer.service';

describe('ShpAnalizerService', () => {
  let service: ShpAnalizerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShpAnalizerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
