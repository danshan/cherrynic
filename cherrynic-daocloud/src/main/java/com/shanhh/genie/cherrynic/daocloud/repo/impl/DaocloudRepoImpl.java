package com.shanhh.genie.cherrynic.daocloud.repo.impl;

import com.shanhh.genie.cherrynic.daocloud.dto.DaocloudAppDTO;
import com.shanhh.genie.cherrynic.daocloud.repo.DaocloudRepo;
import com.shanhh.genie.cherrynic.daocloud.repo.client.DaocloudClient;
import com.shanhh.genie.cherrynic.daocloud.repo.client.entity.DaocloudApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shanhonghao
 * @since 2018-04-15 13:16
 */
@Repository
@Slf4j
public class DaocloudRepoImpl implements DaocloudRepo {

    @Resource
    private DaocloudClient daocloudClient;

    @Override
    public List<DaocloudAppDTO> findApps() {
        List<DaocloudApp> apps = daocloudClient.findApps().getApp();
        log.debug("findApps, {}", apps);
        return apps.stream().map(this::convert).collect(Collectors.toList());
    }

    private DaocloudAppDTO convert(DaocloudApp app) {
        DaocloudAppDTO dto = new DaocloudAppDTO();
        BeanUtils.copyProperties(app, dto);
        return dto;
    }
}
