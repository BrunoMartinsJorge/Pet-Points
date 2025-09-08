import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ViaCep {
  public readonly URL_VIA_CEP = 'https://viacep.com.br/ws';

  constructor(private http: HttpClient) {}

  public getLocation(cep: string) {
    return this.http.get(`${this.URL_VIA_CEP}/${cep}/json/`);
  }
}
