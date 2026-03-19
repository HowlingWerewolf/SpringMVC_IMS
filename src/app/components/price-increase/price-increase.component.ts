import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PriceIncreaseService } from '../../services/price-increase.service';

@Component({
  selector: 'app-price-increase',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  template: `
    <div>
      <h1 class="mb-4">Increase Prices</h1>

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
                <label for="percentage">Increase (%):</label>
              </td>
              <td width="20%">
                <input
                  id="percentage"
                  type="number"
                  class="form-control"
                  formControlName="percentage"
                  min="1"
                  max="50"
                  [class.is-invalid]="isFieldInvalid('percentage')">
              </td>
              <td width="40%">
                <div class="error" *ngIf="isFieldInvalid('percentage')">
                  <div *ngIf="form.get('percentage')?.errors?.['required']">
                    Percentage is required
                  </div>
                  <div *ngIf="form.get('percentage')?.errors?.['min']">
                    Percentage must be greater than 0
                  </div>
                  <div *ngIf="form.get('percentage')?.errors?.['max']">
                    Percentage cannot exceed 50
                  </div>
                </div>
              </td>
              <td width="20%">
                <button type="submit" class="btn btn-primary" [disabled]="form.invalid || submitting">
                  {{ submitting ? 'Increasing...' : 'Increase' }}
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
export class PriceIncreaseComponent {
  form: FormGroup;
  submitting = false;
  errorMessage = '';
  successMessage = '';

  constructor(
    private fb: FormBuilder,
    private priceIncreaseService: PriceIncreaseService,
    private router: Router
  ) {
    this.form = this.fb.group({
      percentage: [20, [Validators.required, Validators.min(1), Validators.max(50)]]
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

      this.priceIncreaseService.increasePrice({
        percentage: parseInt(this.form.get('percentage')?.value)
      }).subscribe(
        () => {
          this.successMessage = 'Prices increased successfully!';
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

