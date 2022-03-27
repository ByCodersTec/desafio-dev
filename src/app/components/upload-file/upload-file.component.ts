import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-uploads-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

  files: Set<File>;
  constructor() {}

  ngOnInit(): void {}

  onChange(event): void {}

  onUpload(): void {}
}
