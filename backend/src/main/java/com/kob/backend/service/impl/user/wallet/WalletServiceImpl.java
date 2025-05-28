package com.kob.backend.service.impl.user.wallet;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.WalletMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.pojo.Wallet;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletMapper walletMapper;

    @Override
    public Wallet getWalletInfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl login = (UserDetailsImpl) authentication.getPrincipal();
        User user = login.getUser();

        QueryWrapper<Wallet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());

        Wallet wallet = walletMapper.selectOne(queryWrapper);
        if (wallet == null) {
            // Create new wallet if not exists
            wallet = new Wallet();
            wallet.setUserId(user.getId());
            wallet.setBalance(0.0);
            wallet.setPoints(0);
            walletMapper.insert(wallet);
        }
        return wallet;
    }

    @Override
    @Transactional
    public Map<String, String> recharge(Double amount) {
        Map<String, String> map = new HashMap<>();
        try {
            Wallet wallet = getWalletInfo();
            wallet.setBalance(wallet.getBalance() + amount);
            walletMapper.updateById(wallet);
            map.put("error_message", "success");
        } catch (Exception e) {
            map.put("error_message", e.getMessage());
        }
        return map;
    }

    @Override
    @Transactional
    public Map<String, String> deduct(Double amount) {
        Map<String, String> map = new HashMap<>();
        try {
            Wallet wallet = getWalletInfo();
            if (wallet.getBalance() < amount) {
                map.put("error_message", "余额不足");
                return map;
            }
            wallet.setBalance(wallet.getBalance() - amount);
            walletMapper.updateById(wallet);
            map.put("error_message", "success");
        } catch (Exception e) {
            map.put("error_message", e.getMessage());
        }
        return map;
    }

    @Override
    @Transactional
    public Map<String, String> addPoints(double points) {
        Map<String, String> map = new HashMap<>();
        try {
            Wallet wallet = getWalletInfo();
            wallet.setPoints(wallet.getPoints() + points);
            walletMapper.updateById(wallet);
            map.put("error_message", "success");
        } catch (Exception e) {
            map.put("error_message", e.getMessage());
        }
        return map;
    }


    @Override
    @Transactional
    public Map<String, String> usePoints(double points) {
        Map<String, String> map = new HashMap<>();
        try {
            Wallet wallet = getWalletInfo();
            if (wallet.getPoints() < points) {
                map.put("error_message", "积分不足");
                return map;
            }
            wallet.setPoints(wallet.getPoints() - points);
            walletMapper.updateById(wallet);
            map.put("error_message", "success");
        } catch (Exception e) {
            map.put("error_message", e.getMessage());
        }
        return map;
    }

    @Override
    public void checkout(Double price, BigInteger merchantId) {
        //if不存在wallet 则先创建
        QueryWrapper<Wallet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", merchantId);
        Wallet wallet = walletMapper.selectOne(queryWrapper);
        wallet.setBalance(wallet.getBalance() + price);
    }
} 