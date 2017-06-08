package com.gettfos.vicarium.service;

import java.util.List;

import com.gettfos.vicarium.model.Referendum;
import com.gettfos.vicarium.model.User;

public interface ReferendumService {
	Referendum addReferendum(Referendum referendum);
	List<Referendum> getAllReferendum();
	Referendum getReferendumById(Integer id);
}
