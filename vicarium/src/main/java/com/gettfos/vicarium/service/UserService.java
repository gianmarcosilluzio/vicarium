package com.gettfos.vicarium.service;

import java.util.List;

import com.gettfos.vicarium.model.User;

public interface UserService {
	User signinUser(User user);
	User loginUserByEmail(String email, String password);
	User updateUser(User user);
	List<User> getAllUser();
	User getUserById(Integer id);
	User getUserByFacebookId(String facebookId);
}
