import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgIcon, provideIcons, provideNgIconsConfig } from '@ng-icons/core';
import { heroAtSymbol, heroKey } from '@ng-icons/heroicons/outline';
import type { LoginForm } from './login-form-type';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, NgIcon],
  providers: [
    provideIcons({ heroKey, heroAtSymbol }),
    provideNgIconsConfig({
      color: 'var(--color-primary)',
      size: '1.2em'
    })
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  #fb = inject(FormBuilder).nonNullable;

  loginForm: LoginForm = this.#fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required]]
  });

  login() {}
}
