import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-product-delete',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <div>
      <h1 class="mb-4">Delete Product</h1>

      <div *ngIf="errorMessage" class="alert alert-error">
        {{ errorMessage }}
      </div>

      <div *ngIf="loading" class="alert alert-info">
        Loading products...
      </div>

      <div class="table-responsive" *ngIf="!loading && products.length > 0">
        <table class="table table-striped">
          <tbody>
            <form *ngFor="let product of products" [formGroup]="forms[product.id!]" (ngSubmit)="onDelete(product)">
              <tr>
                <td align="right" width="20%">Description (name of product):</td>
                <td width="20%">{{ product.description }}</td>
                <td align="right" width="20%">Price:</td>
                <td width="20%">${{ product.price | number: '1.2-2' }}</td>
                <td width="20%">
                  <button type="submit" class="btn btn-danger btn-sm" [disabled]="deletingId === product.id">
                    {{ deletingId === product.id ? 'Deleting...' : 'Delete' }}
                  </button>
                </td>
              </tr>
            </form>
          </tbody>
        </table>
      </div>

      <div *ngIf="!loading && products.length === 0" class="alert alert-info">
        No products to delete.
      </div>
    </div>
  `,
  styles: [`
    .error {
      color: #dc3545;
    }
  `]
})
export class ProductDeleteComponent implements OnInit {
  products: Product[] = [];
  loading = true;
  errorMessage = '';
  deletingId: number | null = null;
  forms: { [key: number]: FormGroup } = {};

  constructor(
    private productService: ProductService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  private loadProducts(): void {
    this.productService.getProducts().subscribe(
      (data: Product[]) => {
        this.products = data;
        this.products.forEach(product => {
          this.forms[product.id!] = this.fb.group({});
        });
        this.loading = false;
      },
      (error) => {
        this.errorMessage = error.message;
        this.loading = false;
      }
    );
  }

  onDelete(product: Product): void {
    if (product.id && confirm(`Are you sure you want to delete "${product.description}"?`)) {
      this.deletingId = product.id;
      this.productService.deleteProduct(product.id).subscribe(
        () => {
          this.products = this.products.filter(p => p.id !== product.id);
          this.deletingId = null;
        },
        (error) => {
          this.errorMessage = error.message;
          this.deletingId = null;
        }
      );
    }
  }
}

