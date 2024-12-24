import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { HomePageComponent } from './features/home-page/home-page.component';
import { authGuard } from './core/guards/auth.guard';
import { LoginDialogComponent } from './features/auth/login-dialog/login-dialog.component';


export const routes: Routes = [
  { path: '', component: HomeComponent }, // Page principale
  { path: 'home', component: HomePageComponent, canActivate: [authGuard] }, // Garde pour sécuriser la route
  { path: 'home-page', component: HomePageComponent },
  { path: 'login', component: LoginDialogComponent },
];
