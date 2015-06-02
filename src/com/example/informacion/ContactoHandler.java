package com.example.informacion;

import java.util.Iterator;

public class ContactoHandler {

	public int OK = 0; 
	public int NO_MAIL = 1;
	public int NO_NOMBRE = 2;
	public int MAIL_ERROR = 3; 
	
	public ContactoHandler()
	{}
	
	public int comprobarContacto(ContactoAgenda contactoActual) {
		
		Iterator<ContactoAgenda> itrContactos = AgendaGlobal.getInstance().miAgenda.iterator();
		if (contactoActual.getMail().equals("")){
		 	return NO_MAIL;
		}
		
		if (contactoActual.getNombre().equals("")){
		 	return NO_NOMBRE;
		}
		
		while(itrContactos.hasNext()){
			ContactoAgenda contacto = itrContactos.next();
			if (contactoActual.getMail().equals(contacto.getMail())){
				return MAIL_ERROR;
			}
		}		
	
		return OK;
	}
	
	public ContactoAgenda recuperarContacto(int posicion)
	{
		ContactoAgenda contacto = AgendaGlobal.getInstance().miAgenda.get(posicion);
		
		if(contacto.getNombre().equals(""))  
			contacto.setNombre("Nombre no entrado");

		if (contacto.getDireccion().equals(""))
			contacto.setDireccion("Direccion no entrado");
		
		if (contacto.getMail().equals(""))
			contacto.setMail("Mail no entrado");
		
		if (contacto.getTelefono().equals("")) 
			contacto.setTelefono("Telefono no disponible");
		
		return contacto;
	}
}
