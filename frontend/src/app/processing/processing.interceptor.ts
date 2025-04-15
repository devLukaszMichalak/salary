import {
  type HttpEvent,
  HttpInterceptorFn,
  HttpRequest
} from '@angular/common/http';
import { inject } from '@angular/core';
import { HotToastService } from '@ngxpert/hot-toast';
import { Observable, tap } from 'rxjs';

export const processingInterceptor: HttpInterceptorFn = (
  request,
  next
): Observable<HttpEvent<unknown>> => {
  const toastService = inject(HotToastService);

  if (!shouldIntercept(request)) {
    return next(request);
  }

  const processingToast = toastService.loading('Processing...', {
    iconTheme: {
      primary: 'var(--color-primary)',
      secondary: 'var(--color-primary-content)'
    }
  });

  return next(request).pipe(
    tap({
      error: () => {
        processingToast.close();
        toastService.error('Error!', {
          iconTheme: {
            primary: 'var(--color-error)',
            secondary: 'var(--color-error-content)'
          }
        });
      },
      complete: () => {
        processingToast.close();
        toastService.success('Success!', {
          iconTheme: {
            primary: 'var(--color-success)',
            secondary: 'var(--color-success-content)'
          }
        });
      }
    })
  );
};

function shouldIntercept(request: HttpRequest<unknown>): boolean {
  return 'GET' !== request.method;
}
