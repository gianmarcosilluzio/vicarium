package com.getfos.vicarium.service;

import java.util.List;

import com.getfos.vicarium.model.Deputy;
import com.getfos.vicarium.model.Politic;
import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.User;
import com.getfos.vicarium.model.Vote;

public interface VoteService {
	Vote addVote(Vote vote);
	List<Vote> getAllVote();
	List<Vote> getReferendumVotes(Referendum referendum);
	List<Vote> getPoliticVotes(Politic politic);
	List<Vote> getUserReferendumVotes(Referendum referendum, List<User> users);
	List<Vote> getDeputyReferendumVotes(Referendum referendum, List<Deputy> deputies);
}
