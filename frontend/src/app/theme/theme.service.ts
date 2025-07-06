import {
  computed,
  effect,
  Injectable,
  type Signal,
  signal
} from '@angular/core';
import { THEME_ATTRIBUTE, THEME_STORAGE_KEY } from './theme';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  constructor() {
    effect(() => {
      this.#setTheme(this.#currentTheme());
    });
  }

  #currentTheme = signal<string>(
    document.documentElement.getAttribute(THEME_ATTRIBUTE) ?? 'light'
  );

  isLight = computed(() => this.#currentTheme() === 'light');

  get currentTheme(): Signal<string> {
    return this.#currentTheme.asReadonly();
  }

  oppositeTheme: Signal<string> = computed(() =>
    this.isLight() ? 'dark' : 'light'
  );

  swapTheme(): void {
    const oppositeTheme = this.oppositeTheme();
    this.#currentTheme.set(oppositeTheme);
  }

  #setTheme(theme: string): void {
    document.documentElement.setAttribute(THEME_ATTRIBUTE, theme);
    localStorage.setItem(THEME_STORAGE_KEY, theme);
  }
}
