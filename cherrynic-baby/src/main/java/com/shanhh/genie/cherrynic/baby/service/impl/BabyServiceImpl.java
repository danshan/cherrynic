package com.shanhh.genie.cherrynic.baby.service.impl;

import com.alibaba.da.coin.ide.spi.meta.ExecuteCode;
import com.alibaba.da.coin.ide.spi.meta.ResultType;
import com.alibaba.da.coin.ide.spi.meta.SlotEntity;
import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.shanhh.genie.cherrynic.baby.repo.ActionLogRepo;
import com.shanhh.genie.cherrynic.baby.repo.entity.ActionLog;
import com.shanhh.genie.cherrynic.baby.service.BabyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shanhonghao
 * @since 2018-04-15 20:11
 */
@Service
@Slf4j
public class BabyServiceImpl implements BabyService {

    @Qualifier("actionLogRepo")
    @Autowired
    private ActionLogRepo actionLogRepo;

    private static final String ACTION_BOWEL = "bowel";
    private static final String ACTION_URINATE = "urinate";
    private static final String ACTION_NURSING = "nursing";


    @Override
    public ResultModel<TaskResult> markAction(TaskQuery query) {
        Map<String, String> params = collectParams(query);
        String action = params.get("action");
        if (StringUtils.isBlank(action)) {
            return errorResult("我好像没听明白宝宝做了什么");
        }

        switch (action) {
            case ACTION_BOWEL:
                return this.markBowel(query);
            case ACTION_URINATE:
                return this.markUrinate(query);
            case ACTION_NURSING:
                return this.markNursing(query);
            default:
                return errorResult("我好像没听明白宝宝做了什么");
        }
    }

    private ResultModel<TaskResult> errorResult(String message) {
        ResultModel<TaskResult> resultModel = new ResultModel<>();
        TaskResult result = new TaskResult();
        result.setResultType(ResultType.RESULT);
        result.setExecuteCode(ExecuteCode.SUCCESS);
        result.setReply(message);

        resultModel.setReturnCode("0");
        resultModel.setReturnValue(result);
        return resultModel;
    }

    private Map<String, String> collectParams(TaskQuery query) {
        if (query.getSlotEntities() == null) {
            return Collections.emptyMap();
        }
        return query.getSlotEntities().stream().collect(Collectors.toMap(SlotEntity::getIntentParameterName, SlotEntity::getStandardValue));
    }

    @Override
    public ResultModel<TaskResult> markBowel(TaskQuery query) {
        this.saveActionLog(ACTION_BOWEL, 0, null, null, null);
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
        this.saveActionLog(ACTION_URINATE, 0, null, null, null);
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
        this.saveActionLog(ACTION_NURSING, 0, null, null, null);
        ResultModel<TaskResult> resultModel = new ResultModel<>();
        TaskResult result = new TaskResult();
        result.setResultType(ResultType.RESULT);
        result.setExecuteCode(ExecuteCode.SUCCESS);
        result.setReply("宝宝喂奶时间记好啦");
        resultModel.setReturnCode("0");
        resultModel.setReturnValue(result);
        return resultModel;
    }

    private void saveActionLog(String action, int amount, Date startTime, Date endTime, String comment) {
        ActionLog actionLog = new ActionLog();
        actionLog.setAction(StringUtils.trimToEmpty(action));
        actionLog.setComment(StringUtils.trimToEmpty(comment));
        actionLogRepo.save(actionLog);
    }

}
