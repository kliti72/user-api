import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FrattaleLoadingComponent } from './frattale-loading.component';

describe('FrattaleLoadingComponent', () => {
  let component: FrattaleLoadingComponent;
  let fixture: ComponentFixture<FrattaleLoadingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FrattaleLoadingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FrattaleLoadingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
