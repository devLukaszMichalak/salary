import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { DialogService } from '@ngneat/dialog';
import { catchError, EMPTY } from 'rxjs';
import { ErrorDialogComponent } from './error-dialog/error-dialog.component';
import type { ErrorResponse } from './error-response';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const dialogService = inject(DialogService);

  return next(req).pipe(
    catchError((error: unknown) => {
      dialogService.open(ErrorDialogComponent, {
        data: {
          error: (error as HttpErrorResponse).error as ErrorResponse
        }
      });

      return EMPTY;
    })
  );
};
