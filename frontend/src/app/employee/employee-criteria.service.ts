import { HttpClient, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { combineLatest, type Observable, Subject, switchMap } from 'rxjs';
import type { EmployeeQuery } from '../browser/criteria/employee-query';
import type { Page } from '../page/page';
import type { PageQuery } from '../page/page-query';
import { PageService } from '../page/page.service';
import type { Employee } from './employee';

@Injectable()
export class EmployeeCriteriaService {
  #httpClient = inject(HttpClient);
  #pageService = inject(PageService);

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
    this.#pageService.pageQuery$
  ]).pipe(
    switchMap(([query, page]) => this.#getEmployeesByCriteria(query, page)),
    takeUntilDestroyed()
  );

  get employeePage$(): Observable<Page<Employee>> {
    return this.#employeePage$;
  }

  search(query: EmployeeQuery): void {
    this.#searchSubject$.next(query);
  }
}
