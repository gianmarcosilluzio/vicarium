package com.gettfos.vicarium.dao;

import java.util.List;

import com.gettfos.vicarium.model.Referendum;

public interface ReferendumDao {
	Referendum readById(Integer id);
	List<Referendum> readAll();
	Referendum createReferendum(Referendum referendum);
}
