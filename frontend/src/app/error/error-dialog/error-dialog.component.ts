import type { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { DialogRef } from '@ngneat/dialog';

@Component({
  selector: 'app-error-dialog',
  imports: [],
  templateUrl: './error-dialog.component.html',
  styleUrl: './error-dialog.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ErrorDialogComponent {
  #ref: DialogRef<{ error: HttpErrorResponse }> = inject(DialogRef);

  get message(): string {
    return this.#ref.data.error.message;
  }

  close = () => this.#ref.close();
}
