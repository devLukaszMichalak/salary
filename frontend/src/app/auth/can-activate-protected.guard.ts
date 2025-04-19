import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { map } from 'rxjs';
import { AuthService } from './auth.service';

export const canActivateProtectedGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const unauthenticatedRedirect = router.parseUrl('/login');

  return authService.isTokenValid$.pipe(
    map(isValid => {
      if (!isValid) {
        authService.resetToken();
        return unauthenticatedRedirect;
      }

      return true;
    })
  );
};
