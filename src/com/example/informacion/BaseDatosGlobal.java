package com.example.informacion;

import android.content.Context;

public class BaseDatosGlobal {

	public BaseDatosContactos agendaBaseDatos;
	private Context mCon;

	private BaseDatosGlobal() {
	}
	
	private BaseDatosGlobal(Context con) { 
		this.mCon=con;
	    agendaBaseDatos = new BaseDatosContactos(con);
	}
	
	private static BaseDatosGlobal instance;

	public static BaseDatosGlobal getInstance() {
		if (instance == null) instance = new BaseDatosGlobal();
	    return instance;
	}

	public static BaseDatosGlobal getInstance(Context con) {
		if (instance == null) instance = new BaseDatosGlobal(con);
		return instance;
	}
}
