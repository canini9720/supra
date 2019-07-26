package com.supra.implementation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.supra.dto.TestDTO;
import com.supra.dto.lost.LostFound;
import com.supra.dto.lost.dp.LostItemApplicationDTO;
import com.supra.model.DipCommVWEntity;
import com.supra.model.LostFoundAttachEntity;
import com.supra.model.LostFoundSerialEntity;
import com.supra.model.TestEntity;

public interface TestDAO {


	

	public void saveTest(TestEntity testEntity, TestDTO dto)throws Exception;
	
	public List<DipCommVWEntity> getList(TestDTO dto) throws Exception;
	
	public Integer saveLostFoundSerial(LostFound dto) throws Exception ;
	
	public Integer saveLostFoundSerialUpdated(LostItemApplicationDTO dto) throws Exception ;
	public LostFoundSerialEntity validateRefno(Long refNo);
	
	public void callStoreProcedure()throws Exception;
	
	public Map<Long,LostFoundAttachEntity> getAttachments(Long refNo) throws Exception;

}