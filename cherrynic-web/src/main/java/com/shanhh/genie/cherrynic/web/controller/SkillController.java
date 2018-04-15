package com.shanhh.genie.cherrynic.web.controller;

import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.trans.MetaFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author shanhonghao
 * @since 2018-04-14 15:07
 */
@Slf4j
public class SkillController {

    public void genieCall(@RequestBody String taskQuery) {
        log.info("taskQuery: {}", taskQuery);
        TaskQuery query = MetaFormat.parseToQuery(taskQuery);

    }
}
