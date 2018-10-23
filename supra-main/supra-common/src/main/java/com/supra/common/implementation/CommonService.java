package com.supra.common.implementation;

import com.supra.model.SupraLogEntity;
import com.supra.model.SupraLogEntityDefault;

public interface CommonService {
	public void saveSupraLogWithDefaultTransaction(SupraLogEntityDefault entity)throws Exception;
	public void saveSupraLog(SupraLogEntity entity)throws Exception;
}
