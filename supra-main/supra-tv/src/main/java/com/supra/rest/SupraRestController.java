package com.supra.rest;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.supra.dto.TestDTO;
import com.supra.service.CommonSupraService;
import com.supra.service.TestService;

@RestController
public class SupraRestController {
	
	@Autowired
	TestService testService;
	
	
	@Autowired
	CommonSupraService commonService;
	
	@Resource
	WebServiceContext ctx;
	
	

	@RequestMapping(value = "/supratvGet", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public String welcome() {// Welcome page, non-rest
		System.out.println("Called Get controller");
		return "Welcome to RestTemplate Example.";
	}

	@RequestMapping(value = "/supratvPost", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String addProduct(@RequestBody String jsonStr)throws Exception {
		// Gson gson = new Gson();
		System.out.println("ctx  Rest=======>"+ctx);
		System.out.println("commonService Rest=======>"+commonService);
		System.out.println("testService Rest=======>"+testService);
		Gson gson = new GsonBuilder().create();
		TestDTO testDTO = gson.fromJson(jsonStr, TestDTO.class);
		return String.valueOf(testService.saveTest(testDTO));

		//return "ok called successfully";
	}

	
}