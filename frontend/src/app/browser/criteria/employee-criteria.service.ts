import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import {
  combineLatest,
  map,
  type Observable,
  startWith,
  Subject,
  switchMap
} from 'rxjs';
import type { Page } from '../../page/page';
import type { PageQuery } from '../../page/page-query';
import { PageService } from '../../page/page.service';
import type { Employee } from '../employee';
import { EmployeeQuery } from './employee-query';

@Injectable()
export class EmployeeCriteriaService {
  #httpClient = inject(HttpClient);
  #pageService = inject(PageService);

  #searchSubject$ = new Subject<EmployeeQuery>();

  #getEmployeesByCriteria(
    employeeQuery: EmployeeQuery,
    pageQuery: PageQuery
  ): Observable<Page<Employee> | undefined> {
    const params = new HttpParams({
      fromObject: { ...employeeQuery, ...pageQuery }
    });

    return this.#httpClient
      .get<Page<Employee>>('/api/v1/employees/search', {
        params
      })
      .pipe(startWith(undefined));
  }

  employeePage$: Observable<Page<Employee> | undefined> = combineLatest([
    this.#searchSubject$,
    this.#pageService.pageQuery$
  ]).pipe(
    switchMap(([query, page]) => this.#getEmployeesByCriteria(query, page)),
    takeUntilDestroyed()
  );

  hasSubmittedCriteria$: Observable<boolean> = this.#searchSubject$.pipe(
    map(() => true),
    startWith(false)
  );

  search(query: EmployeeQuery): void {
    this.#searchSubject$.next(query);
  }
}
