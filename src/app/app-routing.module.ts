import { Routes } from '@angular/router';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductAddComponent } from './components/product-add/product-add.component';
import { ProductDeleteComponent } from './components/product-delete/product-delete.component';
import { PriceIncreaseComponent } from './components/price-increase/price-increase.component';

export const routes: Routes = [
  { path: '', redirectTo: '/products', pathMatch: 'full' },
  { path: 'products', component: ProductListComponent },
  { path: 'add', component: ProductAddComponent },
  { path: 'delete', component: ProductDeleteComponent },
  { path: 'priceincrease', component: PriceIncreaseComponent }
];

