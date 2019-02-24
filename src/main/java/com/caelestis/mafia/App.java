package com.caelestis.mafia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.caelestis.mafia.model.Carcel;
import com.caelestis.mafia.model.Mafia;
import com.caelestis.mafia.model.Subordinado;
import com.caelestis.mafia.util.MafiaUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Carcel carcel = new Carcel();
		
		// tratamos el json, se convierte a objeto
		Mafia mafia = mapper.readValue(new FileInputStream("datos.json"), Mafia.class);
		//organigrama normal
		MafiaUtils.printOrganigrama(mafia.getMembers().stream().filter(e-> e.getBoss().equals("")).findFirst().get(), mafia.getMembers(), "");
		// jhon en la carcel
		carcel.entrar(MafiaUtils.quitarMiembro("Jhon", mafia.getMembers()));
		System.out.println("-------------------------------------------------------------------------------\n\n");
		//pinta organigrama jhon en la carcel
		MafiaUtils.printOrganigrama(mafia.getMembers().stream().filter(e-> e.getBoss().equals("")).findFirst().get(), mafia.getMembers(), "");
		// se saca a jhon de la carcel
		MafiaUtils.addMiembro(carcel.salir("Jhon"), mafia.getMembers());
		System.out.println("-------------------------------------------------------------------------------\n\n");
		//pinta organigrama normal
		MafiaUtils.printOrganigrama(mafia.getMembers().stream().filter(e-> e.getBoss().equals("")).findFirst().get(), mafia.getMembers(), "");
		// andy en la carcel
		carcel.entrar(MafiaUtils.quitarMiembro("Andy", mafia.getMembers()));
		System.out.println("-------------------------------------------------------------------------------\n\n");
		//pinta organigrama andy en la carcel
		MafiaUtils.printOrganigrama(mafia.getMembers().stream().filter(e-> e.getBoss().equals("")).findFirst().get(), mafia.getMembers(), "");
		//saca andy de la carcel
		MafiaUtils.addMiembro(carcel.salir("Andy"), mafia.getMembers());
		System.out.println("-------------------------------------------------------------------------------\n\n");
		//pinta organigrama normal
		MafiaUtils.printOrganigrama(mafia.getMembers().stream().filter(e-> e.getBoss().equals("")).findFirst().get(), mafia.getMembers(), "");
	}
	
	
}
