import { Component } from "@angular/core";

@Component({
    selector: 'snack-bar-component-example-snack',
    template: `<span class="example-pizza-party">
                 Tipo de extensão do arquivo selecionado incorreto, favor inserir um arquivo '.txt' CNAB
                </span>`,
    styles: [
        `
      .example-pizza-party {
        color: hotpink;
      }
    `,
    ],
  })
  export class SnackBarTypeFileErrorComponent {}