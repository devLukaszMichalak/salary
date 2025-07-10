import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import type { EmployeeCriteriaForm } from './employee-criteria-from-type';
import { EmployeeCriteriaService } from './employee-criteria.service';

@Component({
  selector: 'app-employee-criteria',
  imports: [ReactiveFormsModule],
  templateUrl: './employee-criteria.component.html',
  styleUrl: './employee-criteria.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EmployeeCriteriaComponent {
  #fb = inject(FormBuilder).nonNullable;
  #employeeCriteriaService = inject(EmployeeCriteriaService);

  criteriaForm: EmployeeCriteriaForm = this.#fb.group({
    name: '',
    positionTitle: '',
    agencyName: ''
  });

  search() {
    this.#employeeCriteriaService.search(this.criteriaForm.getRawValue());
  }

  clear() {
    this.criteriaForm.reset();
  }
}
