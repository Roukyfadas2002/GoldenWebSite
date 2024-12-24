import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router'; // Importer Router pour la redirection

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  role: string = 'Invité'; // Valeur par défaut

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.loadUserRole();
  }

  /**
   * Charge le rôle de l'utilisateur depuis AuthService.
   */
  private loadUserRole(): void {
    this.role = this.authService.getRole(); // Récupère le rôle depuis le service
    console.log('Rôle récupéré :', this.role);

    if (!this.role || this.role === 'Invité') {
      console.warn(
        'Aucun rôle défini ou utilisateur non connecté. Défini par défaut à "Invité".'
      );
    }
  }

  /**
   * Déconnecte l'utilisateur.
   */
  logout(): void {
    this.authService.setRole(''); // Réinitialise le rôle dans AuthService
    console.log('Utilisateur déconnecté.');
    this.router.navigate(['/']); // Redirige vers la page de connexion ou d'accueil
  }
}
