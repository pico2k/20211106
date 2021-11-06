package com.example.work.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.work.dto.CurrencyRequest;
import com.example.work.dto.CurrencyResponse;
import com.example.work.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

	Logger logger = LoggerFactory.getLogger(CurrencyController.class);
	
	@Autowired
	CurrencyService currencyService;
	
	@GetMapping("/")
	public ResponseEntity<List<CurrencyResponse>> getAllCurrencys() {
		try {
			List<CurrencyResponse> responseList = currencyService.findAll();
			if (responseList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(responseList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CurrencyResponse> getCurrencyById(@PathVariable("id") long id) {
		CurrencyResponse response = currencyService.findById(id);		
		if (response != null) {
			logger.info(response.toString());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<CurrencyResponse> createCurrency(@RequestBody CurrencyRequest req) {
		try {
			CurrencyResponse response = currencyService.insertOrUpdate(req);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<CurrencyResponse> updateTutorial(@PathVariable("id") long id, @RequestBody CurrencyRequest req) {
		CurrencyResponse old = currencyService.findById(id);

		if (old != null) {	
			CurrencyResponse newResponse = currencyService.insertOrUpdate(req);
			logger.info(newResponse.toString());
			return new ResponseEntity<>(newResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			currencyService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
