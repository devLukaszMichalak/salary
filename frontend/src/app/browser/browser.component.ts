import { AsyncPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { EmployeeCriteriaService } from '../employee/employee-criteria.service';
import { PageService } from '../page/page.service';
import { PaginationComponent } from '../page/pagination/pagination.component';
import { CriteriaComponent } from './criteria/criteria.component';

@Component({
  selector: 'app-browser',
  imports: [CriteriaComponent, PaginationComponent, AsyncPipe],
  providers: [PageService, EmployeeCriteriaService],
  templateUrl: './browser.component.html',
  styleUrl: './browser.component.css'
})
export class BrowserComponent {
  #employeeCriteriaService = inject(EmployeeCriteriaService);

  employeePage$ = this.#employeeCriteriaService.employeePage$;
}
