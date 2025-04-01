package com.ivan.curso.springboot.app.springboot_crud.entities;


import com.ivan.curso.springboot.app.springboot_crud.validation.IsExistDb;
import com.ivan.curso.springboot.app.springboot_crud.validation.IsRequiered;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @IsRequiered(message = "{IsRequired.product.name}")
    @Size(min = 3, max = 20)
    private String name;

    @Min(value = 10, message = "{Min.product.price}")
    @IsRequiered(message = " {NotNull.product.price}")
    private Integer price;

    @IsRequiered
    private String description;

    @IsRequiered
    @IsExistDb
    @Column(unique = true, nullable = false)
    private String sku;

    public Product() {

    }

    public Product(Integer price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", sku='" + sku + '\'' +
                '}';
    }
}
