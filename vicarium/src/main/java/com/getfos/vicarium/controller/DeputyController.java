package com.getfos.vicarium.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.getfos.vicarium.model.Deputy;
import com.getfos.vicarium.service.DeputyService;
import com.getfos.vicarium.service.ReferendumService;
import com.getfos.vicarium.service.UserService;
import com.getfos.vicarium.service.VoteService;

@CrossOrigin("http://vicarium.org")
@RestController
@RequestMapping("/deputies")
public class DeputyController {
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
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<List<Deputy>> addAllDeputies() {
		try {
			List<Deputy> deputiesExternal = deputyService.getDeputyFromCamera();
			for (Deputy deputy : deputiesExternal) {
				deputyService.addDeputy(deputy);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpStatus = HttpStatus.OK;
		List<Deputy> deputies = deputyService.getAll();
		return ResponseEntity.status(httpStatus).body(deputies);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<Deputy>> getAllDeputy() {
		List<Deputy> deputies = deputyService.getAll();
		if(deputies.isEmpty()){
			httpStatus = HttpStatus.NOT_FOUND;
		}else{
			httpStatus = HttpStatus.OK;
		}
		return ResponseEntity.status(httpStatus).body(deputies);
	}
	
}