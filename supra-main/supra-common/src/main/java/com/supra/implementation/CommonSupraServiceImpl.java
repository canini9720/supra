package com.supra.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supra.common.implementation.CommonDAO;
import com.supra.service.CommonSupraService;

@Service("commonService")
@Transactional
public class CommonSupraServiceImpl implements CommonSupraService {
	
	@Autowired
	CommonDAO commonDAO;

	

}
