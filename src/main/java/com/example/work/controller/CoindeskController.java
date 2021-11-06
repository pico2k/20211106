package com.example.work.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.work.dto.ConvertResponse;
import com.example.work.service.CoindeskService;

@RestController
@RequestMapping("/coindesk")
public class CoindeskController {
	
	Logger logger = LoggerFactory.getLogger(CoindeskController.class);
	
	@Autowired
	CoindeskService coindeskService;

	@GetMapping("/")
	public ResponseEntity<String> getCoindeskData() {
		logger.info(coindeskService.getCoindeskData().toString());
		return new ResponseEntity<>(coindeskService.getCoindeskData(), HttpStatus.OK);
	}
	
	@GetMapping("/convert")
	public ResponseEntity<ConvertResponse> convertCoindeskData() {
		logger.info(coindeskService.convertCoindeskData().toString());
		return new ResponseEntity<>(coindeskService.convertCoindeskData(), HttpStatus.OK);
	}
}
