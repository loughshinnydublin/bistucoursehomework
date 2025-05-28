package com.kob.backend.controller.history;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kob.backend.mapper.ProductMapper;
import com.kob.backend.pojo.History;
import com.kob.backend.pojo.HistoryVO;
import com.kob.backend.pojo.Product;
import com.kob.backend.pojo.ProductImage;
import com.kob.backend.service.user.history.GetHistoryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class GetHistoryListController {
//    @Autowired
//    private GetHistoryListService getHistoryListService;
//
//    @GetMapping("home/gethistorylist")
//    List<History> historyList() {
//        return getHistoryListService.getHistoryList();
//    }

    @Autowired
    GetHistoryListService getHistoryListService;
    @Autowired
    ProductMapper productMapper;

    @GetMapping("/api/gethistorylist")
    public Map<String, String> getHistory(
            @RequestParam BigInteger userId,
            @RequestParam int page,
            @RequestParam int size
    ) throws JsonProcessingException {
        Page<History> historyPage = getHistoryListService.page(
                new Page<>(page, size),
                new QueryWrapper<History>().eq("user_id", userId).orderByDesc("view_time")
        );

        List<HistoryVO> result = historyPage.getRecords().stream().map(history -> {
            Product product = productMapper.selectById(history.getProductId());
            ProductImage image = productMapper
                    .getProductImagesByProductId(history.getProductId())
                    .get(0);

            HistoryVO vo = new HistoryVO();
            vo.setViewTime(history.getViewTime());
            vo.setProductId(product.getId());
            vo.setProductName(product.getName());
            vo.setDescription(product.getDescription());
            vo.setImageUrl(image != null ? image.getImageUrl() : null);
            return vo;
        }).collect(Collectors.toList());

        int total = getHistoryListService.getHistoryList().size();

        Map<String, String> map = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);

        map.put("result", json);
        map.put("total", String.valueOf(total));

        System.out.println(map.get("result"));

        return map;
    }
}
