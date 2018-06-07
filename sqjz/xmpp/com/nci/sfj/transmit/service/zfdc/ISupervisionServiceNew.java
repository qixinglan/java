package com.nci.sfj.transmit.service.zfdc;

import com.nci.dcs.supervision.model.SupervisionCount;
import com.nci.dcs.system.model.Bmb;

public interface ISupervisionServiceNew {
	
	public SupervisionCount getSupervisionCountNew(Bmb bmb);

	public SupervisionCount getSupervisionCount(Bmb bmb);
}
