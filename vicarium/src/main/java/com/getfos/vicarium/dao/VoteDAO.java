package com.getfos.vicarium.dao;

import java.util.List;

import com.getfos.vicarium.model.User;
import com.getfos.vicarium.model.Vote;

public interface VoteDAO {
	List<Vote> readUserVotes(User user);
	List<Vote> readByReferendumId(Integer referendumId);
	List<Vote> readAll();
	List<Vote> readAllUserVote();
	List<Vote> readAllDeputyVote();
	Vote createVote(Vote vote);
	List<Vote> readByDeputyId(Integer deputyId);
}
