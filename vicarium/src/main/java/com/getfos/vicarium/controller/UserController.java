package com.getfos.vicarium.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.getfos.vicarium.model.ExpressionCategory;
import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.User;
import com.getfos.vicarium.model.Vote;
import com.getfos.vicarium.service.DeputyService;
import com.getfos.vicarium.service.ReferendumService;
import com.getfos.vicarium.service.UserService;
import com.getfos.vicarium.service.VoteService;

@CrossOrigin("http://vicarium.org")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	VoteService voteService;
	
	@Autowired
	DeputyService deputyService;
	
	@Autowired
	ReferendumService referendumService;
	
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
	
	@RequestMapping(value="/{userId}/votes", method = RequestMethod.GET)
	ResponseEntity<List<Vote>> getUserVotes(@PathVariable Integer userId) {
		User user = userService.getUserById(userId);
		List<Vote> votes = voteService.getPoliticVotes(user);
		httpStatus = HttpStatus.OK;
		return ResponseEntity.status(httpStatus).body(votes);
	}
	
	@RequestMapping(value="/votes", method = RequestMethod.GET)
	ResponseEntity<List<Vote>> getAllUserVotes() {
		List<Vote> votes = voteService.getAllVote();
		httpStatus = HttpStatus.OK;
		return ResponseEntity.status(httpStatus).body(votes);
	}
	
	@RequestMapping(value="/{userId}/votes", method = RequestMethod.POST)
	ResponseEntity<Vote> addUserVote(@PathVariable Integer userId, @RequestParam(value="referendumId", defaultValue="") Integer referendumId, @RequestParam(value="expression", defaultValue="") String expression) {
		Vote vote = new Vote();
		if("".equals(userId) || "".equals(referendumId) || "".equals(expression)){
			httpStatus = HttpStatus.BAD_REQUEST;
		}else{
			User user = userService.getUserById(userId);
			Referendum referendum = referendumService.getReferendumById(referendumId);
			if(user == null && referendum == null){
				httpStatus = HttpStatus.NOT_FOUND;
				
			}else{
				httpStatus = HttpStatus.OK;
				vote.setPolitic(user);
				vote.setReferendum(referendum);
				vote.setDate(new Date());
				vote.setExpression(ExpressionCategory.valueOf(expression));
				vote = voteService.addVote(vote);
			}
		}
		
		return ResponseEntity.status(httpStatus).body(vote);
	}
	
	
	@RequestMapping(value="/loginEmail", method = RequestMethod.GET)
	ResponseEntity<User> loginEmailUser(@RequestParam(value="email", defaultValue="") String email, @RequestParam(value="password", defaultValue="") String password) {
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
	
	@RequestMapping(value="/loginFacebook", method = RequestMethod.GET)
	ResponseEntity<User> loginFaceookUser(@RequestParam(value="facebookId", defaultValue="") String facebookId) {
		User user = null;
		if("".equals(facebookId)){
			httpStatus = HttpStatus.BAD_REQUEST;
		}else{
			user = userService.getUserByFacebookId(facebookId);
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
	
	
	
	@RequestMapping(value="/{userId}/bestmatch", method = RequestMethod.GET)
	ResponseEntity<Map<String, List<Object>>> bestmatch(@PathVariable Integer userId) {
		Map<String, List<Object>> results = null;
		User user = userService.getUserById(userId);
		results = deputyService.bestMatch(user);
		httpStatus = HttpStatus.OK;
		return ResponseEntity.status(httpStatus).body(results);
	}
}