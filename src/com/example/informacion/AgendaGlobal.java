package com.example.informacion;

import java.util.ArrayList;

public class AgendaGlobal {
		
		public ArrayList<contactoAgenda> miAgenda;

		private AgendaGlobal() {
		    miAgenda = new ArrayList<contactoAgenda>();
		  }

		  private static AgendaGlobal instance;

		  public static AgendaGlobal getInstance() {
		    if (instance == null) instance = new AgendaGlobal();
		    return instance;
		  }
		}
