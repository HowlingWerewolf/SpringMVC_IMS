import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-products',
  template: `
    <h1>Products</h1>
    <table *ngIf="products?.length">
      <tr><th>Name</th><th>Price</th><th></th></tr>
      <tr *ngFor="let p of products">
        <td>{{p.description}}</td>
        <td>{{p.price | currency}}</td>
        <td><button (click)="removeProduct(p.id)">Remove</button></td>
      </tr>
    </table>

    <div *ngIf="!products">Loading...</div>

    <h2>Add product</h2>
    <form (ngSubmit)="addProduct(); $event.preventDefault();">
      <label>
        Description
        <input [(ngModel)]="newDescription" name="description" />
      </label>
      <label>
        Price
        <input type="number" [(ngModel)]="newPrice" name="price" />
      </label>
      <button type="submit">Add</button>
    </form>
  `
})
export class ProductsComponent implements OnInit {
  products: any[] | null = null;
  newDescription = '';
  newPrice: number | null = null;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchProducts();
  }

  fetchProducts(): void {
    this.http.get<any>(`${environment.apiBase}/hello`).subscribe(r => this.products = r.products || []);
  }

  addProduct(): void {
    if (!this.newDescription || this.newPrice == null) return;
    const payload = { description: this.newDescription, price: Number(this.newPrice) };
    this.http.post<any>(`${environment.apiBase}/productadd`, payload).subscribe({
      next: () => {
        this.newDescription = '';
        this.newPrice = null;
        this.fetchProducts();
      },
      error: (err) => console.error('Add product failed', err)
    });
  }

  removeProduct(id: number): void {
    if (id == null) return;
    this.http.delete<any>(`${environment.apiBase}/product/${id}`).subscribe({
      next: () => this.fetchProducts(),
      error: (err) => console.error('Delete product failed', err)
    });
  }
}

