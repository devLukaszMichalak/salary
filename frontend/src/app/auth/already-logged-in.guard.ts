import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { map } from 'rxjs';
import { AuthService } from './auth.service';

export const alreadyLoggedInGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const authenticatedRedirect = router.parseUrl('/dashboard');

  return authService.isTokenValid$.pipe(
    map(isValid => {
      if (isValid) {
        return authenticatedRedirect;
      }

      return true;
    })
  );
};
