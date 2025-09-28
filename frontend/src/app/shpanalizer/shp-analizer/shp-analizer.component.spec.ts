import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShpAnalizerComponent } from './shp-analizer.component';

describe('ShpAnalizerComponent', () => {
  let component: ShpAnalizerComponent;
  let fixture: ComponentFixture<ShpAnalizerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShpAnalizerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShpAnalizerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
