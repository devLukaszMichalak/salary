import { ChangeDetectionStrategy, Component, input } from '@angular/core';
import type { AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-from-error',
  imports: [],
  templateUrl: './from-error.component.html',
  styleUrl: './from-error.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FromErrorComponent {
  control = input.required<AbstractControl>();
  errorCode = input.required<string>();
  message = input.required<string>();
}
