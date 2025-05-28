package com.kob.backend.service.impl.history;

import com.kob.backend.mapper.HistoryMapper;
import com.kob.backend.pojo.History;
import com.kob.backend.service.user.history.addHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class addHistoryServiceImpl implements addHistoryService {

    @Autowired
    HistoryMapper historyMapper;

    @Override
    public void addHistory(History history) {
        historyMapper.insert(history);
    }
}
