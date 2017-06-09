package com.gettfos.vicarium.service;

import java.util.List;

import com.gettfos.vicarium.model.Vote;

public interface VoteService {
	Vote addVote(Vote vote);
	List<Vote> getAllVote();
	List<Vote> getAllDeputiesVote();
	List<Vote> getAllUserVote();
	List<Vote> getAllVoteByReferendumId(Integer referendumId);
}
