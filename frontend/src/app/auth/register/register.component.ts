import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import type { RegisterForm } from './register-form-type';
import { RegisterValidators } from './register-validators';

@Component({
  selector: 'app-register',
  imports: [],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  #fb = inject(FormBuilder).nonNullable;

  registerForm: FormGroup<RegisterForm> = this.#fb.group(
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
}
