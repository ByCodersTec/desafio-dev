import { Component, ElementRef, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-logout-modal',
  templateUrl: './logout-modal.component.html'
})
export class LogoutModalComponent implements OnInit {
  @ViewChild('modal') modalElement!: ElementRef<HTMLDivElement>;
  modal!: Modal;
  
  @Output() logout = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.modal = new Modal(this.modalElement.nativeElement);
  }


  show(): void {
    this.modal.show();
  }

  confirmClick(): void {
    this.logout.emit();
  }
}