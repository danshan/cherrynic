package com.shanhh.genie.cherrynic.baby.service.impl;

import com.alibaba.da.coin.ide.spi.meta.ExecuteCode;
import com.alibaba.da.coin.ide.spi.meta.ResultType;
import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.shanhh.genie.cherrynic.baby.service.BabyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author shanhonghao
 * @since 2018-04-15 20:11
 */
@Service
@Slf4j
public class BabyServiceImpl implements BabyService {


    @Override
    public ResultModel<TaskResult> markBowel(TaskQuery query) {
        ResultModel<TaskResult> resultModel = new ResultModel<>();
        TaskResult result = new TaskResult();
        result.setResultType(ResultType.RESULT);
        result.setExecuteCode(ExecuteCode.SUCCESS);
        result.setReply("宝宝大便时间记好啦");

        resultModel.setReturnCode("0");
        resultModel.setReturnValue(result);
        return resultModel;
    }

    @Override
    public ResultModel<TaskResult> markUrinate(TaskQuery query) {
        ResultModel<TaskResult> resultModel = new ResultModel<>();
        TaskResult result = new TaskResult();
        result.setResultType(ResultType.RESULT);
        result.setExecuteCode(ExecuteCode.SUCCESS);
        result.setReply("宝宝小便时间记好啦");

        resultModel.setReturnCode("0");
        resultModel.setReturnValue(result);
        return resultModel;
    }

    @Override
    public ResultModel<TaskResult> markNursing(TaskQuery query) {
        ResultModel<TaskResult> resultModel = new ResultModel<>();
        TaskResult result = new TaskResult();
        result.setResultType(ResultType.RESULT);
        result.setExecuteCode(ExecuteCode.SUCCESS);
        result.setReply("宝宝喂奶时间记好啦");

        resultModel.setReturnCode("0");
        resultModel.setReturnValue(result);
        return resultModel;
    }

}
