package com.getfos.vicarium.service;

import java.io.IOException;
import java.util.List;

import com.getfos.vicarium.model.Referendum;

public interface ReferendumService {
	Referendum addReferendum(Referendum referendum);
	List<Referendum> getAllReferendum();
	Referendum getReferendumById(Integer id);
	List<Referendum> getReferendumFromCamera() throws IOException;
}
