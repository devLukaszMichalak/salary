import { Component } from '@angular/core';
import { EmployeeCriteriaService } from '../employee/employee-criteria.service';
import { PageService } from '../page/page.service';
import { CriteriaComponent } from './criteria/criteria.component';

@Component({
  selector: 'app-browser',
  imports: [CriteriaComponent],
  providers: [PageService, EmployeeCriteriaService],
  templateUrl: './browser.component.html',
  styleUrl: './browser.component.css'
})
export class BrowserComponent {}
