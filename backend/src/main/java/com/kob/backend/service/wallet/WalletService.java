package com.kob.backend.service.wallet;

import com.kob.backend.pojo.Wallet;

import java.math.BigInteger;
import java.util.Map;

public interface WalletService {
    Wallet getWalletInfo();
    Map<String, String> recharge(Double amount);
    Map<String, String> deduct(Double amount);//扣钱
    Map<String, String> addPoints(double points);
    Map<String, String> usePoints(double points);

    void checkout(Double price, BigInteger merchantId);
}