import { provideHttpClient } from '@angular/common/http';
import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideDialogConfig } from '@ngneat/dialog';
import { routes } from './app.routes';
import { provideTheme } from './theme/theme.provider';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(),
    provideTheme('light'),
    provideDialogConfig({ enableClose: false, closeButton: true })
  ]
};
