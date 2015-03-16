package com.example.informacion;

import java.util.HashMap;

public class UsuariosGlobal {
		
		public HashMap< String, Usuario> misUsuarios;

		private UsuariosGlobal() {
		    misUsuarios = new HashMap< String, Usuario>();
		  }

		  private static UsuariosGlobal instance;

		  public static UsuariosGlobal getInstance() {
		    if (instance == null) instance = new UsuariosGlobal();
		    return instance;
		  }
		}
