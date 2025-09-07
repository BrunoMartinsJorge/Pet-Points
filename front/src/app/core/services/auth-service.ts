import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly URL_BACK = '/conta';

  constructor(private http: HttpClient) {}

  /**
   *
   * @description - Metodo que realiza a chamada para o backend para realizar o login do usuário
   * @param form - Formulário com o email e senha do usuário.
   * @returns Observable<any> - Usuário logado recebera um token de acesso
   */
  public loadUsers(form: any): Observable<any> {
    return this.http.post<any>(`${this.URL_BACK}/login`, form);
  }

  /**
   *
   * @description - Metodo que realiza a chamada para o backend para realizar o cadastro do usuário
   * @param form - Formulário com o email, senha, nome, data de nascimento e o genero do usuário.
   * @returns Observable<any> - Usuário registrado recebera um token de acesso
   */
  public registerUser(form: any): Observable<any> {
    return this.http.post<any>(`${this.URL_BACK}/register`, form);
  }
}
