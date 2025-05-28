package com.kob.backend.service.impl.user.cart;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.CartItemMapper;
import com.kob.backend.pojo.CartItem;
import com.kob.backend.pojo.CartItemDTO;
import com.kob.backend.pojo.User;
import com.kob.backend.service.cart.CartService;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public List<CartItemDTO> getCartList() {

        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl login = (UserDetailsImpl) authentication.getPrincipal();
        User user = login.getUser();

//        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_id", user.getId());
        return cartItemMapper.getCartItemWithProduct(user.getId());
    }

    @Override
    public Map<String, String> addToCart(CartItem cartItem) {
        Map<String, String> map = new HashMap<>();
        try {
            // 检查是否已存在相同商品
            QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", cartItem.getUserId())
                    .eq("product_id", cartItem.getProductId());
            CartItem existingItem = cartItemMapper.selectOne(queryWrapper);

            if (existingItem != null) {
                // 更新数量
                existingItem.setQuantity(existingItem.getQuantity() + cartItem.getQuantity());
                cartItemMapper.updateById(existingItem);
            } else {
                // 新增商品
                cartItemMapper.insert(cartItem);
            }
            map.put("error_message", "success");
        } catch (Exception e) {
            map.put("error_message", e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, String> updateCartItem(CartItem cartItem) {
        Map<String, String> map = new HashMap<>();
        try {
            cartItemMapper.updateById(cartItem);
            map.put("error_message", "success");
        } catch (Exception e) {
            map.put("error_message", e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, String> deleteCartItem(BigInteger id) {
        Map<String, String> map = new HashMap<>();
        try {
            cartItemMapper.deleteById(id);
            map.put("error_message", "success");
        } catch (Exception e) {
            map.put("error_message", e.getMessage());
        }
        return map;
    }

    @Override
    public Map<String, String> clearCart() {

        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl login = (UserDetailsImpl) authentication.getPrincipal();
        User user = login.getUser();

        Map<String, String> map = new HashMap<>();
        try {
            QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", user.getId());
            cartItemMapper.delete(queryWrapper);
            map.put("error_message", "success");
        } catch (Exception e) {
            map.put("error_message", e.getMessage());
        }
        return map;
    }
} 