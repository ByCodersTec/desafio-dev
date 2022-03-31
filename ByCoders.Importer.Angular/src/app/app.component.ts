import { Component } from '@angular/core';
import { NavigationCancel, NavigationEnd, NavigationError, NavigationStart, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  loading: boolean = true;

  constructor (router: Router) {
    router.events
      .subscribe(event => {
        if (event instanceof NavigationStart) {
          this.loading = true;
        }
        else if (event instanceof NavigationEnd || event instanceof NavigationCancel || event instanceof NavigationError) {
          this.loading = false;
        }
      });
  }
}