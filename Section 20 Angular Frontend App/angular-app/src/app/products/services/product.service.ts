import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { Observable, of } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private products: Product[] = [
    {
      id: 1,
      name: 'Mesa comedor',
      description: 'Excelente para el comedor',
      price: 700
    },
    {
      id: 2,
      name: 'Teclado',
      description: 'Excelente para jugar',
      price: 150
    }, {
      id: 3,
      name: 'Raton',
      description: 'Excelente para jugar',
      price: 300
    }
  ];

  constructor() { }

  findAll(): Observable<Product[]>{
    return of(this.products);
  }


}
