import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div>
      <h1 class="mb-4">Products</h1>

      <div *ngIf="errorMessage" class="alert alert-error">
        {{ errorMessage }}
      </div>

      <div *ngIf="loading" class="alert alert-info">
        Loading products...
      </div>

      <table class="table table-striped" *ngIf="!loading && products.length > 0">
        <thead class="thead-dark">
          <tr>
            <th scope="col">Product Name</th>
            <th scope="col">Price</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let product of products">
            <td>{{ product.description }}</td>
            <td>${{ product.price | number: '1.2-2' }}</td>
          </tr>
        </tbody>
      </table>

      <div *ngIf="!loading && products.length === 0" class="alert alert-info">
        No products found.
      </div>
    </div>
  `,
  styles: []
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  loading = true;
  errorMessage = '';

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  private loadProducts(): void {
    this.productService.getProducts().subscribe(
      (data: Product[]) => {
        this.products = data;
        this.loading = false;
      },
      (error) => {
        this.errorMessage = error.message;
        this.loading = false;
      }
    );
  }
}

