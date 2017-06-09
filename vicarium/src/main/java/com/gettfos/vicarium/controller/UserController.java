package com.gettfos.vicarium.controller;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gettfos.vicarium.model.User;
import com.gettfos.vicarium.service.UserService;

//@CrossOrigin("http://getfos.com")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;
	
	private HttpStatus httpStatus;
	
	@RequestMapping(value="/{userId}", method = RequestMethod.GET)
	ResponseEntity<User> getUserById(@PathVariable Integer userId) {
		User user = userService.getUserById(userId);
		user = userService.updateUser(user);
		if(user == null){
			httpStatus = HttpStatus.NOT_FOUND;
		}else{
			httpStatus = HttpStatus.OK;
		}
		return ResponseEntity.status(httpStatus).body(user);
	}
	
	@RequestMapping(value="/{facebookId}", method = RequestMethod.GET)
	ResponseEntity<User> getUserByFacebookId(@PathVariable String facebookId) {
		User user = userService.getUserByFacebookId(facebookId);	
		if(user == null){
			httpStatus = HttpStatus.NOT_FOUND;	
		}else{
			httpStatus = HttpStatus.OK;
		}
		return ResponseEntity.status(httpStatus).body(user);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<User>> getAllUser() {
		List<User> users = userService.getAllUser();
		if(users.isEmpty()){
			httpStatus = HttpStatus.NOT_FOUND;
		}else{
			httpStatus = HttpStatus.OK;
		}
		return ResponseEntity.status(httpStatus).body(users);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<User> loginUser(@RequestParam(value="email", defaultValue="") String email, @RequestParam(value="password", defaultValue="") String password) {
		User user = null;
		if("".equals(email) || "".equals(password)){
			httpStatus = HttpStatus.BAD_REQUEST;
		}else{
			user = userService.loginUserByEmail(email, password);
			if(user == null){
				httpStatus = HttpStatus.NOT_FOUND;
			}
			else{
				httpStatus = HttpStatus.OK;
			}
		}
		return ResponseEntity.status(httpStatus).body(user);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<User> signinUser(@RequestBody User user) {
		user = userService.signinUser(user);
		if(user == null){
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		else{
			httpStatus = HttpStatus.OK;
		}
		return ResponseEntity.status(httpStatus).body(user);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	ResponseEntity<User> updateUser(@RequestBody User user) {
		user = userService.updateUser(user);
		if(user == null){
			httpStatus = HttpStatus.NOT_FOUND;
		}
		else{
			httpStatus = HttpStatus.OK;
		}
		return ResponseEntity.status(httpStatus).body(user);
	}
	
	
	
	
	
	
	
	
	
}