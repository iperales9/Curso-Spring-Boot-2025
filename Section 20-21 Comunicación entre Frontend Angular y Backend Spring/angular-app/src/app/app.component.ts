import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { ProductComponent } from "./products/components/product/product.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [CommonModule, ProductComponent]
})
export class AppComponent {
  title = 'angular-app';
  enabled: boolean = false;

  courses: string[] = ['Angular', 'React', 'Spring Boot'];

  setEnabled(): void {
    console.log(this.enabled);
    this.enabled = this.enabled ? false : true;
  }
}