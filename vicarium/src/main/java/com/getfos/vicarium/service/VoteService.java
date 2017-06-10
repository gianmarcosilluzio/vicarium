package com.getfos.vicarium.service;

import java.util.List;

import com.getfos.vicarium.model.User;
import com.getfos.vicarium.model.Vote;

public interface VoteService {
	Vote addVote(Vote vote);
	List<Vote> getAllVote();
	List<Vote> getAllDeputiesVote();
	List<Vote> getAllUserVote();
	List<Vote> getAllVoteByReferendumId(Integer referendumId);
	List<Vote> getAllVoteByDeputyId(Integer deputyId);
	List<Vote> getAllVoteByUserId(User user);
}
