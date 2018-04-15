package com.shanhh.genie.cherrynic.daocloud.service.impl;

import com.shanhh.genie.cherrynic.daocloud.dto.DaocloudAppDTO;
import com.shanhh.genie.cherrynic.daocloud.repo.DaocloudRepo;
import com.shanhh.genie.cherrynic.daocloud.service.DaocloudService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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
    public List<DaocloudAppDTO> findApps() {
        return daocloudRepo.findApps();
    }

    @Override
    public List<DaocloudAppDTO> findAppsByStatus(String status) {
        List<DaocloudAppDTO> apps = daocloudRepo.findApps();
        if (StringUtils.isNotBlank(status)) {
            return apps.stream().filter(app -> status.equals(app.getState())).collect(Collectors.toList());
        } else {
            return apps;
        }
    }
}
