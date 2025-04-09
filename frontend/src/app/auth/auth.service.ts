import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  #isLoggedIn = signal(true);

  get isLoggedIn(): boolean {
    return this.#isLoggedIn();
  }
}
