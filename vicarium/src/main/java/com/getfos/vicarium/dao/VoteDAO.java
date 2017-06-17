package com.getfos.vicarium.dao;

import java.util.List;

import com.getfos.vicarium.model.Deputy;
import com.getfos.vicarium.model.ExpressionCategory;
import com.getfos.vicarium.model.Politic;
import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.Vote;

public interface VoteDAO {
	List<Vote> readPoliticVotes(Politic politic);
	List<Vote> readReferendumVotes(Referendum referendum);
	List<Vote> readAll();
	Vote createVote(Vote vote);
	List<Integer> readDeputyIdsAgree(List<Deputy> deputies, List<ExpressionCategory> expressions, Referendum referendum);
}
