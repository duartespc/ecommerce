package com.example.ecommerce.service;


import com.example.ecommerce.domain.cart.Cart;
import com.example.ecommerce.domain.cart.CartProduct;
import com.example.ecommerce.domain.user.User;
import com.example.ecommerce.repositories.CartRepository;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    public Cart createCart(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = new Cart(user);
        return cartRepository.save(cart);
    }

    public Iterable<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public List<CartProduct> getCartProducts(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        return cart.getCartProducts();
    }

    public Cart addProductToCart(Long cartId, CartProduct cartProduct) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        cartProduct.setCart(cart);
        cart.getCartProducts().add(cartProduct);
        return cartRepository.save(cart);
    }
}
