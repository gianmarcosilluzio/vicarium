package com.gettfos.vicarium.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gettfos.vicarium.dao.ReferendumDAO;
import com.gettfos.vicarium.model.Referendum;
import com.gettfos.vicarium.service.ReferendumService;

@Service("referendumService")
@Transactional
public class ReferendumServiceImpl implements ReferendumService{
	
	@Autowired
	private ReferendumDAO referendumDAO;

	public Referendum addReferendum(Referendum referendum) {
		return referendumDAO.createReferendum(referendum);
	}

	public List<Referendum> getAllReferendum() {
		return referendumDAO.readAll();
	}

	public Referendum getReferendumById(Integer id) {
		return referendumDAO.readById(id);
	}

	
}
