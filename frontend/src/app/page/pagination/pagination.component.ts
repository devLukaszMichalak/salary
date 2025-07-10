import {
  ChangeDetectionStrategy,
  Component,
  computed,
  inject,
  input
} from '@angular/core';
import type { PageMetadata } from '../page';
import { PageService } from '../page.service';

@Component({
  selector: 'app-pagination',
  imports: [],
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PaginationComponent {
  #pageService = inject(PageService);

  page = input.required<PageMetadata>();

  isFirstPage = computed(() => this.page().number === 0);

  isLastPage = computed(
    () =>
      this.page().totalPages === 0 ||
      this.page().number === this.page().totalPages - 1
  );

  nextPage() {
    this.#pageService.nextPage();
  }

  previousPage() {
    this.#pageService.previousPage();
  }
}
