package com.gettfos.vicarium.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.gettfos.vicarium.model.Deputy;


@Repository("deputyDao")
public class DeputyDaoImpl extends AbstractDao<Integer, Deputy> implements DeputyDao{

	public Deputy readById(Integer id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Deputy)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Deputy> readAll() {
		Criteria criteria = createEntityCriteria();
		return criteria.list();
	}

	public Deputy createDeputy(Deputy deputy) {
		try{
			persist(deputy);
			return deputy;
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return null;
	}

	public Deputy updateDeputy(Deputy deputy) {
		try{
			update(deputy);
			return deputy;
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return null;
	}


}
