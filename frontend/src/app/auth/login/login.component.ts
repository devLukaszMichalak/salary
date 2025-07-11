import { AsyncPipe } from '@angular/common';
import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { NgIcon, provideIcons, provideNgIconsConfig } from '@ng-icons/core';
import { heroAtSymbol, heroKey } from '@ng-icons/heroicons/outline';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from '../auth.service';
import { FromErrorComponent } from '../from-error/from-error.component';
import type { LoginForm } from './login-form-type';

@Component({
  selector: 'app-login',
  imports: [
    ReactiveFormsModule,
    NgIcon,
    AsyncPipe,
    RouterLink,
    FromErrorComponent
  ],
  providers: [
    provideIcons({ heroKey, heroAtSymbol }),
    provideNgIconsConfig({
      color: 'var(--color-primary)',
      size: '1.2em'
    })
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class LoginComponent {
  #fb = inject(FormBuilder).nonNullable;
  #authService = inject(AuthService);
  #router = inject(Router);

  loading$ = new BehaviorSubject<boolean>(false);

  loginForm: LoginForm = this.#fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required]]
  });

  login() {
    this.loading$.next(true);

    this.#authService
      .login(this.loginForm.getRawValue())
      .subscribe(() => void this.#router.navigate(['dashboard']))
      .add(() => this.loading$.next(false));
  }
}
