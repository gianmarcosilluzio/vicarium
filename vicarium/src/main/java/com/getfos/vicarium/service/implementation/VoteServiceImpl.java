package com.getfos.vicarium.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getfos.vicarium.dao.VoteDAO;
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

	public List<Vote> getAllDeputiesVote() {
		return voteDAO.readAllDeputyVote();
	}

	public List<Vote> getAllUserVote() {
		return voteDAO.readAllUserVote();
	}

	public List<Vote> getAllVoteByReferendumId(Integer referendumId) {
		return voteDAO.readByReferendumId(referendumId);
	}

	public List<Vote> getAllVoteByDeputyId(Integer deputyId) {
		return voteDAO.readByDeputyId(deputyId);
	}

	public List<Vote> getAllVoteByUserId(User user) {
		return voteDAO.readUserVotes(user);
	}

}
