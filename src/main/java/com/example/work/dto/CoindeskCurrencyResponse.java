package com.example.work.dto;

public class CoindeskCurrencyResponse {
	private String name; //幣別名稱
	private String cname; //幣別中文名稱
	private String rate; //匯率
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCname() {
		return cname;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public String getRate() {
		return rate;
	}
	
	public void setRate(String rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "CurrencyResponse [name=" + name + ", cname=" + cname + ", rate=" + rate + "]";
	}	
	
}
