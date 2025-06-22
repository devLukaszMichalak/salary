import { AsyncPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { EmployeeCriteriaService } from '../../employee/employee-criteria.service';
import type { EmployeeCriteriaForm } from './criteria-from-type';

@Component({
  selector: 'app-criteria',
  imports: [AsyncPipe],
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

  employeePage$ = this.#employeeCriteriaService.employeePage$;

  search() {
    this.#employeeCriteriaService.search(this.criteriaForm.getRawValue());
  }

  nextPage() {
    this.#employeeCriteriaService.nextPage();
  }

  previousPage() {
    this.#employeeCriteriaService.previousPage();
  }
}
