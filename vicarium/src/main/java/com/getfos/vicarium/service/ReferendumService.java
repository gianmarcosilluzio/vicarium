package com.getfos.vicarium.service;

import java.util.List;

import com.getfos.vicarium.model.Referendum;

public interface ReferendumService {
	Referendum addReferendum(Referendum referendum);
	List<Referendum> getAllReferendum();
	Referendum getReferendumById(Integer id);
}
