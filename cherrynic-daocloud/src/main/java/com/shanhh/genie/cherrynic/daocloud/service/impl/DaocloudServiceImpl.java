package com.shanhh.genie.cherrynic.daocloud.service.impl;

import com.alibaba.da.coin.ide.spi.meta.ExecuteCode;
import com.alibaba.da.coin.ide.spi.meta.ResultType;
import com.alibaba.da.coin.ide.spi.meta.SlotEntity;
import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.google.common.base.Joiner;
import com.shanhh.genie.cherrynic.daocloud.dto.DaocloudAppDTO;
import com.shanhh.genie.cherrynic.daocloud.repo.DaocloudRepo;
import com.shanhh.genie.cherrynic.daocloud.service.DaocloudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shanhonghao
 * @since 2018-04-15 12:31
 */
@Service
@Slf4j
public class DaocloudServiceImpl implements DaocloudService {

    @Resource
    private DaocloudRepo daocloudRepo;

    @Override
    public ResultModel<TaskResult> findApps(TaskQuery query) {
        Map<String, String> params = collectParams(query);
        List<DaocloudAppDTO> apps = daocloudRepo.findApps();

        String status = params.get("status");
        if (StringUtils.isBlank(status)) {
            return formatResult(apps);
        } else {
            return formatResult(apps.stream().filter(app -> status.equals(app.getState())).collect(Collectors.toList()));
        }
    }

    private ResultModel<TaskResult> formatResult(List<DaocloudAppDTO> apps) {
        ResultModel<TaskResult> resultModel = new ResultModel<>();
        TaskResult result = new TaskResult();
        result.setReply(String.format("您有 %s 个应用, %s",
                apps.size(),
                Joiner.on(",").join(apps.stream().map(DaocloudAppDTO::getName).collect(Collectors.toList())))
        );

        result.setResultType(ResultType.RESULT);
        result.setExecuteCode(ExecuteCode.SUCCESS);

        resultModel.setReturnValue(result);
        resultModel.setReturnCode("0");
        return resultModel;
    }

    private Map<String, String> collectParams(TaskQuery query) {
        if (query.getSlotEntities() == null) {
            return Collections.emptyMap();
        }
        return query.getSlotEntities().stream().collect(Collectors.toMap(SlotEntity::getIntentParameterName, SlotEntity::getStandardValue));
    }
}
