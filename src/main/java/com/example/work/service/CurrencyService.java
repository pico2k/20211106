package com.example.work.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.work.dto.CurrencyRequest;
import com.example.work.dto.CurrencyResponse;
import com.example.work.model.Currency;
import com.example.work.repository.CurrencyRepository;

@Service
public class CurrencyService {

	@Autowired
	CurrencyRepository currencyRepository;
	
	public List<CurrencyResponse> findAll() {
		List<Currency> currencys = new ArrayList<>(); 
	    currencyRepository.findAll().forEach(currencys::add);
		List<CurrencyResponse> dataList = new ArrayList<>();
		CurrencyResponse currencyResponse = null;
		if (currencys.size()>0) {
			for (Currency c : currencys) {
				currencyResponse = new CurrencyResponse();
				currencyResponse.setId(c.getId());
				currencyResponse.setName(c.getName());
				currencyResponse.setCname(c.getCname());
			}
		}
		
		return dataList;
	}
	
	public CurrencyResponse findById(long id) {
		CurrencyResponse currencyResponse = null;
		Optional<Currency> data = currencyRepository.findById(id);
		if (data.isPresent()) {
			Currency c = data.get();
			currencyResponse = new CurrencyResponse();
			currencyResponse.setId(c.getId());
			currencyResponse.setName(c.getName());
			currencyResponse.setCname(c.getCname());
		}
		
		return currencyResponse;
	}
	
	public CurrencyResponse insertOrUpdate(CurrencyRequest request) {
		Currency c = currencyRepository
				.save(new Currency(request.getId(), request.getName(), request.getCname()));
		CurrencyResponse currencyResponse = new CurrencyResponse();
		currencyResponse.setId(c.getId());
		currencyResponse.setName(c.getName());
		currencyResponse.setCname(c.getCname());
		
		return currencyResponse;
	}
	
	public void deleteById(long id) {
		currencyRepository.deleteById(id);
	}
}
