import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SpinnerService {
  public SpinnerSubscrition = new Subject<boolean>();
  constructor() { }
}
