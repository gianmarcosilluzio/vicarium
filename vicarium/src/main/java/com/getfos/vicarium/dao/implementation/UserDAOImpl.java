package com.getfos.vicarium.dao.implementation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.getfos.vicarium.dao.AbstractDAO;
import com.getfos.vicarium.dao.UserDAO;
import com.getfos.vicarium.model.User;


@Repository("userDao")
public class UserDAOImpl extends AbstractDAO<Integer, User> implements UserDAO{

	public User readById(Integer id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (User)criteria.uniqueResult();
	}

	public User readByEmail(String email) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("email", email));
		return (User)criteria.uniqueResult();
	}

	public User readByFacebookId(String facebookId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("facebookId", facebookId));
		return (User)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> readAll() {
		Criteria criteria = createEntityCriteria();
		return criteria.list();
	}

	public User createUser(User user) {
		try{
			persist(user);
			return user;
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return null;
	}

	public User updateUser(User user) {
		try{
			update(user);
			return user;
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return null;
	}


}
