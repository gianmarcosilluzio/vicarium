package com.gettfos.vicarium.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gettfos.vicarium.model.Vote;


@Repository("voteDao")
public class VoteDaoImpl extends AbstractDao<Integer, Vote> implements VoteDao{


	public Vote readByUserId(Integer userId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userId", userId));
		return (Vote)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Vote> readByReferendumId(Integer referendumId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("referendumId", referendumId));
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

	@SuppressWarnings("unchecked")
	public List<Vote> readAll() {
		Criteria criteria = createEntityCriteria();
		return criteria.list();
	}


}
