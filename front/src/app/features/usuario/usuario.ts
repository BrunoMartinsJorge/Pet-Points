import { Component } from '@angular/core';
import { Login } from "./pages/login/login";
import { Register } from "./pages/register/register";

@Component({
  selector: 'app-usuario',
  imports: [Login, Register],
  templateUrl: './usuario.html',
  styleUrl: './usuario.scss'
})
export class Usuario {
  public mode_form: 'login' | 'register' = 'login';

  public constructor() {}

  public toogleModeForm(event: any): void {    
    this.mode_form = this.mode_form === 'login' ? 'register' : 'login';
  }

}
