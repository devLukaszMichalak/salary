import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideDialogConfig } from '@ngneat/dialog';
import { provideHotToastConfig } from '@ngxpert/hot-toast';
import { routes } from './app.routes';
import { authInterceptor } from './auth/auth.interceptor';
import { errorInterceptor } from './error/error.interceptor';
import { processingInterceptor } from './processing/processing.interceptor';
import { provideTheme } from './theme/theme.provider';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHotToastConfig({
      dismissible: false,
      style: {
        backgroundColor: 'var(--color-base-100)',
        color: 'var(--color-base-content)',
        borderRadius: 'var(--radius-field)'
      },
      position: 'bottom-center'
    }),
    provideHttpClient(
      withInterceptors([
        errorInterceptor,
        processingInterceptor,
        authInterceptor
      ])
    ),
    provideTheme('light'),
    provideDialogConfig({ enableClose: false })
  ]
};
