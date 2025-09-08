import { Routes } from '@angular/router';
import { Usuario } from './features/usuario/usuario';
import { loginGuard } from './core/guard/login-guard';

export const routes: Routes = [
  { path: 'login', component: Usuario, title: 'Login' },
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full',
  },
  // Rotas de gerentes
//   {
//     path: 'gerente',
//     // component: GerenteComponent,
//     canActivate: [loginGuard],
//     data: {
//       visible: true,
//       RULES: ['RULE_REST_GERENTE'],
//       nome: 'Página do Gerente',
//     },
//     children: [
//       {
//         path: 'dashboard',
//         // component: HomeGerenteComponent,
//         title: 'Dashboard',
//         data: {
//           visible: true,
//           nome: 'Dashboard',
//           icone: 'home',
//         },
//       },
//     ],
//   },
//   // Rotas de clientes
//   {
//     path: 'cliente',
//     // component: ClienteComponent,
//     canActivate: [loginGuard],
//     data: {
//       visible: true,
//       RULES: ['RULE_REST_USUARIO'],
//       nome: 'Página do Cliente',
//     },
//     children: [
//       {
//         path: 'home',
//          // component: HomeClienteComponent,
//         title: 'Home',
//         data: {
//           visible: true,
//           nome: 'Home',
//           icone: 'home',
//         },
//       },
//       {
//         path: 'emprestimos',
//         // component: EmprestimosComponent,
//         title: 'Emprestimos',
//         data: {
//           visible: true,
//           nome: 'Emprestimos',
//           icone: 'sticky-note',
//         },
//       },
//       {
//         path: 'livros',
//         // component: LivrosComponent,
//         title: 'Livros',
//         data: {
//           visible: true,
//           nome: 'Livros',
//           icone: 'book',
//         },
//       },
//       {
//         path: 'detalhes-livro/:id',
//         // component: DetalhesLivrosComponent,
//         title: 'Detalhes do Livro',
//         data: {
//           visible: false,
//           nome: 'Livros',
//         },
//       },
//     ],
//   },
//   // Rotas de atendente
//   {
//     path: 'atendente',
//     // component: AtendenteComponent,
//     canActivate: [loginGuard],
//     data: {
//       visible: true,
//       RULES: ['RULE_REST_ATENDENTE'],
//       nome: 'Página do Atendente',
//     },
//     children: [
//       {
//         path: 'home',
//         // component: HomeAtendenteComponent,
//         title: 'Home',
//         data: {
//           visible: true,
//           nome: 'Home',
//           icone: 'home',
//         },
//       },
//       {
//         path: 'solicitacoes-emprestimos',
//         // component: SolicitacoesEmprestimosComponent,
//         title: 'Solicitações de Emprestimos',
//         data: {
//           visible: true,
//           nome: 'Solicitações',
//           icone: 'bell-concierge',
//         },
//       },
//       {
//         path: 'solicitacoes-concluidas',
//         // component: SolicitacoesConcluidasComponent,
//         title: 'Solicitações Concluidas',
//         data: {
//           visible: true,
//           nome: 'Solicitações Concluidas',
//           icone: 'check-to-slot',
//         },
//       },
//       {
//         path: 'livros-cadastrados',
//         // component: ListaLivrosComponent,
//         title: 'Livros Cadastrados',
//         data: {
//           visible: true,
//           nome: 'Livros Cadastrados',
//           icone: 'book',
//         },
//       },
//     ],
//   },
//   {
//     path: 'perfil',
//     // component: PerfilComponent,
//     title: 'Perfil de Usuário',
//     data: {
//       // RULES: [],
//       visible: true,
//       nome: 'Perfil de Usuário',
//       icone: 'user',
//     },
//     canActivate: [loginGuard],
//   },
//   {
//     path: 'unauthorized',
//     // component: PageUnauthorizedComponent,
//     title: 'Rota Não Autorizada',
//   },
//   {
//     // path: '**', component: PageNotFoundComponent,
//     title: 'Rota Não Encontrada',
//   },
];
