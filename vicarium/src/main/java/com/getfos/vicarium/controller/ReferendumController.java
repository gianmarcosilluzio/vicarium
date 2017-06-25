package com.getfos.vicarium.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getfos.vicarium.model.Deputy;
import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.User;
import com.getfos.vicarium.model.Vote;
import com.getfos.vicarium.model.webapp.ReferendumWebApp;
import com.getfos.vicarium.service.DeputyService;
import com.getfos.vicarium.service.ReferendumService;
import com.getfos.vicarium.service.UserService;
import com.getfos.vicarium.service.VoteService;
import com.getfos.vicarium.util.DateUtil;

@CrossOrigin("http://vicarium.org")
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
	ResponseEntity<List<ReferendumWebApp>> getAllReferendum(@RequestParam(value="userId", defaultValue="0") Integer userId) {
		User user = userService.getUserById(userId);
		List<Referendum> referendums = referendumService.getAllReferendum();
		List<ReferendumWebApp> results = new ArrayList<>();
		for (Referendum referendum : referendums) {
			ReferendumWebApp referendumWebApp = new ReferendumWebApp();
			for(Vote vote : voteService.getPoliticVotes(user)){
				if(vote.getReferendum().getReferendumId() == referendum.getReferendumId()){
					referendumWebApp.setUserVoted(true);
					break;
				}
				referendumWebApp.setUserVoted(false);
			}
			referendumWebApp.setReferendum(referendum);
			results.add(referendumWebApp);
		}
		if(referendums.isEmpty()){
			httpStatus = HttpStatus.NOT_FOUND;
		}else{
			httpStatus = HttpStatus.OK;
		}
		return ResponseEntity.status(httpStatus).body(results);
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
	ResponseEntity<List<Referendum>> addAllReferendumByDate(@RequestParam(value="date", defaultValue="") String date) {
		try {
			List<Referendum> referendumExternal = referendumService.getReferendumFromCamera(DateUtil.convertStringToDate(date,"yyyyMMdd"));
			for (Referendum referendum : referendumExternal) {
				referendum = referendumService.addReferendum(referendum);
				voteService.addVoteToReferendumFromCamera(referendum);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpStatus = HttpStatus.OK;
		List<Referendum> referendums = referendumService.getAllReferendum();
		return ResponseEntity.status(httpStatus).body(referendums);
	}
	
	
}