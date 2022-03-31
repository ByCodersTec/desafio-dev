import { ChangeDetectionStrategy, ChangeDetectorRef, Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { delay, lastValueFrom, map, of, tap } from 'rxjs';

import { MenuItem } from 'src/app/shared/models/navigation/menu-item';

@Component({
  selector: 'app-navigation-drawer',
  templateUrl: './navigation-drawer.component.html',
  styleUrls: [
    './navigation-drawer.component.scss',
    './navigation-drawer.component-animations.scss'
  ]
})
export class NavigationDrawerComponent implements OnInit {
  @ViewChild('navMenu') navMenu!: ElementRef<HTMLUListElement>;
  
  @Input()
  menus: MenuItem[] | null = [];

  @Input()
  autoNavigate: boolean = true;

  @Output() navigated = new EventEmitter<MenuItem>();


  firstOpened: boolean = true;
  collapsed: boolean = false;
  currentScroll: number = 0;

  scrolling: boolean = false;
  private scrollingTimer: NodeJS.Timeout | null = null;

  private activeRootNode: MenuItem | null = null;


  constructor(private router: Router, private ref: ChangeDetectorRef) {
      ref.detach();
  }

  ngOnInit(): void { 
  }

  ngAfterViewInit() {
      this.ref.reattach();
      this.ref.detectChanges();
  }


  public async toggleState() {
    if (this.activeRootNode?.floatMenuState === 'show') {
      await this.clearSelection(this.activeRootNode);
      this.activeRootNode = null;
    }

    this.collapsed = !this.collapsed;
    this.firstOpened = false;
  }


  async menuClick(menu: MenuItem) {
    await this.selectMenu(menu);

    if (menu.url) {
      if (this.autoNavigate) {
        this.router.navigate([menu.url]);
      } else {
        this.navigated.emit(menu);
      }

      if (this.collapsed) {
        this.closeFloatMenu();
      }
    }
  }

  private async clearSelection(menu: MenuItem): Promise<void> {
    if (!(menu.parent) && menu.floatMenuState == 'show') {
      menu.floatMenuState = 'closing';

      await lastValueFrom(of(menu)
        .pipe(
          delay(400),
          tap(menu => {
            menu.floatMenuState = 'hide';
          }),
          delay(100),
          tap(menu => {
            menu.floatMenuState = '';
          })
        ));
    }

    menu.selected = false;
    menu = menu.children.filter(el => el.selected)[0];
    if (menu) {
      this.clearSelection(menu);
    }
    return;
  }

  private async selectMenu(menu: MenuItem): Promise<void> {
    const rootNode = menu.getRootNode();

    if (this.collapsed && menu.parent === null) {
      if (this.activeRootNode?.floatMenuState === 'show') {
        this.clearSelection(this.activeRootNode);

        if (this.activeRootNode === menu) {
          this.activeRootNode = null;
          return;
        }
      }
      else if (this.activeRootNode && this.activeRootNode !== menu) {
        this.clearSelection(this.activeRootNode);
      }

      if (menu.children.length > 0) {
        this.activeRootNode = menu;
        this.activeRootNode.floatMenuState = 'opening';
        this.activeRootNode.selected = true;
        
        return lastValueFrom(of(this.activeRootNode)
          .pipe(delay(400),
            map(menu => {
              menu.floatMenuState = 'show';
            })
          ));
      }
    }
    else {
      if (this.activeRootNode) {
        if (this.activeRootNode !== rootNode) {
          await this.clearSelection(this.activeRootNode);
        }
        else {
          let activeNode: MenuItem | null = this.activeRootNode;
          let menuNode = rootNode;

          while (activeNode) {
            if (activeNode === menu) {
              this.clearSelection(activeNode);

              if (activeNode === this.activeRootNode) {
                this.activeRootNode = null;
              }
              return;
            }
            else if (activeNode !== menuNode) {
              this.clearSelection(activeNode);
              break;
            }
            
            activeNode = activeNode.children.filter(el => el.selected)[0];

            menuNode = menuNode.children.filter(el => el.id == menu.id)[0]
                      ?? menuNode.children.filter(el => el.selected)[0];
          }
        }
      }
    }
    
    this.activeRootNode = rootNode;
    menu.selected = true;
  }


  public async closeFloatMenu() {
    if (this.activeRootNode && this.activeRootNode.floatMenuState === 'show') {
      await this.clearSelection(this.activeRootNode);
    }
  }


  public getOffsetHeight(): number {
    return this.navMenu?.nativeElement?.offsetHeight ?? 0;
  }

  public getOffsetTop(): number {
    return this.navMenu?.nativeElement?.offsetTop ?? 0;
  }

  public updateScroll(event: number) {
    this.currentScroll = event;
    this.scrolling = true;

    if(this.scrollingTimer) {
      clearTimeout(this.scrollingTimer);
    }

    this.scrollingTimer = setTimeout(() => {
      this.scrolling = false;
      this.scrollingTimer = null;
    }, 100);
  }
}