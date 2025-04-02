import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { FormComponent } from "../form/form.component";

@Component({
  selector: 'app-product',
  imports: [CommonModule, RouterOutlet, FormComponent],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent implements OnInit {

  products: Product[] = [];

  productSelected: Product = new Product();

  constructor(private service: ProductService) { }

  ngOnInit(): void {
    this.service.findAll().subscribe(products =>
      this.products = products
    );
  }

  addProduct(product: Product): void {

    if (product.id > 0) {
      this.products = this.products.map(prod => {
        if (prod.id == product.id) {
          return { ...product };
        }
        return prod;
      })
    } else {
      product.id = new Date().getTime();
      this.products.push(product)
    }
  }

  onRemoveProduct(id: number): void {
    this.products = this.products.filter(product => product.id != id)
  }

  onUpdateProduct(productRow: Product): void {
    this.productSelected = { ...productRow };
  }

}
