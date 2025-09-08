import { Component, EventEmitter, Input, Output } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { LoginForm } from '../../shared/forms/Login';
import { AuthService } from '../../../../core/services/auth-service';

@Component({
  selector: 'app-login',
  imports: [
    ButtonModule,
    InputTextModule,
    PasswordModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  @Output()
  public toogleModeForm: EventEmitter<void> = new EventEmitter<void>();
  public loginForm: FormGroup;

  constructor(private authService: AuthService) {
    this.loginForm = this.generateForm();
  }

  private generateForm(): FormGroup {
    return new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
      ]),
    });
  }

  public loginUser(): void {
    if (this.loginForm.invalid) return;
    const loginPayload: LoginForm = {
      email: this.loginForm.get('email')?.value,
      senha: this.loginForm.get('password')?.value,
    };
    this.authService.loadUsers(loginPayload).subscribe({
      next: (res) => console.log(res),
      error: (err) => console.error(err),
    })
  }
}
