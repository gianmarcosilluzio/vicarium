package com.gettfos.vicarium.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gettfos.vicarium.dao.UserDAO;
import com.gettfos.vicarium.model.User;
import com.gettfos.vicarium.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;


	public User signinUser(User user) {
		return userDAO.createUser(user);
	}

	public User loginUserByEmail(String email, String password) {
		User user = userDAO.readByEmail(email);
		if(password.equals(user.getPassword())){
			return user;
		}
		return null;
	}

	public User updateUser(User user) {
		return userDAO.updateUser(user);
	}

	public List<User> getAllUser() {
		return userDAO.readAll();
	}

	public User getUserById(Integer id) {
		return userDAO.readById(id);
	}

	public User getUserByFacebookId(String facebookId) {
		return userDAO.readByFacebookId(facebookId);
	}

}
