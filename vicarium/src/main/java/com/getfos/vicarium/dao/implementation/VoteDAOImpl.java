package com.getfos.vicarium.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.getfos.vicarium.dao.AbstractDAO;
import com.getfos.vicarium.dao.VoteDAO;
import com.getfos.vicarium.model.Deputy;
import com.getfos.vicarium.model.ExpressionCategory;
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
	
	@SuppressWarnings("unchecked")
	public List<Integer> readDeputyIdsAgree(List<Deputy> deputies, List<ExpressionCategory> expressions, Referendum referendum) {
		Query query = createQuery("select v.politic.id from Vote v where v.politic in (:ids) and v.expression in (:exp) and v.referendum = :ref")
				.setParameterList("ids", deputies)
				.setParameterList("exp", expressions)
				.setParameter("ref", referendum);
		return query.list();
	}

	

}
