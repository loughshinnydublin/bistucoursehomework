package com.kob.backend.service.impl.user.product;

import com.kob.backend.mapper.ProductMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.Product;
import com.kob.backend.pojo.User;
import com.kob.backend.service.user.product.auditProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class auditProductServiceImpl implements auditProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public boolean auditProduct(BigInteger productId, String newStatus) {
        if (!newStatus.equals("APPROVED") && !newStatus.equals("REJECTED")) {
            throw new IllegalArgumentException("状态必须为 APPROVED 或 REJECTED");
        }

        Product product = productMapper.selectById(productId);
        if (product == null) {
            throw new RuntimeException("用户不存在");
        }

        product.setStatus(newStatus);
        return productMapper.updateById(product) > 0;
    }


    //todo:待审核用户列表  //前端展示所有待审核用户，输入id和newstatus
    @Override
    public List<Product> getPendingProducts() {
        System.out.println("获取pendinglist");
        return productMapper.selectPendingProducts();
    }
}
