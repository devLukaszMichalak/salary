import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { CapitalizePipe } from '../text-transform/capitalize.pipe';
import { ThemeService } from '../theme/theme.service';

@Component({
  selector: 'app-navbar',
  imports: [CapitalizePipe],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  #themeService = inject(ThemeService);
  #authService = inject(AuthService);
  #router = inject(Router);

  swapTheme = () => this.#themeService.swapTheme();
  oppositeTheme = this.#themeService.oppositeTheme;

  logOut = () => {
    this.#authService.logOut();
    this.#router.navigate(['/login']).then();
  };
}
