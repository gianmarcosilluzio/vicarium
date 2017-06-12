package com.getfos.vicarium.dao.implementation;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.getfos.vicarium.dao.AbstractDAO;
import com.getfos.vicarium.dao.ReferendumDAO;
import com.getfos.vicarium.model.Referendum;


@Repository("referendumDao")
public class ReferendumDAOImpl extends AbstractDAO<Integer, Referendum> implements ReferendumDAO{

	public Referendum readById(Integer id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Referendum)criteria.uniqueResult();
	}

	public Referendum createReferendum(Referendum referendum) {
		try{
			persist(referendum);
			return referendum;
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Referendum> readAll() {
		Criteria criteria = createEntityCriteria();
		return criteria.list();
	}

	@Override
	public Referendum readByDate(Date date) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("date", date));
		return (Referendum)criteria.uniqueResult();
	}

}
