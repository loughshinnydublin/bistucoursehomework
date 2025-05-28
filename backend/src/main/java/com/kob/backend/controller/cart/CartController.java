package com.kob.backend.controller.cart;

import com.kob.backend.pojo.CartItem;
import com.kob.backend.pojo.CartItemDTO;
import com.kob.backend.pojo.User;
import com.kob.backend.service.cart.CartService;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/list")
    public List<CartItemDTO> getCartList() {
        return cartService.getCartList();
    }

    @PostMapping("/add")
    public Map<String, String> addToCart(@RequestBody Map<String, String> data) {
        CartItem cartItem = new CartItem();

        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl login = (UserDetailsImpl) authentication.getPrincipal();
        User user = login.getUser();

        cartItem.setUserId(user.getId());
        cartItem.setQuantity(1);
        Date now = new Date();
        cartItem.setCreateTime(now);
        cartItem.setProductId(new BigInteger(data.get("productId")));
        return cartService.addToCart(cartItem);
    }

    @PostMapping("/update")
    public Map<String, String> updateCartItem(@RequestBody CartItem cartItem) {
        return cartService.updateCartItem(cartItem);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, String> deleteCartItem(@PathVariable BigInteger id) {
        return cartService.deleteCartItem(id);
    }

    @DeleteMapping("/clear/")
    public Map<String, String> clearCart() {
        return cartService.clearCart();
    }
} 