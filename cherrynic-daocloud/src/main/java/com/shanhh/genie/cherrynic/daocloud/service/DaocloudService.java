package com.shanhh.genie.cherrynic.daocloud.service;

import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.shanhh.genie.cherrynic.daocloud.dto.DaocloudAppDTO;

import java.util.List;

/**
 * @author shanhonghao
 * @since 2018-04-15 12:31
 */
public interface DaocloudService {
    ResultModel<TaskResult> findApps(TaskQuery query);
}
