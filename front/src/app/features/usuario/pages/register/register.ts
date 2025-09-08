import { Component, EventEmitter, Output } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { LoginForm } from '../../shared/forms/Login';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { StepperModule } from 'primeng/stepper';
import { RegisterForm } from '../../shared/forms/Register';
import { DatePickerModule } from 'primeng/datepicker';
import { InputMaskModule } from 'primeng/inputmask';
import { SelectModule } from 'primeng/select';
import { CommonModule } from '@angular/common';
import { InputGroupModule } from 'primeng/inputgroup';
import { ViaCep } from '../../../../shared/services/via-cep';
import { TooltipModule } from 'primeng/tooltip';
import { AuthService } from '../../../../core/services/auth-service';

@Component({
  selector: 'app-register',
  imports: [
    ButtonModule,
    InputTextModule,
    PasswordModule,
    ReactiveFormsModule,
    FormsModule,
    StepperModule,
    DatePickerModule,
    InputMaskModule,
    SelectModule,
    CommonModule,
    InputGroupModule,
    TooltipModule,
  ],
  templateUrl: './register.html',
  styleUrl: './register.scss',
})
export class Register {
  @Output()
  public toogleModeForm: EventEmitter<void> = new EventEmitter<void>();
  public registerFormFirstStep: FormGroup;
  public registerFormSecondStep: FormGroup;
  public registerFormThirdStep: FormGroup;
  public readonly generos = [{ label: 'Masculino', value: 'M' }, { label: 'Feminino', value: 'F' }];
  public generoSelected: string = '';

  constructor(private viaCepService: ViaCep, private auth: AuthService) {
    this.registerFormFirstStep = this.generateFirstForm();
    this.registerFormSecondStep = this.generateSecondForm();
    this.registerFormThirdStep = this.generateThirdForm();
  }

  private generateFirstForm(): FormGroup {
    return new FormGroup(
      {
        email: new FormControl('', [Validators.required, Validators.email]),
        password: new FormControl('', [
          Validators.required,
          Validators.minLength(6),
        ]),
        confirmPassword: new FormControl('', [
          Validators.required,
          Validators.minLength(6),
        ]),
      }
      // { validators: this.checkPasswords }
    );
  }

  private checkPasswords(group: FormGroup) {
    const pass = group.get('password')?.value;
    const confirmPass = group.get('confirmPassword')?.value;
    return pass === confirmPass ? null : { notSame: true };
  }

  private generateSecondForm(): FormGroup {
    return new FormGroup({
      nome: new FormControl('', [Validators.required]),
      dataNascimento: new FormControl('', [Validators.required]),
      contato: new FormControl('', [Validators.required]),
      cpf: new FormControl('', [
        Validators.required,
        Validators.minLength(11),
      ]),
      genero: new FormControl('', [Validators.required]),
    });
  }

    //   logradouro: string;
    // numero?: string;
    // complemento?: string;
    // bairro: string;
    // cep?: string;
  private generateThirdForm(): FormGroup {
    return new FormGroup({
      logradouro: new FormControl('', [Validators.required]),
      numero: new FormControl(0),
      complemento: new FormControl(''),
      bairro: new FormControl('', [Validators.required]),
      cep: new FormControl(''),
    });
  }

  public get firstStepCompleted(): boolean {
    return this.registerFormFirstStep.valid;
  }

  public get secondStepCompleted(): boolean {
    return this.registerFormSecondStep.valid;
  }

  public get thirdStepCompleted(): boolean {
    return this.registerFormThirdStep.valid;
  }

  public get formsCompleted(): boolean {
    return (
      this.firstStepCompleted &&
      this.secondStepCompleted &&
      this.thirdStepCompleted
    );
  }

  public searchByCep(): void {
    if (!this.registerFormThirdStep.get('cep')?.value) return;
    this.viaCepService.getLocation(this.registerFormThirdStep.get('cep')?.value).subscribe({
      next: (res: any) => {
        this.registerFormThirdStep.get('logradouro')?.setValue(res.logradouro);
        this.registerFormThirdStep.get('bairro')?.setValue(res.bairro);
        this.registerFormThirdStep.get('complemento')?.setValue(res.complemento);
        this.registerFormThirdStep.get('cep')?.setValue(res.cep);
        this.registerFormThirdStep.get('numero')?.setValue(res.numero);
      },
      error: (err) => console.error(err),
    });
  }

  public registerUser(): void {
    if (!this.formsCompleted) return;
    const registerPayload: RegisterForm = {
      email: this.registerFormFirstStep.get('email')?.value,
      password: this.registerFormFirstStep.get('password')?.value,
      nome: this.registerFormSecondStep.get('nome')?.value,
      dataNascimento: this.registerFormSecondStep.get('dataNascimento')?.value,
      contato: this.registerFormSecondStep.get('contato')?.value,
      cpf: this.registerFormSecondStep.get('cpf')?.value,
      genero: this.registerFormSecondStep.get('genero')?.value,
      logradouro: this.registerFormThirdStep.get('logradouro')?.value,
      numero: this.registerFormThirdStep.get('numero')?.value,
      complemento: this.registerFormThirdStep.get('complemento')?.value,
      bairro: this.registerFormThirdStep.get('bairro')?.value,
      cep: this.registerFormThirdStep.get('cep')?.value,
    }
    this.auth.registerUser(registerPayload).subscribe({
      next: (res) => console.log(res),
      error: (err) => console.error(err),
    });
  }
}
