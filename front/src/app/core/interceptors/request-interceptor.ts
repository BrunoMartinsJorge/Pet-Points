import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { TokenService } from '../services/token-service';

export const requestInterceptor: HttpInterceptorFn = (req, next) => {
  const tokenService = inject(TokenService);
  const router = inject(Router);

  /* Adiciona o prefixo para todas as requisições caso alguma não possua a porta */
  if (
    !req.url.startsWith('http://localhost:8080') &&
    !req.url.startsWith('https://viacep.com.br/ws')
  )
    req = req.clone({ url: `http://localhost:8080${req.url}` });
  console.log(req.url);

  /* Adiciona o token no header para todas as requisições */
  if (localStorage.getItem('token') != null) {
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      },
    });
  }

  // Tratamento de erros, como por exemplo, 401 ou 403
  return next(req).pipe(
    catchError((error) => {
      switch (error.status) {
        case 401:
          tokenService.removeToken();
          router.navigate(['/login']);
          break;
        case 403:
          router.navigate(['/unauthorized']);
          break;
      }

      return throwError(() => error);
    })
  );
};
