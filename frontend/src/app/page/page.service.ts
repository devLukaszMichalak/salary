import { computed, Injectable, type Signal, signal } from '@angular/core';
import { toObservable } from '@angular/core/rxjs-interop';
import type { Observable } from 'rxjs';
import type { PageQuery } from './page-query';

@Injectable()
export class PageService {
  #currentPage = signal<number>(0);
  #pageSize = signal<number>(10);

  #pageQuery: Signal<PageQuery> = computed(() => ({
    page: this.#currentPage(),
    size: this.#pageSize()
  }));

  #pageQuery$ = toObservable(this.#pageQuery);

  get pageQuery$(): Observable<PageQuery> {
    return this.#pageQuery$;
  }

  nextPage(): void {
    this.#currentPage.update(value => value + 1);
  }

  previousPage(): void {
    this.#currentPage.update(value => value - 1);
  }
}
