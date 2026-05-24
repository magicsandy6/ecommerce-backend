package com.ecommerce.controller;

import com.ecommerce.entity.Cart;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam Integer quantity) {
        Cart cart = cartService.addToCart(userId, productId, quantity);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cart>> getCartByUserId(@PathVariable Long userId) {
        List<Cart> cartItems = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long cartId) {
        cartService.removeFromCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{cartId}/quantity/{quantity}")
    public ResponseEntity<Cart> updateCartQuantity(@PathVariable Long cartId, @PathVariable Integer quantity) {
        Cart updatedCart = cartService.updateCartQuantity(cartId, quantity);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }
}
