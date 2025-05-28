package com.kob.backend.service.user.product;

import com.kob.backend.pojo.Product;
import com.kob.backend.pojo.User;

import java.math.BigInteger;
import java.util.List;

public interface auditProductService {
    boolean auditProduct(BigInteger productId, String newStatus);
    List<Product> getPendingProducts();
}
