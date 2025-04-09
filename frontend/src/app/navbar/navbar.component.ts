import { Component, inject } from '@angular/core';
import { CapitalizePipe } from '../text-transform/capitalize.pipe';
import { ThemeService } from '../theme/theme.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [CapitalizePipe, RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  #themeService = inject(ThemeService);

  swapTheme = () => this.#themeService.swapTheme();
  oppositeTheme = this.#themeService.oppositeTheme;
}
