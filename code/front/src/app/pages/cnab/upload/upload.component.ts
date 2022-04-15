import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { CnabService } from 'src/app/services/cnab.service';
import { SnackBarNoFileComponent } from './snack-bar/snack-bar-no-file.component';
import { SnackBarComponent } from './snack-bar/snack-bar.component';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss']
})
export class UploadComponent implements OnInit {
  formulario:FormGroup;

  fileName = '';

  durationInSeconds = 10;

  uploadProgress:number;
  uploadSub: Subscription;
  formData = new FormData();

  constructor(
    private snackBar: MatSnackBar, 
    private formBuilder: FormBuilder,
    private cnabService: CnabService
  ) { }

  ngOnInit(): void {
    this.createFormulario();
  }
  createFormulario(){
    this.formulario = this.formBuilder.group({
      file: ['', Validators.required]
    });
  }

  onFileSelected(event){
    const file:File = event.target.files[0];
    
    if (file) {
      this.fileName = file.name;
      this.formulario.value.file = file;
        this.formData.append("file", file);
    }
  }

  enviarArquivo(){
    if(this.formulario.value.file === null || this.formulario.value.file === ''){
      return this.snackBar.openFromComponent(SnackBarNoFileComponent, {
        duration: this.durationInSeconds * 1000,
      });
    }


    this.cnabService.uploadArquivoCNAB(this.formData)
      .pipe(finalize(() => this.reset()))
      .subscribe(retorno => {
        
        return this.snackBar.openFromComponent(SnackBarComponent, {
          duration: this.durationInSeconds * 1000,
        });
      });
  
  }
  
  cleanInputArquivo(){
    this.fileName = null;
    this.formData =  new FormData();
    this.formulario.reset();

  }

  cancelUpload() {
    this.uploadSub.unsubscribe();
    this.reset();
  }

  reset() {
    this.cleanInputArquivo();
    this.uploadProgress = null;
    this.uploadSub = null;
    
  }
}
