import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { EmployeeCriteriaService } from '../../employee/employee-criteria.service';
import type { EmployeeCriteriaForm } from './criteria-from-type';

@Component({
  selector: 'app-criteria',
  imports: [ReactiveFormsModule],
  templateUrl: './criteria.component.html',
  styleUrl: './criteria.component.css'
})
export class CriteriaComponent {
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
