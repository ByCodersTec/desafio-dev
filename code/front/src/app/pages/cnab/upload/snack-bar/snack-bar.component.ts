import { Component } from "@angular/core";

@Component({
    selector: 'snack-bar-component-example-snack',
    template: `<span class="example-pizza-party">
                 Upload Concluido com sucesso. Vá no menu, em "Consultas" para visualizar os dados!
                </span>`,
    styles: [
        `
      .example-pizza-party {
        color: hotpink;
      }
    `,
    ],
  })
  export class SnackBarComponent {}