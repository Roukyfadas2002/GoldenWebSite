import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './../../../core/services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.css'],
  imports: [FormsModule], // Importation explicite de FormsModule
})
export class LoginDialogComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';
  @Output() close = new EventEmitter<void>(); // Émetteur d'événement pour informer le parent


  constructor(private authService: AuthService, private router: Router) {}

  onLogin(): void {
    this.authService.login(this.email, this.password).subscribe({
      next: (response) => {
        console.log('Connexion réussie', response);

        // Enregistrer le rôle dans le service
        const role = response.user.role;
        this.authService.setRole(role);
        console.log('Rôle enregistré :', role);

        // Redirection vers la page suivante
        this.router.navigate(['/home-page']);
      },
      error: (err) => {
        console.error('Erreur de connexion :', err);
        this.errorMessage = err.error?.error || 'Une erreur est survenue.';
      },
    });
  }

  closeDialog(): void {
    this.close.emit(); // Émet un événement pour informer que la modale doit être fermée
    console.log('Dialog closed.');
  }
}
