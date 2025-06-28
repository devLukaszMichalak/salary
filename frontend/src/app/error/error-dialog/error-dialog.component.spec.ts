import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogRef } from '@ngneat/dialog';
import type { ErrorResponse } from '../error-response';
import { ErrorDialogComponent } from './error-dialog.component';

describe('ErrorDialogComponent', () => {
  let component: ErrorDialogComponent;
  let fixture: ComponentFixture<ErrorDialogComponent>;

  const mockErrorResponse: ErrorResponse = {
    status: 500,
    title: 'Test error',
    message: 'This is a test error message'
  };
  const dialogMock = jasmine.createSpyObj('DialogRef', ['close'], {
    data: { error: mockErrorResponse }
  });

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ErrorDialogComponent],
      providers: [{ provide: DialogRef, useValue: dialogMock }]
    }).compileComponents();

    fixture = TestBed.createComponent(ErrorDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
