package com.gettfos.vicarium.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gettfos.vicarium.model.Referendum;


@Repository("referendumDao")
public class ReferendumDaoImpl extends AbstractDao<Integer, Referendum> implements ReferendumDao{

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

}
