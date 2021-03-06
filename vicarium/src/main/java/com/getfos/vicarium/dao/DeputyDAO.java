package com.getfos.vicarium.dao;

import java.util.List;

import com.getfos.vicarium.model.Deputy;

public interface DeputyDAO {
	Deputy readById(Integer id);
	Deputy readByIdentifier(String identifier);
	List<Deputy> readAll();
	Deputy createDeputy(Deputy deputy);
	Deputy updateDeputy(Deputy deputy);
}
