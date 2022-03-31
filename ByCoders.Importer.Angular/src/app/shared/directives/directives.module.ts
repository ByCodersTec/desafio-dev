import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TrackScrollDirective } from './track-scroll.directive';


@NgModule({
  declarations: [
    TrackScrollDirective
  ],
  imports: [
    CommonModule
  ],
  exports: [
    TrackScrollDirective
  ]
})
export class DirectivesModule { }