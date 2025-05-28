package com.kob.backend.service.user.product;


import com.kob.backend.pojo.ProductImage;

import java.math.BigInteger;
import java.util.List;

public interface GetImageService {
    List<ProductImage> getImage(BigInteger productId);
}
