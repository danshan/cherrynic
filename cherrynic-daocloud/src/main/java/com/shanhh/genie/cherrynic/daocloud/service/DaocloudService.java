package com.shanhh.genie.cherrynic.daocloud.service;

import com.shanhh.genie.cherrynic.daocloud.dto.DaocloudAppDTO;

import java.util.List;

/**
 * @author shanhonghao
 * @since 2018-04-15 12:31
 */
public interface DaocloudService {
    List<DaocloudAppDTO> findApps();

    List<DaocloudAppDTO> findAppsByStatus(String status);
}
