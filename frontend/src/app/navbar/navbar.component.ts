import { NgTemplateOutlet } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { NgIcon, provideIcons } from '@ng-icons/core';
import {
  heroBars3,
  heroMagnifyingGlass,
  heroMoon,
  heroPower,
  heroSun
} from '@ng-icons/heroicons/outline';
import { AuthService } from '../auth/auth.service';
import { CapitalizePipe } from '../text-transform/capitalize.pipe';
import { ThemeService } from '../theme/theme.service';

@Component({
  selector: 'app-navbar',
  imports: [CapitalizePipe, NgIcon, RouterLink, NgTemplateOutlet],
  providers: [
    provideIcons({
      heroBars3,
      heroPower,
      heroMoon,
      heroSun,
      heroMagnifyingGlass
    })
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  #themeService = inject(ThemeService);
  #authService = inject(AuthService);
  #router = inject(Router);

  swapTheme = () => this.#themeService.swapTheme();
  oppositeTheme = this.#themeService.oppositeTheme;
  isLight = this.#themeService.isLight;

  logOut = () => {
    this.#authService.logOut();
    void this.#router.navigate(['/login']);
  };
}
