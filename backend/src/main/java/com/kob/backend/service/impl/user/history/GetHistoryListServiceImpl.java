package com.kob.backend.service.impl.user.history;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kob.backend.mapper.HistoryMapper;
import com.kob.backend.pojo.History;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.history.GetHistoryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GetHistoryListServiceImpl extends
        ServiceImpl<HistoryMapper, History> implements GetHistoryListService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public List<History> getHistoryList() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl login = (UserDetailsImpl) authentication.getPrincipal();
        User user = login.getUser();

        return historyMapper.getHistoryByUserId(user.getId());
    }
}
