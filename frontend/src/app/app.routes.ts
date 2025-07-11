import { Routes } from '@angular/router';
import { alreadyLoggedInGuard } from './auth/already-logged-in.guard';
import { canActivateProtectedGuard } from './auth/can-activate-protected.guard';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { EmployeeBrowserComponent } from './browser/employee-browser.component';
import { DashboardComponent } from './dashboard/dashboard.component';

export const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [canActivateProtectedGuard],
    children: [
      {
        path: 'browser',
        component: EmployeeBrowserComponent
      }
    ]
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
