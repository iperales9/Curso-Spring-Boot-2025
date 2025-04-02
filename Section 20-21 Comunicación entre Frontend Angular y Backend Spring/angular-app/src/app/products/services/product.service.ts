import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { map, Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';


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

  constructor(private http: HttpClient) { }

  private url: string = 'http://localhost:8080/products'

  findAll(): Observable<Product[]> {
    //return of(this.products);
    return this.http.get<Product[]>(this.url).pipe(
      map((response: any) => response._embedded.products as Product[])
    );

  }

  create(product: Product): Observable<Product> {
    return this.http.post<Product>(this.url, product)
  }


  update(product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.url}/${product.id}`, product)
  }

  remove(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`)
  }

}