import { Component, OnInit } from "@angular/core";

@Component({
    selector: 'pages',
    templateUrl: './pages.component.html'
})
export class PagesComponent implements OnInit {

    menu;
    ngOnInit(){
        console.log('Chegou no pages Component');
    }
}