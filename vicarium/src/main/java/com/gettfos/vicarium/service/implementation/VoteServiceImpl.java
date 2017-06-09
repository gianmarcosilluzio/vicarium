package com.gettfos.vicarium.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gettfos.vicarium.dao.DeputyDAO;
import com.gettfos.vicarium.dao.UserDAO;
import com.gettfos.vicarium.dao.VoteDAO;
import com.gettfos.vicarium.model.Deputy;
import com.gettfos.vicarium.model.User;
import com.gettfos.vicarium.model.Vote;
import com.gettfos.vicarium.service.VoteService;

@Service("voteService")
@Transactional
public class VoteServiceImpl implements VoteService{
	
	@Autowired
	private VoteDAO voteDAO;

	
	@Autowired
	private DeputyDAO deputyDAO;
	
	@Autowired
	private UserDAO userDAO;


	public Vote addVote(Vote vote) {
		return voteDAO.createVote(vote);
	}

	public List<Vote> getAllVote() {
		return voteDAO.readAll();
	}

	public List<Vote> getAllDeputiesVote() {
		List<Vote> votes = new ArrayList<Vote>();
		List<Deputy> deputies = deputyDAO.readAll();
		for (Deputy deputy : deputies) {
			votes.addAll(deputy.getVotes());
		}
		return votes;
	}

	public List<Vote> getAllUserVote() {
		List<Vote> votes = new ArrayList<Vote>();
		List<User> users = userDAO.readAll();
		for (User user : users) {
			votes.addAll(user.getVotes());
		}
		return votes;
	}

	public List<Vote> getAllVoteByReferendumId(Integer referendumId) {
		return voteDAO.readByReferendumId(referendumId);
	}

}
