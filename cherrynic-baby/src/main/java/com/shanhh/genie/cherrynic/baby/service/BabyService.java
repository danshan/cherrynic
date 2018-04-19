package com.shanhh.genie.cherrynic.baby.service;

import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;

/**
 * @author shanhonghao
 * @since 2018-04-15 20:05
 */
public interface BabyService {

    /**
     * 标记大便
     */
    ResultModel<TaskResult> bowel(TaskQuery query);

    /**
     * 标记小便
     */
    ResultModel<TaskResult> urinate(TaskQuery query);

    /**
     * 播放儿歌
     *
     * @param query
     * @return
     */
    ResultModel<TaskResult> playSongs(TaskQuery query);

    /**
     * 亲喂宝宝
     *
     * @param query
     * @return
     */
    ResultModel<TaskResult> breastFeed(TaskQuery query);

    /**
     * 奶瓶喂奶
     *
     * @param query
     * @return
     */
    ResultModel<TaskResult> bottleFeed(TaskQuery query);
}
