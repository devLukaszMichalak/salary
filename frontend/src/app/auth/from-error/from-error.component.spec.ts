import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FromErrorComponent } from './from-error.component';

describe('FromErrorComponent', () => {
  let component: FromErrorComponent;
  let fixture: ComponentFixture<FromErrorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FromErrorComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(FromErrorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
