package com.getfos.vicarium.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getfos.vicarium.dao.VoteDAO;
import com.getfos.vicarium.model.Deputy;
import com.getfos.vicarium.model.Politic;
import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.User;
import com.getfos.vicarium.model.Vote;
import com.getfos.vicarium.service.VoteService;

@Service("voteService")
@Transactional
public class VoteServiceImpl implements VoteService{
	
	@Autowired
	private VoteDAO voteDAO;


	public Vote addVote(Vote vote) {
		return voteDAO.createVote(vote);
	}

	public List<Vote> getAllVote() {
		return voteDAO.readAll();
	}

	public List<Vote> getReferendumVotes(Referendum referendum) {
		return voteDAO.readReferendumVotes(referendum);
	}

	public List<Vote> getPoliticVotes(Politic politic) {
		return voteDAO.readPoliticVotes(politic);
	}

	public List<Vote> getUserReferendumVotes(Referendum referendum, List<User> users) {
		List<Vote> userVotes = new ArrayList<Vote>();
		for (User user : users) {
			for (Vote vote : voteDAO.readPoliticVotes(user)) {
				if(vote.getReferendum().getReferendumId().equals(referendum.getReferendumId())){
					userVotes.add(vote);
				}
			}
		}
		return userVotes;
	}
	
	public List<Vote> getDeputyReferendumVotes(Referendum referendum, List<Deputy> deputies) {
		List<Vote> votesDeputies = new ArrayList<Vote>();
		for (Deputy deputy : deputies) {
			for (Vote vote : voteDAO.readPoliticVotes(deputy)) {
				if(vote.getReferendum().getReferendumId().equals(referendum.getReferendumId())){
					votesDeputies.add(vote);
				}
			}
		}
		return votesDeputies;
	}

}
