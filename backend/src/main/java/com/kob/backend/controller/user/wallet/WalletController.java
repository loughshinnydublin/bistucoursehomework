package com.kob.backend.controller.user.wallet;

import com.kob.backend.pojo.Wallet;
import com.kob.backend.service.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/info")
    public Wallet getWalletInfo() {
        return walletService.getWalletInfo();
    }

    @PostMapping("/recharge")
    public Map<String, String> recharge(@RequestParam Double amount) {
        return walletService.recharge(amount);
    }

    @PostMapping("/deduct")
    public Map<String, String> deduct(@RequestParam Double amount) {
        return walletService.deduct(amount);
    }

    @PostMapping("/add-points")
    public Map<String, String> addPoints(@RequestParam Integer points) {
        return walletService.addPoints(points);
    }

    @PostMapping("/use-points")
    public Map<String, String> usePoints(@RequestParam Integer points) {
        return walletService.usePoints(points);
    }
} 