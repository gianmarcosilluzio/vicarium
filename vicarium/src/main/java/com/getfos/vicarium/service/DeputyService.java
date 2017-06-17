package com.getfos.vicarium.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.getfos.vicarium.model.Deputy;
import com.getfos.vicarium.model.User;

public interface DeputyService {
	Deputy addDeputy(Deputy deputy);
	Deputy updateDeputy(Deputy deputy);
	List<Deputy> getAll();
	Map<String, List<Object>> bestMatch(User user);
	List<Deputy> getDeputyFromCamera() throws IOException;
}
