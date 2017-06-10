package com.getfos.vicarium.dao;

import java.util.List;

import com.getfos.vicarium.model.User;

public interface UserDAO {
	User readById(Integer id);
	User readByEmail(String email);
	User readByFacebookId(String facebookId);
	List<User> readAll();
	User createUser(User user);
	User updateUser(User user);
}
