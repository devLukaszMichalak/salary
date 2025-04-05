import { Component, inject } from '@angular/core';
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

  swapTheme = () => this.#themeService.swapTheme();
  oppositeTheme = this.#themeService.oppositeTheme;
}
