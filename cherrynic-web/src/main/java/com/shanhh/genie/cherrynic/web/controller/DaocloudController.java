package com.shanhh.genie.cherrynic.web.controller;

import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.alibaba.da.coin.ide.spi.trans.MetaFormat;
import com.shanhh.genie.cherrynic.daocloud.service.DaocloudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author shanhonghao
 * @since 2018-04-14 15:07
 */
@RestController
@Slf4j
@RequestMapping(value = "v1/skills/daocloud", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DaocloudController {

    @Resource
    private DaocloudService daocloudService;

    @RequestMapping(value = "apps", method = RequestMethod.POST)
    public ResultModel<TaskResult> genieCall(@RequestBody String taskQuery) {
        log.info("taskQuery: {}", taskQuery);
        TaskQuery query = MetaFormat.parseToQuery(taskQuery);

        ResultModel<TaskResult> resultModel = new ResultModel<TaskResult>();
        daocloudService.findApps();

        TaskResult result = new TaskResult();
        resultModel.setReturnCode("0");
        resultModel.setReturnValue(result);
        return resultModel;
    }

}
