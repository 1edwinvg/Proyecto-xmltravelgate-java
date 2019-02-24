package com.caelestis.mafia.model;

import java.util.List;

public class Subordinado {

	private String name;
	
	private List<String> subordinates;
	
	private Integer seniority;
	
	private String boss;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<String> subordinates) {
		this.subordinates = subordinates;
	}

	

	public Integer getSeniority() {
		return seniority;
	}

	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}
	
	
}
