import { computed, Injectable, type Signal, signal } from '@angular/core';
import { toObservable } from '@angular/core/rxjs-interop';
import type { PageQuery } from './page-query';

@Injectable()
export class PageService {
  #currentPage = signal<number>(0);

  #pageQuery: Signal<PageQuery> = computed(() => ({
    page: this.#currentPage(),
    size: this.pageSize()
  }));

  pageQuery$ = toObservable(this.#pageQuery);

  pageSize = signal<number>(10);

  nextPage(): void {
    this.#currentPage.update(value => value + 1);
  }

  previousPage(): void {
    this.#currentPage.update(value => value - 1);
  }
}
