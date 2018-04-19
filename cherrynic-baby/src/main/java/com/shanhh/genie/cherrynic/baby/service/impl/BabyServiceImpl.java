package com.shanhh.genie.cherrynic.baby.service.impl;

import com.alibaba.da.coin.ide.spi.meta.Action;
import com.alibaba.da.coin.ide.spi.meta.ExecuteCode;
import com.alibaba.da.coin.ide.spi.meta.ResultType;
import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.google.common.collect.Lists;
import com.shanhh.genie.cherrynic.baby.repo.ActionLogRepo;
import com.shanhh.genie.cherrynic.baby.repo.entity.ActionLog;
import com.shanhh.genie.cherrynic.baby.service.BabyService;
import com.shanhh.genie.cherrynic.commons.bean.TimeRange;
import com.shanhh.genie.cherrynic.commons.utils.TaskQueryUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * @author shanhonghao
 * @since 2018-04-15 20:11
 */
@Service
@Slf4j
public class BabyServiceImpl implements BabyService {

    @Resource
    private ActionLogRepo actionLogRepo;

    private static final String ACTION_BOWEL = "bowel";
    private static final String ACTION_URINATE = "urinate";
    private static final String ACTION_BOTTLE_FEED = "bottleFee";
    private static final String ACTION_BREAST_FEED = "breastFeed";

    private static final String[] SONG_IDS = {"4218", "4219"};

    @Resource
    private TaskQueryUtils taskQueryUtils;

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

    /**
     * 宝宝大便了
     *
     * @param query
     * @return
     */
    @Override
    public ResultModel<TaskResult> bowel(TaskQuery query) {
        Date startTime = taskQueryUtils.getTimeParam(query, "time");
        this.saveActionLog(ACTION_BOWEL, 0, startTime, startTime, null);
        long dailyCountBowel = countByAction(ACTION_BOWEL, startTime);
        ResultModel<TaskResult> resultModel = new ResultModel<>();
        TaskResult result = new TaskResult();
        result.setResultType(ResultType.RESULT);
        result.setExecuteCode(ExecuteCode.SUCCESS);
        result.setReply(String.format("好的, 这是今天第%s次拉臭臭", dailyCountBowel));

        resultModel.setReturnCode("0");
        resultModel.setReturnValue(result);
        return resultModel;
    }

    @Override
    public ResultModel<TaskResult> urinate(TaskQuery query) {
        Date startTime = taskQueryUtils.getTimeParam(query, "time");
        this.saveActionLog(ACTION_URINATE, 0, startTime, startTime, null);
        long dailyCountUrinate = countByAction(ACTION_URINATE, startTime);
        ResultModel<TaskResult> resultModel = new ResultModel<>();
        TaskResult result = new TaskResult();
        result.setResultType(ResultType.RESULT);
        result.setExecuteCode(ExecuteCode.SUCCESS);
        result.setReply(String.format("好的, 这是今天第%s次嘘嘘", dailyCountUrinate));

        resultModel.setReturnCode("0");
        resultModel.setReturnValue(result);
        return resultModel;
    }

    @Override
    public ResultModel<TaskResult> playSongs(TaskQuery query) {
        ResultModel<TaskResult> resultModel = new ResultModel<>();
        TaskResult result = new TaskResult();
        result.setResultType(ResultType.RESULT);
        result.setExecuteCode(ExecuteCode.SUCCESS);
        result.setReply("看我的");
        result.setActions(Lists.newArrayList(buildSongsAction()));
        resultModel.setReturnCode("0");
        resultModel.setReturnValue(result);
        return resultModel;
    }

    @Override
    public ResultModel<TaskResult> breastFeed(TaskQuery query) {
        TimeRange timeRange = taskQueryUtils.getTimeRangeParam(query, "time");
        this.saveActionLog(ACTION_BREAST_FEED, 0, timeRange.getStartTime(), timeRange.getEndTime(), null);
        long dailyCountBreastFeed = countByAction(ACTION_BREAST_FEED, timeRange.getStartTime());
        long dailyCountBottleFeed = countByAction(ACTION_BOTTLE_FEED, timeRange.getStartTime());

        ResultModel<TaskResult> resultModel = new ResultModel<>();
        TaskResult result = new TaskResult();
        result.setResultType(ResultType.RESULT);
        result.setExecuteCode(ExecuteCode.SUCCESS);
        result.setReply(String.format("好的, 这是今天第%s次亲喂, 一共喂了%s次", dailyCountBreastFeed, (dailyCountBottleFeed + dailyCountBreastFeed)));
        resultModel.setReturnCode("0");
        resultModel.setReturnValue(result);
        return resultModel;
    }

    @Override
    public ResultModel<TaskResult> bottleFeed(TaskQuery query) {
        Date startTime = taskQueryUtils.getTimeParam(query, "time");
        int amount = taskQueryUtils.getInt(query, "amount");
        this.saveActionLog(ACTION_BOTTLE_FEED, amount, startTime, startTime, null);
        long dailyCountBreastFeed = countByAction(ACTION_BREAST_FEED, startTime);
        long dailyCountBottleFeed = countByAction(ACTION_BOTTLE_FEED, startTime);

        ResultModel<TaskResult> resultModel = new ResultModel<>();
        TaskResult result = new TaskResult();
        result.setResultType(ResultType.RESULT);
        result.setExecuteCode(ExecuteCode.SUCCESS);
        result.setReply(String.format("好的, 这是今天第%s次奶瓶喂奶, 一共喂了%s次", dailyCountBottleFeed, (dailyCountBottleFeed + dailyCountBreastFeed)));
        resultModel.setReturnCode("0");
        resultModel.setReturnValue(result);
        return resultModel;
    }

    private long countByAction(String action, Date startTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date fromTime = cal.getTime();

        cal.add(Calendar.DATE, 1);
        Date toTime = cal.getTime();

        return actionLogRepo.countByActionAndStartTime(action, fromTime, toTime);
    }

    private Action buildSongsAction() {
        Action action = new Action("audioPlayGenieSource");
        action.addProperty("audioGenieId", SONG_IDS[RandomUtils.nextInt(SONG_IDS.length)]);
        return action;
    }

    private void saveActionLog(String action, int amount, Date startTime, Date endTime, String comment) {
        ActionLog actionLog = new ActionLog();
        actionLog.setAction(StringUtils.trimToEmpty(action));
        actionLog.setAmount(amount);
        actionLog.setComment(StringUtils.trimToEmpty(comment));
        actionLog.setStartTime(startTime);
        actionLog.setEndTime(endTime);
        actionLogRepo.save(actionLog);
    }

}
