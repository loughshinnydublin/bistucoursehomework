package com.kob.backend.service.user.product;


import com.kob.backend.pojo.Product;

import java.util.List;

public interface GetListService {
    List<Product> getList();    //返回对应userid的表，需要前端提供token（查询该用户（商家）的所有商品）

    List<Product> getListByPrice(double price);    //所有价格大于指定数值的产品列表
    List<Product> getListByName(String name);  //按名称排序
    List<Product> getListByComment(String comment);    //按评价
}