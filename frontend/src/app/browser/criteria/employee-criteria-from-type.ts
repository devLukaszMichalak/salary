import type { FormControl } from '@angular/forms';
import { type FormGroup } from '@angular/forms';

export type EmployeeCriteriaForm = FormGroup<{
  name: FormControl<string>;
  positionTitle: FormControl<string>;
  agencyName: FormControl<string>;
}>;
