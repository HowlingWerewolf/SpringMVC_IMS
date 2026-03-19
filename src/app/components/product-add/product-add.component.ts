import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'app-product-add',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <div>
      <h1 class="mb-4">Add Product</h1>

      <div *ngIf="successMessage" class="alert alert-success">
        {{ successMessage }}
      </div>

      <div *ngIf="errorMessage" class="alert alert-error">
        {{ errorMessage }}
      </div>

      <form [formGroup]="form" (ngSubmit)="onSubmit()" class="mb-4">
        <div class="table-responsive">
          <table class="table table-striped">
            <tr>
              <td align="right" width="20%">
                <label for="description">Description (name of product):</label>
              </td>
              <td width="20%">
                <input
                  id="description"
                  type="text"
                  class="form-control"
                  formControlName="description"
                  [class.is-invalid]="isFieldInvalid('description')">
              </td>
              <td width="20%">
                <div class="error" *ngIf="isFieldInvalid('description')">
                  <div *ngIf="form.get('description')?.errors?.['required']">
                    Description is required
                  </div>
                </div>
              </td>
              <td align="right" width="20%">
                <label for="price">Price:</label>
              </td>
              <td width="20%">
                <input
                  id="price"
                  type="number"
                  class="form-control"
                  formControlName="price"
                  step="0.01"
                  [class.is-invalid]="isFieldInvalid('price')">
              </td>
              <td width="10%">
                <div class="error" *ngIf="isFieldInvalid('price')">
                  <div *ngIf="form.get('price')?.errors?.['required']">
                    Price is required
                  </div>
                  <div *ngIf="form.get('price')?.errors?.['min']">
                    Price must be positive
                  </div>
                </div>
              </td>
              <td width="10%">
                <button type="submit" class="btn btn-primary" [disabled]="form.invalid || submitting">
                  {{ submitting ? 'Executing...' : 'Execute' }}
                </button>
              </td>
            </tr>
          </table>
        </div>
      </form>
    </div>
  `,
  styles: [`
    .error {
      color: #dc3545;
      font-size: 0.875em;
      margin-top: 0.25rem;
    }
    .is-invalid {
      border-color: #dc3545 !important;
    }
  `]
})
export class ProductAddComponent {
  form: FormGroup;
  submitting = false;
  errorMessage = '';
  successMessage = '';

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private router: Router
  ) {
    this.form = this.fb.group({
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0.01)]]
    });
  }

  isFieldInvalid(field: string): boolean {
    const control = this.form.get(field);
    return !!(control && control.invalid && (control.dirty || control.touched));
  }

  onSubmit(): void {
    if (this.form.valid) {
      this.submitting = true;
      this.errorMessage = '';
      this.successMessage = '';

      const product: Product = {
        description: this.form.get('description')?.value,
        price: parseFloat(this.form.get('price')?.value)
      };

      this.productService.addProduct(product).subscribe(
        () => {
          this.successMessage = 'Product added successfully!';
          this.submitting = false;
          setTimeout(() => this.router.navigate(['/products']), 1500);
        },
        (error) => {
          this.errorMessage = error.message;
          this.submitting = false;
        }
      );
    }
  }
}

