import { FormControl, FormGroup } from '@angular/forms';
import { RegisterValidators } from './register-validators';

describe('RegisterValidators', () => {
  describe('hasUpperCaseLetterValidator', () => {
    it('should pass if value contains an uppercase letter', () => {
      const control = new FormControl('Password123');
      const result = RegisterValidators.hasUpperCaseLetterValidator(control);
      expect(result).toBeNull();
    });

    it('should fail if value has no uppercase letter', () => {
      const control = new FormControl('password123');
      const result = RegisterValidators.hasUpperCaseLetterValidator(control);
      expect(result).toEqual({ missingUpperCase: true });
    });
  });

  describe('hasLowerCaseLetterValidator', () => {
    it('should pass if value contains a lowercase letter', () => {
      const control = new FormControl('Password123');
      const result = RegisterValidators.hasLowerCaseLetterValidator(control);
      expect(result).toBeNull();
    });

    it('should fail if value has no lowercase letter', () => {
      const control = new FormControl('PASSWORD123');
      const result = RegisterValidators.hasLowerCaseLetterValidator(control);
      expect(result).toEqual({ missingLowerCase: true });
    });
  });

  describe('hasNumberValidator', () => {
    it('should pass if value contains a number', () => {
      const control = new FormControl('Password123');
      const result = RegisterValidators.hasNumberValidator(control);
      expect(result).toBeNull();
    });

    it('should fail if value has no number', () => {
      const control = new FormControl('Password');
      const result = RegisterValidators.hasNumberValidator(control);
      expect(result).toEqual({ missingNumber: true });
    });
  });

  describe('passwordMatchValidator', () => {
    const validatorFn = RegisterValidators.passwordMatchValidator(
      'password',
      'repeatPassword'
    );

    it('should pass if passwords match', () => {
      const form = new FormGroup({
        password: new FormControl('MySecurePass123'),
        repeatPassword: new FormControl('MySecurePass123')
      });
      const result = validatorFn(form);
      expect(result).toBeNull();
    });

    it('should fail if passwords do not match', () => {
      const form = new FormGroup({
        password: new FormControl('MySecurePass123'),
        repeatPassword: new FormControl('WrongPass123')
      });
      const result = validatorFn(form);
      expect(result).toEqual({ passwordMismatch: true });
    });
  });
});
