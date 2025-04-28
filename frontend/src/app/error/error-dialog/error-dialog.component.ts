import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { DialogRef } from '@ngneat/dialog';
import type { ErrorResponse } from '../error-response';

@Component({
  selector: 'app-error-dialog',
  imports: [],
  templateUrl: './error-dialog.component.html',
  styleUrl: './error-dialog.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ErrorDialogComponent {
  #ref: DialogRef<{ error: ErrorResponse }> = inject(DialogRef);

  get error(): ErrorResponse {
    return this.#ref.data.error;
  }

  close = () => this.#ref.close();
}
