package com.gettfos.vicarium.dao;

import java.util.List;

import com.gettfos.vicarium.model.Vote;

public interface VoteDao {
	Vote readByUserId(Integer userId);
	List<Vote> readByReferendumId(Integer referendumId);
	List<Vote> readAll();
	Vote createVote(Vote vote);
}
