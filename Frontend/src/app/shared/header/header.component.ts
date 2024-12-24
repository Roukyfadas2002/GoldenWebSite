import { Component } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';


@Component({
  selector: 'app-header',
  standalone: true,
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  imports: [CommonModule],
})
export class HeaderComponent {
  role: string = '';

  constructor(private authService: AuthService, private router: Router) {
    this.role = this.authService.getRole();
  }

  logout(): void {
    this.authService.setRole(''); // Réinitialise le rôle dans AuthService
    console.log('Utilisateur déconnecté.');
    this.router.navigate(['/']); // Redirige vers la page de connexion ou d'accueil
  }

  navigateToLogs(): void {
    if (this.role === 'admin' || this.role === 'eleveur') {
      this.router.navigate(['/logs']);
    } else {
      console.warn('Vous n’avez pas les autorisations pour accéder à cette page.');
    }
  }

  home(): void {
    console.log('Retour a la page de garde.');
    this.router.navigate(['/home-page']); // Redirige vers la page de connexion ou d'accueil
  }
}
