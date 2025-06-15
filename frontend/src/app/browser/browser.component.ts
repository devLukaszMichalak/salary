import { Component } from '@angular/core';
import { CriteriaComponent } from './criteria/criteria.component';

@Component({
  selector: 'app-browser',
  imports: [CriteriaComponent],
  templateUrl: './browser.component.html',
  styleUrl: './browser.component.css'
})
export class BrowserComponent {}
