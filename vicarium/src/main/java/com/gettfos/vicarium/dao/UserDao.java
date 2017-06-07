package com.gettfos.vicarium.dao;

import java.util.List;

import com.gettfos.vicarium.model.User;

public interface UserDao {
	User readById(Integer id);
	User readByEmail(String email);
	User readByFacebookId(String facebookId);
	List<User> readAll();
	User createUser(User user);
	User updateUser(User user);
}
