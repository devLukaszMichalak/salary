import { Component, inject } from '@angular/core';
import { DialogService } from '@ngneat/dialog';
import { HelloComponent } from './hello/hello.component';

@Component({
  selector: 'app-root',
  imports: [],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  private dialog = inject(DialogService);

  openDialog() {
    this.dialog.open(HelloComponent);
  }
}
