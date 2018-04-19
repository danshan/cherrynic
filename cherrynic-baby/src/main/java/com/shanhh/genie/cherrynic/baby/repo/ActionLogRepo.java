package com.shanhh.genie.cherrynic.baby.repo;

import com.shanhh.genie.cherrynic.baby.repo.entity.ActionLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * @author shanhonghao
 * @since 2018-04-16 17:48
 */
public interface ActionLogRepo extends CrudRepository<ActionLog, Integer> {

    @Query("SELECT COUNT(id) FROM ActionLog WHERE action = :action AND startTime >= :fromTime AND startTime < :toTime")
    long countByActionAndStartTime(
            @Param("action") String action,
            @Param("fromTime") Date fromTime,
            @Param("toTime") Date toTime);
}
