package com.caelestis.mafia.util;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.caelestis.mafia.model.Subordinado;

public class MafiaUtils {

	
	/**
	 * metodo para quitarlo de la lista(meterlo en la lista)
	 * @param name subordinado para quitar
	 * @param subordinados
	 * @return el subordinado de la lista
	 */
	public static Subordinado quitarMiembro(String name, List<Subordinado> subordinados) {
		Subordinado subordinado=null;
		for(int i=0;i<subordinados.size();i++) {
			if(subordinados.get(i).getName().equals(name)) {
				//quitamos el miembro de la lista
				subordinado = subordinados.get(i);
				subordinados.remove(i);
		
				//comprobamos si es el jefe o no(si es el jefe no tiene boss)
				if(!subordinado.getBoss().isEmpty()) {
					for(int j=0; j<subordinados.size(); j++) {
						//aqui buscamos anuestro jefe
						if (subordinados.get(j).getName().equals(subordinado.getBoss())) {
							//lo quitamos de la lista de subordinados del jefe
							subordinados.get(j).getSubordinates().remove(name);
							//comprobacion de si el jefe tiene mas subordinados sino hay que ascender al mas antiguo
							if(!subordinados.get(j).getSubordinates().isEmpty()) {
								//obtencion del mas viejo
								String nameMayor = getMayorSubordinador(subordinados.get(j), subordinados);
								//se recore la lista para pasar los subordinados al siguiente jefe.
								for(int k=0; k<subordinados.size();k++) {
									if(subordinados.get(k).getName().equals(nameMayor)){
										subordinados.get(k).getSubordinates().addAll(subordinado.getSubordinates());//añade los subordinados al nuevo jefe
										Subordinado newBoss = subordinados.get(k);
										//filtra los subordinados del nuevo jefe, luego se recorre la array para cambiar su nuevo jefe( cambiar el boss) 
										subordinados.stream().filter(o -> newBoss.getSubordinates().contains(o.getName())).forEach(o -> o.setBoss(newBoss.getName()));
									}
								}
							} else {
								ascenderSubordinado(subordinado, subordinados.get(j), subordinados);
							}
						}
					}
				} else {
					ascenderSubordinado(subordinado, null, subordinados);
				}
			}
		}
		return subordinado;
	}
	
	/**
	 * añade la lista a la mafia (sacarlo de la carcel)
	 * @param subordinado, el mimbro que sale de la carcel
	 * @param subordinados lista de la mafia
	 */
	public static void addMiembro(Subordinado subordinado,List<Subordinado> subordinados) {
		
		for(int i=0; i<subordinados.size();i++) {
			// este for es para recuperar los subordinados que tenia
			for(String value:subordinado.getSubordinates()) {	
				//el subordinado ewstya en la lista de subordinados del jefe que sale de la carcel. se asigna al este.
				if(subordinados.get(i).getName().equals(value)) {
					subordinados.get(i).setBoss(subordinado.getName());
					//aqui se le quita los subordinados de los demas jefes, que eran suyos.
				}else {
					subordinados.get(i).getSubordinates().remove(value);
				}
				
				
			}
			// si encuentro mi jefe, me añado a su lista de subordinados
			if(subordinados.get(i).getName().equals(subordinado.getBoss())) {
				subordinados.get(i).getSubordinates().add(subordinado.getName());
			}
		}
		
		subordinados.add(subordinado);
		
	}
	
	/**
	 *  filtro parea sacar el subornidano mas antiguo de un jefe
	 * @param subordinado,  un jefe 
	 * @param subordinados, lista de toda la banda
	 * @return nombre del subordinado que es mas antiguo
	 */
	private static String getMayorSubordinador(Subordinado subordinado, List<Subordinado> subordinados) {
		return subordinados.stream().filter(e -> subordinado.getSubordinates().contains(e.getName())).max(Comparator.comparing(Subordinado::getSeniority)).get().getName();
	}
	
	/**
	 * aqui acendemos al subordinado si no hay jefe
	 * @param subordinado , para metro que se va a quitar
	 * @param boss , el jefe  del que se va
	 * @param subordinados lista entera de la mafia
	 */
	private static void ascenderSubordinado(Subordinado subordinado, Subordinado boss, List<Subordinado> subordinados) {
		String name = getMayorSubordinador(subordinado, subordinados);
		for(int k=0; k<subordinados.size();k++) {
			if(subordinados.get(k).getName().equals(name)){
			  // se añade todos los subordinados, menos si mismo, por que ya es jefe
				subordinados.get(k).getSubordinates().addAll(subordinado.getSubordinates().stream().filter(e -> !e.equals(name)).collect(Collectors.toList()));							
				Subordinado newBoss = subordinados.get(k);
				//aqui metemos el jefe que ascendio, a los subordinados
				subordinados.stream().filter(o -> newBoss.getSubordinates().contains(o.getName())).forEach(o -> o.setBoss(newBoss.getName()));
				//aqui metemos al jefe que ascendio, quien es su nuevo jefe
				subordinados.get(k).setBoss(subordinado.getBoss());
			}
		}
		// se mete al subordinado que a ascendio a jefe, a su nuevo jefe. 
		if(boss!=null) {
			boss.getSubordinates().add(name);
		}
	}


	/**
	 * aqui se pinta el organigrama
	 * @param subordinado
	 * @param subordinados
	 * @param tab
	 */
	public static void printOrganigrama(Subordinado subordinado, List<Subordinado> subordinados, String tab) {
		System.out.println(tab+subordinado.getName()+"\n");
		if(!subordinado.getSubordinates().isEmpty()) {
			System.out.println(tab+"|\n");
			System.out.println(tab+"|\n");
		}
		// se recorre y se llama al mismo metodo para imprimir con recursividad 
		subordinados.stream().filter(e -> subordinado.getSubordinates().contains(e.getName())).forEach(e -> printOrganigrama(e, subordinados, tab+"\t"));
		
	}
}
