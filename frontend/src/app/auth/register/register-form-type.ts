import { FormControl, type FormGroup } from '@angular/forms';

export type RegisterForm = FormGroup<{
  email: FormControl<string>;
  password: FormControl<string>;
  repeatPassword: FormControl<string>;
}>;
