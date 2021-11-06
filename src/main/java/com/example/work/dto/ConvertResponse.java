package com.example.work.dto;

import java.util.List;

public class ConvertResponse {
	private String timeUpdate; //更新時間 格式為 yyyy/MM/dd HH:mm:ss
	private List<CoindeskCurrencyResponse> currencys; //幣別相關資訊
	
	public String getTimeUpdate() {
		return timeUpdate;
	}
	
	public void setTimeUpdate(String timeUpdate) {
		this.timeUpdate = timeUpdate;
	}
	
	public List<CoindeskCurrencyResponse> getCurrencys() {
		return currencys;
	}
	
	public void setCurrencys(List<CoindeskCurrencyResponse> currencys) {
		this.currencys = currencys;
	}

	@Override
	public String toString() {
		return "ConvertResponse [timeUpdate=" + timeUpdate + ", currencys=" + currencys + "]";
	}	
}
