package com.shanhh.genie.cherrynic.daocloud.repo.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author shanhonghao
 * @since 2018-04-15 13:50
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DaocloudAppResp {
    private List<DaocloudApp> app;
}
