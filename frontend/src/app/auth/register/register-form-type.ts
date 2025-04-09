import { FormControl } from '@angular/forms';

export type RegisterForm = {
  email: FormControl<string>;
  password: FormControl<string>;
  repeatPassword: FormControl<string>;
};