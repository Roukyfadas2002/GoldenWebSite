import { Component } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-footer',
  standalone: true,
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css'],
})
export class FooterComponent {
  role: string = 'invité'; // Valeur par défaut

  constructor(private authService: AuthService) {
    this.role = this.authService.getRole();
    console.log('Rôle utilisateur chargé :', this.role);

    if (!this.role) {
      this.role = 'invité';
      console.warn('Aucun rôle trouvé. Défaut : Invité.');
    }
  }
}
