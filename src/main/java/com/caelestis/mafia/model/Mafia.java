package com.caelestis.mafia.model;

import java.util.List;

public class Mafia {

	private List<Subordinado> members;

	@Override
	public String toString() {
		return "Mafia [members=" + members + "]";
	}

	public List<Subordinado> getMembers() {
		return members;
	}

	public void setMembers(List<Subordinado> members) {
		this.members = members;
	}
	
	
}
