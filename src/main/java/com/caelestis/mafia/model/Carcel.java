package com.caelestis.mafia.model;

import java.util.ArrayList;
import java.util.List;

public class Carcel {

	private List<Subordinado> cells;
	
	public Carcel() {
		cells = new ArrayList<Subordinado>();
	}
	
	public void entrar(Subordinado subordinado) {
		cells.add(subordinado);
	}
	
	public Subordinado salir(String name) {
		for(Subordinado subordinado: cells) {
			if(subordinado.getName().equals(name)) {
				return subordinado;
			}
		}
		return null;
	}
}
