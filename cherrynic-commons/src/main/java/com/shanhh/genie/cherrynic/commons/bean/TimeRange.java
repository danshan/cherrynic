package com.shanhh.genie.cherrynic.commons.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author shanhonghao
 * @since 2018-04-19 13:29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeRange {
    private Date startTime;
    private Date endTime;
}
