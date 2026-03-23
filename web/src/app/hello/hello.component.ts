import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Component({
    selector: 'app-hello',
    template: `<h2>{{message}}</h2>`,
    standalone: false
})
export class HelloComponent {
  message = 'Loading...';
  constructor(private http: HttpClient) {
    this.http.get<any>(`${environment.apiBase}/hello`).subscribe(r => this.message = r.message || 'no message');
  }
}

