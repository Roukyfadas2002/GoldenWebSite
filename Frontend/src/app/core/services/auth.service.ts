import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private role: string = ''; // Stockage du rôle localement

  constructor(private http: HttpClient) {}

  /**
   * Méthode pour connecter un utilisateur.
   * Envoie une requête POST au backend avec l'email et le mot de passe.
   * 
   * @param email L'email de l'utilisateur
   * @param password Le mot de passe de l'utilisateur
   * @returns Un Observable contenant la réponse du serveur
   */
  login(email: string, password: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(
      `${this.apiUrl}/login`,
      { email, password },
      { headers }
    );
  }

  logout(): void {
    this.role = ''; // Réinitialise le rôle localement
    console.log('Session utilisateur terminée.');
  }  

  /**
   * Définit le rôle de l'utilisateur après authentification.
   * 
   * @param role Le rôle de l'utilisateur
   */
  setRole(role: string): void {
    this.role = role;
  }

  /**
   * Retourne le rôle de l'utilisateur actuellement connecté.
   * 
   * @returns Le rôle de l'utilisateur
   */
  getRole(): string {
    return this.role;
  }
}
