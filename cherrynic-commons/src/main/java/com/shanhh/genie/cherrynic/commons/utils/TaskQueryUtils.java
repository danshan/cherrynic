package com.shanhh.genie.cherrynic.commons.utils;

import com.alibaba.da.coin.ide.spi.meta.SlotEntity;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shanhh.genie.cherrynic.commons.bean.TimeRange;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shanhonghao
 * @since 2018-04-19 11:27
 */
@Component
@Slf4j
public class TaskQueryUtils {

    @Resource
    private ObjectMapper objectMapper;

    public Map<String, String> collectParams(TaskQuery query) {
        if (query.getSlotEntities() == null) {
            return Collections.emptyMap();
        }
        return query.getSlotEntities().stream().collect(Collectors.toMap(SlotEntity::getIntentParameterName, SlotEntity::getStandardValue));
    }


    public Date getTimeParam(TaskQuery query, String paramName) {
        return getTimeParam(collectParams(query), paramName);
    }


    public Date getTimeParam(Map<String, String> params, String paramName) {
        String timeParam = params.get(paramName);
        if (StringUtils.isBlank(timeParam)) {
            return new Date();
        }

        try {
            Map<String, String> time = objectMapper.readValue(timeParam, Map.class);
            return formatTimeString(time.get("iDateString"));
        } catch (Exception e) {
            log.error("format time failed, {}", timeParam);
            return new Date();
        }
    }

    private Date formatTimeString(String timeString) {
        LocalTime localTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm:ss"));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, localTime.getHour());
        cal.set(Calendar.MINUTE, localTime.getMinute());
        cal.set(Calendar.SECOND, localTime.getSecond());
        return cal.getTime();
    }

    public int getInt(TaskQuery query, String paramName) {
        return getInt(collectParams(query), paramName);
    }

    public int getInt(Map<String, String> params, String paramName) {
        String value = params.get(paramName);
        if (StringUtils.isBlank(value)) {
            return 0;
        } else {
            return Integer.parseInt(value);
        }
    }

    public TimeRange getTimeRangeParam(TaskQuery query, String paramName) {
        return getTimeRangeParam(collectParams(query), paramName);
    }

    public TimeRange getTimeRangeParam(Map<String, String> params, String paramName) {
        String timerangeParam = params.get(paramName);
        Date now = new Date();
        if (StringUtils.isBlank(timerangeParam)) {
            return new TimeRange(now, now);
        }

        try {
            Map<String, String> time = objectMapper.readValue(timerangeParam, Map.class);
            Date startTime = formatTimeString(time.get("startDateTime"));
            Date endTime = formatTimeString(time.get("endDateTime"));
            return new TimeRange(startTime, endTime);
        } catch (Exception e) {
            log.error("format time failed, {}", timerangeParam);
            return new TimeRange(now, now);
        }
    }

}
