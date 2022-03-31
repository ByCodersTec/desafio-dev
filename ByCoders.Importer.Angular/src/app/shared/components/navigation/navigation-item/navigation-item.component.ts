import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { ActivatedRoute, ActivationEnd, Router } from '@angular/router';

import { MenuItem } from 'src/app/shared/models/navigation/menu-item';

@Component({
  selector: 'app-navigation-item',
  templateUrl: './navigation-item.component.html',
  styleUrls: ['./navigation-item.component.scss']
})
export class NavigationItemComponent implements OnInit {
    @ViewChild('navItem') navItem!: ElementRef<HTMLLIElement>;

    @Input() parentCollapsed!: boolean;
    @Input() parentScrolling!: boolean;
    @Input() firstOpened!: boolean;
    @Input() level: number = 0;
  
    @Input() parentOffsetTop!: number;
    @Input() parentScroll: number = 0;
    @Input() parentOffsetHeight!: number;
    
    @Input() menu!: MenuItem;
  
    @Output() menuClick = new EventEmitter<MenuItem>();
    @Output() closeFloatMenu = new EventEmitter();
        
    constructor(private router: Router, private activatedRoute: ActivatedRoute) { }
  
    ngOnInit(): void {
      const activatedPath = this.router.url.substring(1, this.router.url.length);
      if (activatedPath === this.menu.url) {
        this.menuClick.emit(this.menu);
      }
  
      this.router.events.subscribe(event => {
        if (event instanceof ActivationEnd) {
          if (event.snapshot.component && event.snapshot.firstChild?.url[0] && event.snapshot.firstChild?.url[0].path === this.menu.url) {
            this.menu.linkActive = true;
            let menu = this.menu;
            
            while (menu.parent) {
              menu.parent.linkActive = true;
              menu = menu.parent;
            }
          }
          else if (this.menu.linkActive && this.menu.children.length === 0) {
            this.menu.linkActive = false;
            let menu = this.menu;
            
            while (menu.parent) {
              menu.parent.linkActive = false;
              menu = menu.parent;
            }
          }
        }
      });
    }
    
  
    public click() {
      this.menuClick.emit(this.menu);
    }
  
    public subMenuClick(menu: MenuItem) {
      this.menuClick.emit(menu);
    }
  
    public subMenuCloseFloatMenu() {
      this.closeFloatMenu.emit();
    }
  
    
    public calculateHeight(menu: MenuItem): number {
      if (menu.children.length > 0 && menu.selected) {
        
        let height = menu.children.length * 45;
        menu.children.forEach(subMenu => {
          height += this.calculateHeight(subMenu);
        });
  
        const maxHeight = this.calculateMaxHeight() as number;
  
        return height < maxHeight || this.level > 0 || !this.parentCollapsed
          ? height
          : maxHeight ?? height;
      }
      return 0;
    }
  
    public calculateMarginTop() {
      if (this.parentCollapsed && (this.menu.floatMenuState === 'opening' || this.menu.floatMenuState === 'show' || this.menu.floatMenuState === 'closing')) {
        if (this.navItem.nativeElement.offsetTop - this.parentScroll <= this.parentOffsetTop) {
          return this.parentOffsetTop - this.navItem.nativeElement.offsetTop - 45;
        }
        else if (this.navItem.nativeElement.offsetTop + this.calculateHeight(this.menu) - this.parentOffsetTop - this.parentScroll > this.parentOffsetHeight) {
          return this.parentOffsetTop - this.navItem.nativeElement.offsetTop + this.parentOffsetHeight - this.calculateHeight(this.menu) - 45;
        }
        return (this.parentScroll + this.navItem.nativeElement.clientHeight) * -1;
      }
      return 0;
    }
  
    public calculateMaxHeight(): number | null {
      return this.parentCollapsed
        ? this.parentOffsetHeight
        : null;
    }
}