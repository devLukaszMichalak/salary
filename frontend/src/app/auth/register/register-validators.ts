import {
  AbstractControl,
  type ValidationErrors,
  type ValidatorFn
} from '@angular/forms';

export class RegisterValidators {
  static hasUpperCaseLetterValidator: ValidatorFn = (
    control: AbstractControl
  ): ValidationErrors | null =>
    /[A-Z]/.test(control.value) ? null : { missingUpperCase: true };

  static hasLowerCaseLetterValidator: ValidatorFn = (
    control: AbstractControl
  ): ValidationErrors | null =>
    /[a-z]/.test(control.value) ? null : { missingLowerCase: true };

  static hasNumberValidator: ValidatorFn = (
    control: AbstractControl
  ): ValidationErrors | null =>
    /[0-9]/.test(control.value) ? null : { missingNumber: true };

  static passwordMatchValidator =
    (
      passwordControlName: string,
      repeatPasswordControlName: string
    ): ValidatorFn =>
    (group: AbstractControl): ValidationErrors | null => {
      const password = group.get(passwordControlName)?.value;
      const repeatPassword = group.get(repeatPasswordControlName)?.value;
      return password === repeatPassword ? null : { passwordMismatch: true };
    };
}
