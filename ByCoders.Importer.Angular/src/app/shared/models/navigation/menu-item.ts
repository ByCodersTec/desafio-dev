export class MenuItem {
    parent: MenuItem | null = null;
    
    linkActive: boolean = false;
    selected: boolean = false;
    floatMenuState: 'opening' | 'show' | 'closing' | 'hide' | '' = '';
  
    id!: number;
  
    icon!: string;
    label!: string;
    url!: string | undefined;
  
    children: MenuItem[] = new Array<MenuItem>();
  
    public getRootNode(): MenuItem {
      return (this.parent)
        ? this.parent.getRootNode()
        : this;
    }
  }