import { AsyncPipe } from '@angular/common';
import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoadingComponent } from '../loading/loading.component';
import { PageSizeComponent } from '../page/page-size/page-size.component';
import { PageService } from '../page/page.service';
import { PaginationComponent } from '../page/pagination/pagination.component';
import { EmployeeCriteriaComponent } from './criteria/employee-criteria.component';
import { EmployeeCriteriaService } from './criteria/employee-criteria.service';
import { EmployeeResultTableComponent } from './table/employee-result-table.component';

@Component({
  selector: 'app-employee-browser',
  imports: [
    EmployeeCriteriaComponent,
    PaginationComponent,
    AsyncPipe,
    FormsModule,
    EmployeeResultTableComponent,
    PageSizeComponent,
    LoadingComponent
  ],
  providers: [PageService, EmployeeCriteriaService],
  templateUrl: './employee-browser.component.html',
  styleUrl: './employee-browser.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EmployeeBrowserComponent {
  #employeeCriteriaService = inject(EmployeeCriteriaService);

  employeePage$ = this.#employeeCriteriaService.employeePage$;
  hasSubmittedCriteria$ = this.#employeeCriteriaService.hasSubmittedCriteria$;
}
