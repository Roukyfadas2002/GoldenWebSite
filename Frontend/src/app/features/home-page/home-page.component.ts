import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../core/services/auth.service';
import { HeaderComponent } from "../../shared/header/header.component";
import { FooterComponent } from "../../shared/footer/footer.component";

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FooterComponent],
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  role: string = 'invité'; // Valeur par défaut

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.loadUserRole();
  }

  /**
   * Charge le rôle de l'utilisateur depuis le service AuthService.
   */
  private loadUserRole(): void {
    this.role = this.authService.getRole();
    console.log('Rôle utilisateur chargé :', this.role);

    if (!this.role) {
      this.role = 'invité';
      console.warn('Aucun rôle trouvé. Défaut : Invité.');
    }
  }
}
