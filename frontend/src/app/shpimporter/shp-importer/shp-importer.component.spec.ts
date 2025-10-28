import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShpImporterComponent } from './shp-importer.component';

describe('ShpImporterComponent', () => {
  let component: ShpImporterComponent;
  let fixture: ComponentFixture<ShpImporterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShpImporterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShpImporterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
