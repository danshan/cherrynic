package com.shanhh.genie.cherrynic.daocloud.repo;

import com.shanhh.genie.cherrynic.daocloud.dto.DaocloudAppDTO;

import java.util.List;

/**
 * @author shanhonghao
 * @since 2018-04-15 12:31
 */
public interface DaocloudRepo {
    List<DaocloudAppDTO> findApps();
}
