import { Component, ElementRef, OnInit, QueryList, ViewChildren } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthenticationService } from 'src/app/shared/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  //@ViewChildren('popover') popovers!: QueryList<ElementRef>;

  loginForm!: FormGroup;
  loading: boolean = false;
  success: boolean = false;

  constructor(private formBuilder: FormBuilder, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      rememberMe: [false]
    });
  }

  ngAfterViewInit(): void {
    // this.popovers.map((x: ElementRef) => {
    //    return new Popover(x.nativeElement, { content: '' })
    // });
  }


  public hasFormErrors():boolean {
    return this.loginForm.hasError('invalid')
      || this.loginForm.hasError('internalServerError')
    ;
  }

  public async submit() {
    if (this.loginForm.valid) {
      this.loginForm.disable();

      const res = await this.authenticationService.authenticate({
        username: this.loginForm.get('username')?.value.toString(),
        password: this.loginForm.get('password')?.value.toString()
      });

      if (res === '') {
        this.success = true;
      }
      else {
        this.loginForm.get('password')?.reset();
        this.loginForm.enable();

        switch(res) {
          case 'InvalidUsernamePassword':
            this.loginForm.setErrors({'invalid': true});
            break;
          default:
            this.loginForm.setErrors({'internalServerError': true});
            break;
        }
      }

    } else {
      Object.keys(this.loginForm.controls).forEach(field => {
        const control = this.loginForm.get(field);
        control?.markAsTouched({ onlySelf: true });
      });
    }
  }
}
