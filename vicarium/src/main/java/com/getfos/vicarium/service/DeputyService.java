package com.getfos.vicarium.service;

import java.io.IOException;
import java.util.List;

import com.getfos.vicarium.model.Deputy;

public interface DeputyService {
	Deputy addDeputy(Deputy deputy);
	Deputy updateDeputy(Deputy deputy);
	List<Deputy> getAll();
	List<Deputy> bestMatch(Integer userId);
	List<Deputy> getDeputyFromCamera() throws IOException;
}
