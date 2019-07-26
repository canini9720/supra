package com.supra.service;

import java.util.List;

import com.supra.dto.TestDTO;
import com.supra.dto.lost.LostFound;
import com.supra.dto.lost.dp.LostAndFoundResponseDTO;
import com.supra.dto.lost.dp.LostItemApplicationDTO;
import com.supra.model.DipCommVWEntity;

public interface TestService {

	public Long saveTest(TestDTO dto) throws Exception;
	
	public List<DipCommVWEntity> getList(TestDTO dto) throws Exception;
	
	public Integer saveLostFoundSerial(LostFound dto) throws Exception;
	
	public Integer saveLostFoundSerialUpdated(LostItemApplicationDTO dto) throws Exception;

	public LostAndFoundResponseDTO registerLostItem(Long refno) throws Exception;
	
	public void callStoreProcedure()throws Exception;

	
}