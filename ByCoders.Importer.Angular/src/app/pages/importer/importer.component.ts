import { FileManagementService } from './../../shared/services/file-management.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { catchError, delay, lastValueFrom, tap } from 'rxjs';

@Component({
  selector: 'app-importer',
  templateUrl: './importer.component.html',
  styleUrls: ['./importer.component.scss'],
  host: { class: 'w-100' },
})
export class ImporterComponent implements OnInit {

  @ViewChild('upload') fileUpload!: ElementRef<HTMLInputElement>;

  importerForm!: FormGroup;

  success: boolean = false;
  successButton: boolean = false;

  constructor(private formBuilder: FormBuilder, private fileManagement: FileManagementService) { }

  ngOnInit(): void {
    this.importerForm = this.formBuilder.group({
      file: ['', Validators.required],
    });
  }


  hasFormErrors(): boolean {
    return this.importerForm.hasError('invalid')
    || this.importerForm.hasError('internalServerError');
  }

  resetForm(): void {
    this.importerForm.enable();
    this.importerForm.patchValue({
      file: ''
    });
    this.importerForm.markAsUntouched();

    this.fileUpload.nativeElement.value = '';
  }

  uploadFile(event: Event) {
    const element = event.target as HTMLInputElement;
    if (element.files?.length) {
      this.importerForm.patchValue({
        file: element.files[0]
      });

      if (this.importerForm.controls['file'].hasError('required')) {
        this.importerForm.controls['file'].setErrors({ 'required' : false })
      }
    }
    else {
      this.importerForm.patchValue({
        file: ''
      });
    }
  }

  validateFields(): void {
    if (!this.importerForm.value.file) {
      this.importerForm.controls['file'].setErrors({ 'required' : true });
    }
  }

  async submit(): Promise<any> {
    this.validateFields();

    if (this.importerForm.valid) {
      this.importerForm.disable();
      this.success = false;

      await lastValueFrom(
        this.fileManagement.uploadFile(this.importerForm.value.file)
        .pipe(
          tap(() => {
            this.success = true;
            this.successButton = true;
          }),
          delay(2000),
          catchError((error: string) => {
            switch(error) {
              case 'InvalidFormData':
                this.importerForm.setErrors({ 'invalid': true });
                break;
              default:
                this.importerForm.setErrors({ 'internalServerError': true });
                break;
            }
            throw error;
          })
        )
      );
      
      this.successButton = false;

      this.resetForm();
    }
    else {
      Object.keys(this.importerForm.controls).forEach(field => {
        const control = this.importerForm.get(field);
        control?.markAsTouched({ onlySelf: true });
      });
    }
  }
}