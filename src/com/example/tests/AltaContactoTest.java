package com.example.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.example.informacion.AltaContacto;
import com.example.informacion.ContactoAgenda;

public class AltaContactoTest extends ActivityInstrumentationTestCase2<AltaContacto> {

	/*
	 * 
	 * Este clase de test se usa para realizar tests en la actividad AltaContacto
	 * 
	 * A su vez también se estan realizando tests 
	 * a las funciones getter/setter de la clase ContactoAgenda
	 */
	
	private AltaContacto alta;
	private ContactoAgenda contacto;

	public AltaContactoTest() {
		super(AltaContacto.class);
		
	}
	
	public AltaContactoTest(Class<AltaContacto> activityClass) {
		super(activityClass);
		
	}
	
	 @Override
	 protected void setUp() throws Exception {
		 super.setUp();
		 alta = getActivity();
		 contacto = new ContactoAgenda();
	 }
	
	public void tearDown() throws Exception
	{
		super.tearDown();
		alta = null;
	}
	
	public void testMailVacio() throws Exception
	{
		
	}
	
	public void testNombreVacio() throws Exception
	{

	}
	
	public void testExistenRegistros() throws Exception
	{

	}
	
	public void testNoExistenRegistros() throws Exception
	{

	}
	
	public void testExisteContacto() throws Exception
	{

	}
	
	public void testNoExisteContacto() throws Exception
	{

	}
}
