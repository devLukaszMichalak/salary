import { HttpClient, HttpContext } from '@angular/common/http';
import {
  computed,
  effect,
  inject,
  Injectable,
  type Signal,
  signal
} from '@angular/core';
import { map, type Observable, of, tap } from 'rxjs';
import { TOAST_BYPASS } from '../processing/toast-bypass';
import type { JwtResponse } from './jwt-response';
import type { LoginUserCommand } from './login-user-command';
import type { RegisterUserCommand } from './register-user-command';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  #token = signal(localStorage.getItem('token') ?? '');

  #httpClient = inject(HttpClient);

  constructor() {
    effect(() => {
      localStorage.setItem('token', this.#token());
    });
  }

  get token(): Signal<string> {
    return this.#token.asReadonly();
  }

  resetToken(): void {
    this.#token.set('');
  }

  doesTokenExist: Signal<boolean> = computed(() => !!this.#token());

  get isTokenValid$(): Observable<boolean> {
    if (!this.doesTokenExist()) {
      return of(false);
    }

    return this.validateToken();
  }

  isEmailTaken(email: string): Observable<boolean> {
    return this.#httpClient.get<boolean>('api/v1/auth/is-email-taken', {
      params: { email }
    });
  }

  login(command: LoginUserCommand): Observable<boolean> {
    return this.#httpClient
      .post<JwtResponse>('api/v1/auth/login', command)
      .pipe(
        tap(jwt => this.#token.set(jwt.token)),
        map(jwt => !!jwt.token)
      );
  }

  register(command: RegisterUserCommand): Observable<boolean> {
    return this.#httpClient
      .post<JwtResponse>('api/v1/auth/register', command)
      .pipe(
        tap(jwt => this.#token.set(jwt.token)),
        map(jwt => !!jwt.token)
      );
  }

  logOut = (): void => this.#token.set('');

  validateToken(): Observable<boolean> {
    return this.#httpClient.post<boolean>(
      '/api/v1/auth/validate-token',
      {},
      { context: new HttpContext().set(TOAST_BYPASS, true) }
    );
  }
}
