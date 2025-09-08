import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-page-unauthorized',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './page-unauthorized.html',
  styleUrl: './page-unauthorized.scss'
})
export class PageUnauthorizedComponent {

  /**
   * Retorna para a pagina anterior
   */
  public returnToLastPage(): void {
    window.history.back();
  }
}
