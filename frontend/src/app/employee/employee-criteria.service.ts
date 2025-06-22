import { HttpClient, HttpParams } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { takeUntilDestroyed, toObservable } from '@angular/core/rxjs-interop';
import { combineLatest, type Observable, Subject, switchMap } from 'rxjs';
import type { EmployeeQuery } from '../browser/criteria/employee-query';
import type { Page } from '../common/page';
import type { PageQuery } from '../common/page-query';
import type { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeCriteriaService {
  #httpClient = inject(HttpClient);

  #currentPage = signal<number>(0);
  #pageSize = signal<number>(10);

  #pageQuery = computed(() => ({
    page: this.#currentPage(),
    size: this.#pageSize()
  })); //todo page logic in a separate service

  #searchSubject$ = new Subject<EmployeeQuery>();

  #getEmployeesByCriteria(
    employeeQuery: EmployeeQuery,
    pageQuery: PageQuery
  ): Observable<Page<Employee>> {
    const params = new HttpParams({
      fromObject: { ...employeeQuery, ...pageQuery }
    });

    return this.#httpClient.get<Page<Employee>>('/api/v1/employees/search', {
      params
    });
  }

  #employeePage$ = combineLatest([
    this.#searchSubject$,
    toObservable(this.#pageQuery)
  ]).pipe(
    switchMap(([query, page]) => this.#getEmployeesByCriteria(query, page)),
    takeUntilDestroyed()
  );

  get employeePage$() {
    return this.#employeePage$;
  }

  search(query: EmployeeQuery) {
    this.#searchSubject$.next(query);
  }

  nextPage() {
    this.#currentPage.update(value => value + 1);
  }

  previousPage() {
    this.#currentPage.update(value => value - 1);
  }
}
