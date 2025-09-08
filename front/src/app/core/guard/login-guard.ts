import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { TokenService } from '../services/token-service';

export const loginGuard: CanActivateFn = (route, state) => {
  const tokenService = inject(TokenService);
  const router = inject(Router);

  const token = tokenService.getToken;

  /**
   * @description Verifica se o token expirou
   * @param {string} token - A token a ser verificada
   * @returns {boolean} - Verdadeiro se o token expirou
   */
  if (!token || tokenService.isTokenExpired(token)) {
    router.navigate(['/login']);
    return false;
  }

  /**
   * @description Verifica se o token contém as regras necessárias para acessar a rota
   * @param {string[]} rules - As regras necessárias para acessar a rota
   * @returns {boolean} - Verdadeiro se o token contém as regras necessárias
   */
  if (
    route.data['RULES'] &&
    !tokenService.TokenHasRulesNecessaryToAccess(route.data['RULES'])
  ) {
    router.navigate(['/unauthorized']);
    return false;
  }
  console.log("Passou!");
  
  return true;
};
