import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { injectQuery } from '@ngneat/query';

@Component({
  selector: 'app-hello',
  imports: [],
  templateUrl: './hello.component.html',
  styleUrl: './hello.component.css'
})
export class HelloComponent {
  #query = injectQuery();
  #http = inject(HttpClient);

  todos = this.getTodos().result;

  getTodos() {
    return this.#query({
      queryKey: ['todos'] as const,
      queryFn: () => {
        return this.#http.get<{ id: string; title: string }[]>(
          'https://jsonplaceholder.typicode.com/todos'
        );
      }
    });
  }
}
