import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    const isLoggedIn = localStorage.getItem('loggedIn') === 'true'; // Exemple simple
    if (!isLoggedIn) {
      this.router.navigate(['/']); // Redirige vers la page principale si non connecté
      return false;
    }
    return true;
  }
}
