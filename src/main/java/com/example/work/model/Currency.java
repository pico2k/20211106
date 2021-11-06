package com.example.work.model;

import javax.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "cname")
	private String cname;	

	public Currency() {

	}

	public Currency(long id, String name, String cname) {
		super();
		this.id = id;
		this.name = name;
		this.cname = cname;
	}

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
		return "Currency [id=" + id + ", name=" + name + ", cname=" + cname + "]";
	}	
	
}
