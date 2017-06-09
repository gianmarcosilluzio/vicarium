package com.gettfos.vicarium.service;

import java.util.List;

import com.gettfos.vicarium.model.Deputy;

public interface DeputyService {
	Deputy addDeputy(Deputy deputy);
	Deputy updateDeputy(Deputy deputy);
	List<Deputy> getAll();
	List<Deputy> bestMatch(Integer userId);
}
