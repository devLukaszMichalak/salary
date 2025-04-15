import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import {
  NgIcon,
  NgIconStack,
  provideIcons,
  provideNgIconsConfig
} from '@ng-icons/core';
import {
  heroArrowUturnRight,
  heroAtSymbol,
  heroKey
} from '@ng-icons/heroicons/outline';
import { AuthService } from '../auth.service';
import type { RegisterForm } from './register-form-type';
import { RegisterValidators } from './register-validators';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, NgIcon, NgIconStack],
  providers: [
    provideIcons({ heroKey, heroAtSymbol, heroArrowUturnRight }),
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
  #authService = inject(AuthService);

  registerForm: RegisterForm = this.#fb.group(
    {
      email: [
        '',
        [Validators.required, Validators.email],
        [RegisterValidators.emailTakenValidator(this.#authService)]
      ],
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
