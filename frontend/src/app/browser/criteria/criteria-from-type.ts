import { FormControl, type FormGroup } from '@angular/forms';

export type CriteriaForm = FormGroup<{
  name: FormControl<string>;
  positionTitle: FormControl<string>;
  agencyName: FormControl<string>;
}>;