package com.shanhh.genie.cherrynic.web.controller;

import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.alibaba.da.coin.ide.spi.trans.MetaFormat;
import com.shanhh.genie.cherrynic.baby.service.BabyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author shanhonghao
 * @since 2018-04-15 20:17
 */
@RestController
@Slf4j
@RequestMapping(value = "v1/skills/baby", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BabyController {

    @Resource
    private BabyService babyService;

    @RequestMapping(value = "markAction", method = RequestMethod.POST)
    public ResultModel<TaskResult> markBowel(@RequestBody String taskQuery) {
        log.info("taskQuery: {}", taskQuery);
        TaskQuery query = MetaFormat.parseToQuery(taskQuery);
        return babyService.markAction(query);
    }

    @RequestMapping(value = "playSongs", method = RequestMethod.POST)
    public ResultModel<TaskResult> playSongs(@RequestBody String taskQuery) {
        log.info("taskQuery: {}", taskQuery);
        TaskQuery query = MetaFormat.parseToQuery(taskQuery);
        return babyService.playSongs(query);
    }
}
