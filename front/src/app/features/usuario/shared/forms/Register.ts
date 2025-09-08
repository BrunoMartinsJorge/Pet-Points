export interface RegisterForm {
    email: string;
    password: string;
    
    nome: string;
    genero: "M" | "F";
    dataNascimento: Date;
    contato: string;
    cpf: string;
    
    logradouro: string;
    numero?: string;
    complemento?: string;
    bairro: string;
    cep?: string;
}