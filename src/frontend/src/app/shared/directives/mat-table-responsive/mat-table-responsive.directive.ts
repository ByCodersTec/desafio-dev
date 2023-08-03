import {
  AfterViewInit,
  Directive,
  ElementRef,
  OnDestroy,
  OnInit,
  Renderer2
} from '@angular/core';
import { BehaviorSubject, combineLatest, Subject } from 'rxjs';
import { map, mapTo, takeUntil } from 'rxjs/operators';

@Directive({
  selector: '[matTableResponsive]'
})
export class MatTableResponsiveDirective
  implements OnInit, AfterViewInit, OnDestroy {
  private onDestroy$ = new Subject<boolean>();

  private thead: HTMLTableSectionElement | null = null;
  private tbody: HTMLTableSectionElement | null = null;

  private theadChanged$ = new BehaviorSubject(true);
  private tbodyChanged$ = new Subject<boolean>();

  private theadObserver = new MutationObserver(() =>
    this.theadChanged$.next(true)
  );
  private tbodyObserver = new MutationObserver(() =>
    this.tbodyChanged$.next(true)
  );

  constructor(private table: ElementRef, private renderer: Renderer2) {}

  ngOnInit() {
    this.thead = this.table.nativeElement.querySelector('thead');
    this.tbody = this.table.nativeElement.querySelector('tbody');

    if (this.thead != null) {
      this.theadObserver.observe(this.thead, {
        characterData: true,
        subtree: true
      }); 
    }

    if (this.tbody) {
      this.tbodyObserver.observe(this.tbody, { childList: true }); 
    }
  }

  ngAfterViewInit() {
    /**
     * Set the "data-column-name" attribute for every body row cell, either on
     * thead row changes (e.g. language changes) or tbody rows changes (add, delete).
     */
    if (this.thead && this.tbody) {
      combineLatest([this.theadChanged$, this.tbodyChanged$])
      .pipe(
        mapTo({ headRow: this.thead.rows.item(0)!, bodyRows: this.tbody.rows }),
        map(({ headRow, bodyRows }) => {
          return {
            columnNames: [...headRow.children].map(
              headerCell => headerCell.textContent!
            ),
            rows: [...bodyRows].map(row => [...row.children])
          }
        }),
        takeUntil(this.onDestroy$)
      )
      .subscribe(({ columnNames, rows }) =>
        rows.forEach(rowCells =>
          rowCells.forEach(cell =>
            this.renderer.setAttribute(
              cell,
              'data-column-name',
              columnNames[(cell as HTMLTableCellElement).cellIndex]
            )
          )
        )
      ); 
    }
  }

  ngOnDestroy(): void {
    this.theadObserver.disconnect();
    this.tbodyObserver.disconnect();

    this.onDestroy$.next(true);
  }
}
