package com.example.work.dto;

public class CurrencyResponse {

	private long id;

	private String name;

	private String cname;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "CurrencyResponse [id=" + id + ", name=" + name + ", cname=" + cname + "]";
	}
	
}
