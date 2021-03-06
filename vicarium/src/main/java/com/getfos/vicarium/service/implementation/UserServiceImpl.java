package com.getfos.vicarium.service.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getfos.vicarium.dao.UserDAO;
import com.getfos.vicarium.model.User;
import com.getfos.vicarium.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;


	public User signinUser(User user) {
		User userDb = userDAO.readByFacebookId(user.getFacebookId());
		if(userDb == null){
			user.setLastAccess(new Date());
			user.setRegistrationDate(new Date());
			userDb = userDAO.createUser(user);
		}
		return userDb;
	}

	public User loginUserByEmail(String email, String password) {
		User user = userDAO.readByEmail(email);
		if(user != null && password.equals(user.getPassword())){
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
