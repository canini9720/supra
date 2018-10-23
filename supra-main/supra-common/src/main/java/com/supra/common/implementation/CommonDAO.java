package com.supra.common.implementation;

import com.supra.model.SupraLogEntity;
import com.supra.model.SupraLogEntityDefault;

public interface CommonDAO {
	
	public void saveSupraLog(SupraLogEntity entity)throws Exception;
	public void saveSupraLogDefault(SupraLogEntityDefault entity)throws Exception;

}
