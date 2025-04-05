import { EnvironmentProviders, provideAppInitializer } from '@angular/core';
import { THEME_ATTRIBUTE, THEME_STORAGE_KEY } from './theme';

/**
 * Prevents the flash of default theme when loading a page without a theme in localstorate
 * @param defaultTheme The theme to be set, if none is present
 */
export const provideTheme = (
  defaultTheme: 'light' | 'dark' = 'light'
): EnvironmentProviders =>
  provideAppInitializer(() => initializeTheme(defaultTheme));

const initializeTheme = (defaultTheme: 'light' | 'dark') => {
  const theme = localStorage.getItem(THEME_STORAGE_KEY);

  if (theme === null) {
    document.documentElement.setAttribute(THEME_ATTRIBUTE, defaultTheme);
  } else {
    document.documentElement.setAttribute(THEME_ATTRIBUTE, theme);
  }
};
