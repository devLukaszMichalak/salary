import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PageService } from '../page.service';

@Component({
  selector: 'app-page-size',
  imports: [FormsModule],
  templateUrl: './page-size.component.html',
  styleUrl: './page-size.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PageSizeComponent {
  #pageService = inject(PageService);

  pageSize = this.#pageService.pageSize;
}
