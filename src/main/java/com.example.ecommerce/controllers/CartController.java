package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.cart.Cart;
import com.example.ecommerce.domain.cart.CartProduct;
import com.example.ecommerce.domain.user.User;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public Cart createCart(@RequestBody String email) {
        return cartService.createCart(email);
    }

    @GetMapping("/list")
    public Iterable<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{cartId}/products")
    public List<CartProduct> getCartProducts(@PathVariable Long cartId) {
        return cartService.getCartProducts(cartId);
    }

    @PostMapping("/{cartId}/products")
    public Cart addProductToCart(@PathVariable Long cartId, @RequestBody CartProduct cartProduct) {
        return cartService.addProductToCart(cartId, cartProduct);
    }
}
