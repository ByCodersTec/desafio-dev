import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableGridParserComponent } from './table-grid-parser.component';

describe('TableGridParserComponent', () => {
  let component: TableGridParserComponent;
  let fixture: ComponentFixture<TableGridParserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableGridParserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableGridParserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
