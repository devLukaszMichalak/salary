import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import type { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  #isLoggedIn = signal(true);

  #httpClient = inject(HttpClient);

  get isLoggedIn(): boolean {
    return this.#isLoggedIn();
  }

  checkEmail(email: string): Observable<boolean> {
    return this.#httpClient.get<boolean>('api/v1/auth/check-email', {
      params: { email }
    });
  }
}
