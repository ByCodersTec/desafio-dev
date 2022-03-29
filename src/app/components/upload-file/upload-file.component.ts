import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { UploadFileService } from './service/upload-file.service';
import { ToastrService } from 'ngx-toastr';
import { HttpEvent, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-uploads-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {
  files: Set<File>;

  constructor(private service: UploadFileService, private toastr: ToastrService) {}

  ngOnInit(): void {}

  showSuccess() {
    this.toastr.success('Sucesso, arquivo enviado com sucesso!');
  }

  showError() {
    this.toastr.error('');
  }

  onChange(event) {
    const selectedFiles = <FileList>event.srcElement.files;

    const fileNames = [];
    this.files = new Set();
    for (let i = 0; i < selectedFiles.length; i++) {
      fileNames.push(selectedFiles[i].name);
      this.files.add(selectedFiles[i]);
    }
    document.getElementById('customFileLabel').innerHTML = fileNames.join(', ');
  }

  onUpload() {
    if (this.files && this.files.size > 0) {
      this.service.upload(this.files, environment.API_URL + '/upload').subscribe((event: HttpEvent<Object>) => {
        if (event.type === HttpEventType.Response) {
          this.showSuccess();
        }
      });
    }
  }
}
