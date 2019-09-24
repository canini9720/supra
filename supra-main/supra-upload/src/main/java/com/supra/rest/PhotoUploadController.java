package com.supra.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.supra.dto.FileInfo;

@RestController
public class PhotoUploadController {
	
	@RequestMapping(value = "/fileupload", headers=("content-type=multipart/*"), method = RequestMethod.POST)
	 public ResponseEntity<FileInfo> upload(@RequestParam("file") MultipartFile inputFile,@RequestParam(name="personName") String personName) {
	  FileInfo fileInfo = new FileInfo();
	  HttpHeaders headers = new HttpHeaders();
	  if (!inputFile.isEmpty()) {
	   try {
	    String originalFilename = inputFile.getOriginalFilename();
	    File destinationFile = new File("C:\\Users\\ahash\\OneDrive\\Desktop\\New folder"+  File.separator + originalFilename);
	    inputFile.transferTo(destinationFile);
	    fileInfo.setFileName(destinationFile.getPath());
	    fileInfo.setFileSize(inputFile.getSize());
	    headers.add("File Uploaded Successfully - ", originalFilename);
	    System.out.println("File Uploaded Successfully - "+ originalFilename);
	    return new ResponseEntity<FileInfo>(fileInfo, headers, HttpStatus.OK);
	   } catch (Exception e) {    
	    return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
	   }
	  }else{
	   return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
	  }
	 }

}
