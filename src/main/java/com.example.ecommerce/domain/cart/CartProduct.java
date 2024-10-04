package com.example.ecommerce.domain.cart;

import com.example.ecommerce.domain.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct {

    @Id
    @Column(name = "cart_product_id")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @JsonIgnore
    private Product product;

    private int quantity;
}
