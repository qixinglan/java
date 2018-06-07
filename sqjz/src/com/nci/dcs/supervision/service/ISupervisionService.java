package com.nci.dcs.supervision.service;

import com.nci.dcs.supervision.model.SupervisionCount;
import com.nci.dcs.system.model.Bmb;
import com.nci.dcs.system.model.User;

public interface ISupervisionService {
	
	public SupervisionCount getSupervisionCount(User user);

	public SupervisionCount getSupervisionCount(Bmb bmb,User User);
}
