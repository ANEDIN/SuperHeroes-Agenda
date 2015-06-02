package com.example.tests;

import com.example.informacion.BaseDatosContactos;
import com.example.informacion.BaseDatosGlobal;
import com.example.informacion.ContactoAgenda;

import junit.framework.TestCase;

public class testBaseDatosContactos extends TestCase {

	private BaseDatosGlobal bdGlobal;
	private BaseDatosContactos bdContactos;
	private ContactoAgenda contacto;
	
	public void setUp() throws Exception
	{
		super.setUp();
		bdGlobal = BaseDatosGlobal.getInstance();
		bdContactos = bdGlobal.agendaBaseDatos;
		contacto = new ContactoAgenda();
	}
	
	public void tearDown() throws Exception
	{
		super.tearDown();
		bdGlobal = null;
		bdContactos = null;
		contacto = null;
	}
	
	public void testConexion() throws Exception
	{
		assertNotSame(bdGlobal, null);
	}
	
	public void testInsertarContacto() throws Exception
	{
		contacto.setNombre("Pedro");
		contacto.setDireccion("c/ San Juan 2");
		contacto.setMail("pedro@mail.es");
		contacto.setMiembroFacebook(0);
		contacto.setMiembroGoogle(0);
		contacto.setMiembroLinnkedin(0);
		contacto.setMiembroTwitter(0);
		contacto.setTelefono("999888777");
		contacto.setTipoContacto("Laboral");

		boolean resultado = bdContactos.insertarContacto(contacto);
		assertEquals(resultado, true);
	}
}
