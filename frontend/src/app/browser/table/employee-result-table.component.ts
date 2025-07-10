import { ChangeDetectionStrategy, Component, input } from '@angular/core';
import type { Page } from '../../page/page';
import type { Employee } from '../employee';

@Component({
  selector: 'app-employee-result-table',
  imports: [],
  templateUrl: './employee-result-table.component.html',
  styleUrl: './employee-result-table.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EmployeeResultTableComponent {
  page = input.required<Page<Employee>>();
}
