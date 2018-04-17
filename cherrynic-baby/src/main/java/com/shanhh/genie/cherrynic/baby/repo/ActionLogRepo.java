package com.shanhh.genie.cherrynic.baby.repo;

import com.shanhh.genie.cherrynic.baby.repo.entity.ActionLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author shanhonghao
 * @since 2018-04-16 17:48
 */
@Repository("actionLogRepo")
public interface ActionLogRepo extends CrudRepository<ActionLog, Integer> {
}
