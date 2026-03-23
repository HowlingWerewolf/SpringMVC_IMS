import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `<nav><a routerLink="/">Products</a> | <a routerLink="/hello">Hello</a></nav><router-outlet></router-outlet>`
})
export class AppComponent {}

