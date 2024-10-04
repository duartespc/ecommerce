package com.example.ecommerce.domain.cart;

import com.example.ecommerce.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProduct> cartProducts = new ArrayList<>();

    public Cart(User user) {
        this.user = user;
    }
}
