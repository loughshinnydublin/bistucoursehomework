package com.kob.backend.service.cart;

import com.kob.backend.pojo.CartItem;
import com.kob.backend.pojo.CartItemDTO;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface CartService {
    List<CartItemDTO> getCartList();
    Map<String, String> addToCart(CartItem cartItem);
    Map<String, String> updateCartItem(CartItem cartItem);
    Map<String, String> deleteCartItem(BigInteger id);
    Map<String, String> clearCart();
} 