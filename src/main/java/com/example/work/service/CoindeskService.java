package com.example.work.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.work.dto.ConvertResponse;
import com.example.work.dto.CoindeskCurrencyResponse;

@Service
public class CoindeskService {
	
	private static String COINDESK_SERVICE_DATA = "https://api.coindesk.com/v1/bpi/currentprice.json";

	public String getCoindeskData() {
		String result = "";		
		
		try {
			RestTemplate resTemplate = new RestTemplate();
			ResponseEntity<String> responseEntity = resTemplate.getForEntity(COINDESK_SERVICE_DATA, String.class);
			result = responseEntity.getBody();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	public ConvertResponse convertCoindeskData() {
		ConvertResponse result = new ConvertResponse();	
		List<CoindeskCurrencyResponse> dataList = new ArrayList<>();
		CoindeskCurrencyResponse currencyResponse = null;
		
		try {
			String jsonString = getCoindeskData();
			JSONObject json = new JSONObject(jsonString);
			//更新時間
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String strTimeUpdate = sdf.format(new Date());
			//幣別資訊
			JSONObject currencyData = (JSONObject)json.get("bpi");
			//USD,GBP,EUR
			JSONObject USDData = (JSONObject)currencyData.get("USD");
			JSONObject GBPData = (JSONObject)currencyData.get("GBP");
			JSONObject EURData = (JSONObject)currencyData.get("EUR");
			
			if (USDData!=null) {
				String strRate = "-";
				if (USDData.get("rate") != null) {
					strRate = (String)USDData.get("rate");
				}
				currencyResponse = new CoindeskCurrencyResponse();
				currencyResponse.setName("USD");
				currencyResponse.setCname("美金");
				currencyResponse.setRate(strRate);
				dataList.add(currencyResponse);
			}
			
			if (GBPData!=null) {
				String strRate = "-";
				if (GBPData.get("rate") != null) {
					strRate = (String)GBPData.get("rate");
				}
				currencyResponse = new CoindeskCurrencyResponse();
				currencyResponse.setName("GBP");
				currencyResponse.setCname("英鎊");
				currencyResponse.setRate(strRate);
				dataList.add(currencyResponse);
			}
			
			if (EURData!=null) {
				String strRate = "-";
				if (EURData.get("rate") != null) {
					strRate = (String)EURData.get("rate");
				}
				currencyResponse = new CoindeskCurrencyResponse();
				currencyResponse.setName("EUR");
				currencyResponse.setCname("歐元");
				currencyResponse.setRate(strRate);
				dataList.add(currencyResponse);
			}
			result.setTimeUpdate(strTimeUpdate);
			result.setCurrencys(dataList);			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
}
