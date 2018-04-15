package com.shanhh.genie.cherrynic.baby.service;

import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;

/**
 * @author shanhonghao
 * @since 2018-04-15 20:05
 */
public interface BabyService {

    ResultModel<TaskResult> markAction(TaskQuery query);

    /**
     * 标记大便
     */
    ResultModel<TaskResult> markBowel(TaskQuery query);

    /**
     * 标记小便
     */
    ResultModel<TaskResult> markUrinate(TaskQuery query);

    /**
     * 标记喂奶
     */
    ResultModel<TaskResult> markNursing(TaskQuery query);

}
