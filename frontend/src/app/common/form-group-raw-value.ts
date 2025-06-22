import type { FormControl, FormGroup } from '@angular/forms';

export type FormGroupRawValue<TFormGroup extends FormGroup> = {
  [K in keyof TFormGroup['controls']]: TFormGroup['controls'][K] extends FormControl<
      infer TControl
    >
    ? TControl
    : never;
};