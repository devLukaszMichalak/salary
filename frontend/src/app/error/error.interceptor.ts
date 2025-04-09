import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { DialogService } from '@ngneat/dialog';
import { catchError, EMPTY } from 'rxjs';
import { ErrorDialogComponent } from './error-dialog/error-dialog.component';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  const dialogService = inject(DialogService);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      dialogService.open(ErrorDialogComponent, {
        data: {
          error
        }
      });

      return EMPTY;
    })
  );
};
