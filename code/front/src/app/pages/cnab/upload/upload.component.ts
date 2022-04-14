import { HttpClient, HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { SnackBarNoFileComponent } from './snack-bar/snack-bar-no-file.component';
import { SnackBarTypeFileErrorComponent } from './snack-bar/snack-bar-type-file-error.component';
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

  constructor(private http: HttpClient,private snackBar: MatSnackBar, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.createFormulario();
  }
  createFormulario(){
    this.formulario = this.formBuilder.group({
      file: ['', Validators.required]
    });
  }

  onFileSelected(event){
    console.log(event.target.files[0]);
    const file:File = event.target.files[0];
    console.log("this.formulario");
    console.log(this.formulario);
    if(file.type !== 'text/plain'){
      this.cleanInputArquivo();
      return this.snackBar.openFromComponent(SnackBarTypeFileErrorComponent, {
        duration: this.durationInSeconds * 1000,
      });
    }
    if (file) {
      this.fileName = file.name;
      this.formulario.value.file = file;
        this.formData.append("cnab", file);
    }
  }

  enviarArquivo(){
    if(this.formulario.value.file === null || this.formulario.value.file === ''){
      return this.snackBar.openFromComponent(SnackBarNoFileComponent, {
        duration: this.durationInSeconds * 1000,
      });
    }
    const upload$ = this.http.post("/api/thumbnail-upload", this.formData, {
      reportProgress: true,
      observe: 'events'
    })
    .pipe(
        finalize(() => this.reset())
    );
  
    this.uploadSub = upload$.subscribe(event => {
      if (event.type == HttpEventType.UploadProgress) {
        this.uploadProgress = Math.round(100 * (event.loaded / event.total));
      }
      if(this.uploadProgress == 100){
        this.snackBar.openFromComponent(SnackBarComponent, {
          duration: this.durationInSeconds * 1000,
        });
      }
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
    this.uploadProgress = null;
    this.uploadSub = null;
    this.formulario.reset();
  }
}
