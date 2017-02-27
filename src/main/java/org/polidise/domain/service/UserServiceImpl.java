package org.polidise.domain.service;

import org.polidise.controller.command.NewUserCmd;
import org.polidise.domain.User;
import org.polidise.persistence.entity.UserEntity;
import org.polidise.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstactServiceImpl<User, UserRepository, UserEntity> implements UserService {

	@Autowired
	public UserServiceImpl(UserRepository repo) {
		super(repo);
	}

	@Override protected User init(UserEntity entity) {
		return new User(entity.getUsername(), entity.getPasswordHash(), entity.getActive());
	}

	@Override public User createUser(NewUserCmd command) {

		return null;
	}
}
