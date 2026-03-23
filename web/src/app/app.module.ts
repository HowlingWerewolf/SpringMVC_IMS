import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { AppComponent } from './app.component';
import { HelloComponent } from './hello/hello.component';
import { ProductsComponent } from './products/products.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@NgModule({ declarations: [AppComponent, HelloComponent, ProductsComponent],
    bootstrap: [AppComponent], imports: [BrowserModule, FormsModule, RouterModule.forRoot([
            { path: '', component: ProductsComponent },
            { path: 'hello', component: HelloComponent }
        ])], providers: [provideHttpClient(withInterceptorsFromDi())] })
export class AppModule {}

