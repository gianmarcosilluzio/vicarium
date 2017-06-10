package com.getfos.vicarium.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getfos.vicarium.dao.DeputyDAO;
import com.getfos.vicarium.model.Deputy;
import com.getfos.vicarium.service.DeputyService;

@Service("deputyService")
@Transactional
public class DeputyServiceImpl implements DeputyService{
	
	@Autowired
	private DeputyDAO deputyDAO;

	public Deputy addDeputy(Deputy deputy) {
		return deputyDAO.createDeputy(deputy);
	}

	public Deputy updateDeputy(Deputy deputy) {
		return deputyDAO.updateDeputy(deputy);
	}

	public List<Deputy> getAll() {
		return deputyDAO.readAll();
	}

	public List<Deputy> bestMatch(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}