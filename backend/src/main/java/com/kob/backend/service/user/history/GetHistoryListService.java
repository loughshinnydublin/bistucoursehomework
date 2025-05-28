package com.kob.backend.service.user.history;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kob.backend.pojo.History;

import java.util.List;

public interface GetHistoryListService extends IService<History> {
    List<History> getHistoryList();
}
