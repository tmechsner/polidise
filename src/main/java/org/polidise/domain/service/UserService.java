package org.polidise.domain.service;

import org.polidise.controller.command.NewUserCmd;
import org.polidise.domain.User;
import org.polidise.persistence.entity.UserEntity;

/**
 * Created by timo on 27.02.17.
 */
public interface UserService extends AbstactService<User, UserEntity> {

	User createUser(NewUserCmd command);

}
