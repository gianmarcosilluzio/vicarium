package com.getfos.vicarium.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.getfos.vicarium.dao.AbstractDAO;
import com.getfos.vicarium.dao.VoteDAO;
import com.getfos.vicarium.model.Politic;
import com.getfos.vicarium.model.Referendum;
import com.getfos.vicarium.model.Vote;


@Repository("voteDao")
public class VoteDAOImpl extends AbstractDAO<Integer, Vote> implements VoteDAO{


	@SuppressWarnings("unchecked")
	public List<Vote> readPoliticVotes(Politic politic) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("politic", politic));
		return criteria.list();
	}

	
	@SuppressWarnings("unchecked")
	public List<Vote> readAll() {
		Criteria criteria = createEntityCriteria();
		return criteria.list();
	}


	@SuppressWarnings("unchecked")
	public List<Vote> readReferendumVotes(Referendum referendum) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("referendum", referendum));
		return criteria.list();
	}

	
	public Vote createVote(Vote vote) {
		try{
			persist(vote);
			return vote;
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return null;
	}



}
