import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-products',
  template: `
    <h1>Products</h1>
    <table *ngIf="products?.length">
      <tr><th>Name</th><th>Price</th></tr>
      <tr *ngFor="let p of products">
        <td>{{p.description}}</td>
        <td>{{p.price | currency}}</td>
      </tr>
    </table>
    <div *ngIf="!products">Loading...</div>
  `
})
export class ProductsComponent {
  products: any[] | null = null;
  constructor(private http: HttpClient) {
    this.http.get<any>(`${environment.apiBase}/hello`).subscribe(r => this.products = r.products || []);
  }
}

