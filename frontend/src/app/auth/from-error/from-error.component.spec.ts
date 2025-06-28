import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FromErrorComponent } from './from-error.component';

describe('FromErrorComponent', () => {
  let component: FromErrorComponent;
  let fixture: ComponentFixture<FromErrorComponent>;

  const mockControl = jasmine.createSpyObj('FromControl', ['hasError']);

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FromErrorComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(FromErrorComponent);
    const componentRef = fixture.componentRef;
    componentRef.setInput('control', mockControl);
    componentRef.setInput('errorCode', 'testError');
    componentRef.setInput('message', 'This is a test error message');
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
