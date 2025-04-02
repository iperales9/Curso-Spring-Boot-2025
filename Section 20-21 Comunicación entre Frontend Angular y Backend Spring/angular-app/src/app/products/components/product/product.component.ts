import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';
import { CommonModule } from '@angular/common';
import { FormComponent } from "../form/form.component";

@Component({
  selector: 'app-product',
  imports: [CommonModule, FormComponent],
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
      this.service.update(product).subscribe(prodUpdate => {

        this.products = this.products.map(prod => {
          if (prod.id == product.id) {
            return { ...prodUpdate };
          }
          return prod;
        });
      });

    } else {
      //product.id = new Date().getTime();
      this.service.create(product).subscribe(prod => {
        this.products.push({ ...prod })
      });
    }
  }

  onRemoveProduct(id: number): void {
    this.service.remove(id).subscribe(() => {
      this.products = this.products.filter(product => product.id != id)
    });
  }

  onUpdateProduct(productRow: Product): void {
    this.productSelected = { ...productRow };
  }

}
