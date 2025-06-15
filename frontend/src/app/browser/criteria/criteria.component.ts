import { Component, inject } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import type { CriteriaForm } from './criteria-from-type';

@Component({
  selector: 'app-criteria',
  imports: [],
  templateUrl: './criteria.component.html',
  styleUrl: './criteria.component.css'
})
export class CriteriaComponent {
  #fb = inject(FormBuilder).nonNullable;

  criteriaForm: CriteriaForm = this.#fb.group({
    name: '',
    positionTitle: '',
    agencyName: ''
  });
}
