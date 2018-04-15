package com.shanhh.genie.cherrynic.daocloud.repo.client;

import com.shanhh.genie.cherrynic.daocloud.repo.client.entity.DaocloudAppResp;
import feign.RequestLine;

/**
 * @author shanhonghao
 * @since 2018-04-15 12:01
 */
public interface DaocloudClient {
    @RequestLine("GET v1/apps")
    DaocloudAppResp findApps();
}
