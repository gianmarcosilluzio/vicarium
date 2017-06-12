package com.getfos.vicarium.dao;

import java.util.Date;
import java.util.List;

import com.getfos.vicarium.model.Referendum;

public interface ReferendumDAO {
	Referendum readById(Integer id);
	Referendum readByDate(Date date);
	List<Referendum> readAll();
	Referendum createReferendum(Referendum referendum);
}
