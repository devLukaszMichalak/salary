import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgIcon, provideIcons, provideNgIconsConfig } from '@ng-icons/core';
import { heroAtSymbol, heroKey } from '@ng-icons/heroicons/outline';
import type { RegisterForm } from './register-form-type';
import { RegisterValidators } from './register-validators';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, NgIcon],
  providers: [
    provideIcons({ heroKey, heroAtSymbol }),
    provideNgIconsConfig({
      color: 'var(--color-primary)',
      size: '1.2em'
    })
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  #fb = inject(FormBuilder).nonNullable;

  registerForm: RegisterForm = this.#fb.group(
    {
      email: ['', [Validators.required, Validators.email]],
      password: [
        '',
        [
          Validators.required,
          Validators.minLength(8),
          RegisterValidators.hasUpperCaseLetterValidator,
          RegisterValidators.hasLowerCaseLetterValidator,
          RegisterValidators.hasNumberValidator
        ]
      ],
      repeatPassword: ['', [Validators.required]]
    },
    {
      validators: [
        RegisterValidators.passwordMatchValidator('password', 'repeatPassword')
      ]
    }
  );

  register() {}
}
