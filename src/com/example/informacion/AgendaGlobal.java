package com.example.informacion;

import java.util.ArrayList;

public class AgendaGlobal {

	public ArrayList<ContactoAgenda> miAgenda;

	private AgendaGlobal() {
		miAgenda = new ArrayList<ContactoAgenda>();
	}
	
	private static AgendaGlobal instance;
	
	public static AgendaGlobal getInstance() {
		if (instance == null) instance = new AgendaGlobal();
			return instance;
	}
}
