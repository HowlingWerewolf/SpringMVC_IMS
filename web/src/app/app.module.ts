import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { HelloComponent } from './hello/hello.component';
import { ProductsComponent } from './products/products.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [AppComponent, HelloComponent, ProductsComponent],
  imports: [BrowserModule, HttpClientModule, RouterModule.forRoot([
    { path: '', component: ProductsComponent },
    { path: 'hello', component: HelloComponent }
  ])],
  bootstrap: [AppComponent]
})
export class AppModule {}

