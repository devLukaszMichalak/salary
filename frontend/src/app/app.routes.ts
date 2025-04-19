import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { canActivateProtectedGuard } from './auth/can-activate-protected.guard';
import { alreadyLoggedInGuard } from './auth/already-logged-in.guard';

export const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [canActivateProtectedGuard]
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [alreadyLoggedInGuard]
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [alreadyLoggedInGuard]
  },
  { path: '**', redirectTo: 'dashboard', pathMatch: 'full' }
];
