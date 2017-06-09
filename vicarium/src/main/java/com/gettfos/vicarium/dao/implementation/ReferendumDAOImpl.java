package com.gettfos.vicarium.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gettfos.vicarium.dao.AbstractDAO;
import com.gettfos.vicarium.dao.ReferendumDAO;
import com.gettfos.vicarium.model.Referendum;


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

}
