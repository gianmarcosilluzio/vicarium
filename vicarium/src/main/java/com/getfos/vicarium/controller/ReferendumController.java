package com.getfos.vicarium.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.getfos.vicarium.model.Deputy;
import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.User;
import com.getfos.vicarium.model.Vote;
import com.getfos.vicarium.service.DeputyService;
import com.getfos.vicarium.service.ReferendumService;
import com.getfos.vicarium.service.UserService;
import com.getfos.vicarium.service.VoteService;

//@CrossOrigin("http://getfos.com")
@RestController
@RequestMapping("/referendums")
public class ReferendumController {
	@Autowired
	ReferendumService referendumService;
	
	@Autowired
	VoteService voteService;
	
	@Autowired
	DeputyService deputyService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	UserService userService;
	
	private HttpStatus httpStatus;
	
	@RequestMapping(value="/{referendumId}", method = RequestMethod.GET)
	ResponseEntity<Referendum> getReferendumById(@PathVariable Integer referendumId) {
		Referendum referendum = referendumService.getReferendumById(referendumId);
		if(referendum == null){
			httpStatus = HttpStatus.NOT_FOUND;
		}else{
			httpStatus = HttpStatus.OK;
		}
		return ResponseEntity.status(httpStatus).body(referendum);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<Referendum>> getAllReferendum() {
		List<Referendum> referendums = referendumService.getAllReferendum();
		if(referendums.isEmpty()){
			httpStatus = HttpStatus.NOT_FOUND;
		}else{
			httpStatus = HttpStatus.OK;
		}
		return ResponseEntity.status(httpStatus).body(referendums);
	}
	
	@RequestMapping(value="/{referendumId}/users/votes", method = RequestMethod.GET)
	ResponseEntity<List<Vote>> getReferendumUserVotes(@PathVariable Integer referendumId){
		List<Vote> votes = null;
		List<User> users = userService.getAllUser();
		Referendum referendum = referendumService.getReferendumById(referendumId);
		if(referendum != null && users != null){
			votes = voteService.getUserReferendumVotes(referendum, users);
		}
		return ResponseEntity.status(httpStatus).body(votes);
	}
	
	@RequestMapping(value="/{referendumId}/deputies/votes", method = RequestMethod.GET)
	ResponseEntity<List<Vote>> getReferendumDeputyVotes(@PathVariable Integer referendumId) {
		List<Vote> votes = null;
		List<Deputy> deputies = deputyService.getAll();
		Referendum referendum = referendumService.getReferendumById(referendumId);
		if(referendum != null && deputies != null){
			httpStatus = HttpStatus.OK;
			votes = voteService.getDeputyReferendumVotes(referendum, deputies);
		}else{
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return ResponseEntity.status(httpStatus).body(votes);
	}
	
	@RequestMapping(value="/{referendumId}/votes", method = RequestMethod.GET)
	ResponseEntity<List<Vote>> getAllReferendumVotes(@PathVariable Integer referendumId) {
		List<Vote> votes =  null;
		Referendum referendum = referendumService.getReferendumById(referendumId);
		if(referendum != null){
			votes = voteService.getReferendumVotes(referendum);
			httpStatus = HttpStatus.OK;
		}
		return ResponseEntity.status(httpStatus).body(votes);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<List<Referendum>> getReferendumById() {
		try {
			List<Referendum> referendumExternal = referendumService.getReferendumFromCamera();
			for (Referendum referendum : referendumExternal) {
				referendumService.addReferendum(referendum);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpStatus = HttpStatus.OK;
		List<Referendum> referendums = referendumService.getAllReferendum();
		return ResponseEntity.status(httpStatus).body(referendums);
	}
	
	
}