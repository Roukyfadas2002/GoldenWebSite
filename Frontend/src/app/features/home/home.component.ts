import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginDialogComponent } from '../auth/login-dialog/login-dialog.component';

@Component({
  selector: 'app-home',
  standalone: true,
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  imports: [CommonModule, LoginDialogComponent], // Importer les modules nécessaires
})
export class HomeComponent {
  showLoginDialog = false;

  openLoginDialog() {
    this.showLoginDialog = true;
  }

  closeLoginDialog() {
    this.showLoginDialog = false;
  }
}
