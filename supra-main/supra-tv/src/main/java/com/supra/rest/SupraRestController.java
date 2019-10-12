package com.supra.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.supra.dto.TestDTO;
import com.supra.service.CommonService;
import com.supra.service.TestService;

@RestController
public class SupraRestController {
	
	@Autowired
	TestService testService;
	

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
		Gson gson = new GsonBuilder().create();
		TestDTO testDTO = gson.fromJson(jsonStr, TestDTO.class);
		return String.valueOf(testService.saveTest(testDTO));

		//return "ok called successfully";
	}

	/*
	 * @RequestMapping("/hello/{player}") public Message message(@PathVariable
	 * String player) {//REST Endpoint.
	 * 
	 * Message msg = new Message(player, "Hello " + player); return msg; }
	 */
}