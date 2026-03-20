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

    <h2>Increase prices</h2>
    <form (ngSubmit)="increasePrices(); $event.preventDefault();">
      <label>
        Percentage (%)
        <input type="number" [(ngModel)]="increasePercentage" name="increase" />
      </label>
      <button type="submit">Increase</button>
    </form>
  `
})
export class ProductsComponent implements OnInit {
  products: any[] | null = null;
  newDescription = '';
  newPrice: number | null = null;
  increasePercentage: number | null = null;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchProducts();
  }

  fetchProducts(): void {
    // Try the configured apiBase first, then fall back to some common alternatives
    const attempts = [
      `${environment.apiBase}/products`,
      // Fallback to legacy hello endpoint if present
      `${environment.apiBase}/hello`,
      // Try without context path (backend mounted at root)
      `http://localhost:8080/api/products`,
      // Try relative path (useful when using dev-server proxy)
      `/api/products`,
      `/api/hello`
    ];

    const tryNext = (i: number) => {
      if (i >= attempts.length) {
        console.error('All product fetch attempts failed');
        this.products = [];
        return;
      }
      const url = attempts[i];
      this.http.get<any>(url).subscribe({
        next: (r) => this.products = Array.isArray(r) ? r : (r?.products || []),
        error: (err) => {
          // If 404 or other error, try the next candidate
          console.warn(`GET ${url} failed:`, err?.status || err);
          tryNext(i + 1);
        }
      });
    };

    tryNext(0);
  }

  addProduct(): void {
    if (!this.newDescription || this.newPrice == null) return;
    const payload = { description: this.newDescription, price: Number(this.newPrice) };
    // Some deployments use legacy /api/productadd; try that endpoint first, fall back to /api/products
    this.http.post<any>(`${environment.apiBase}/productadd`, payload).subscribe({
      next: () => {
        this.newDescription = '';
        this.newPrice = null;
        this.fetchProducts();
      },
      error: (err) => console.error('Add product failed', err)
    });
  }

  increasePrices(): void {
    if (this.increasePercentage == null) return;
    const pct = Number(this.increasePercentage);
    if (isNaN(pct) || pct <= 0 || pct > 50) {
      // Backend requires percentage between 1 and 50
      console.error('Percentage must be between 1 and 50');
      alert('Please enter a percentage between 1 and 50');
      return;
    }
    const payload = { percentage: pct };
    this.http.post<any>(`${environment.apiBase}/priceincrease`, payload).subscribe({
      next: () => {
        this.increasePercentage = null;
        this.fetchProducts();
      },
      error: (err) => console.error('Increase prices failed', err)
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

