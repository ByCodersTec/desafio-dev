import { Component } from "@angular/core";

@Component({
    selector: 'snack-bar-component-example-snack',
    template: `<span class="example-pizza-party">
                 Necessário selecionar arquivo!
                </span>`,
    styles: [
        `
      .example-pizza-party {
        color: hotpink;
      }
    `,
    ],
  })
  export class SnackBarNoFileComponent {}