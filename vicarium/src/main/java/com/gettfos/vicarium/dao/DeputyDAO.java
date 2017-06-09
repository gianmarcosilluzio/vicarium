package com.gettfos.vicarium.dao;

import java.util.List;

import com.gettfos.vicarium.model.Deputy;

public interface DeputyDAO {
	Deputy readById(Integer id);
	List<Deputy> readAll();
	Deputy createDeputy(Deputy deputy);
	Deputy updateDeputy(Deputy deputy);
}
