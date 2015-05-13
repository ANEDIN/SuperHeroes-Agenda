package com.example.tests;

import android.test.ActivityInstrumentationTestCase2;

import com.example.informacion.AltaContacto;
import com.example.informacion.ContactoAgenda;

public class AltaContactoTest extends ActivityInstrumentationTestCase2<AltaContacto> {

	/*
	 * 
	 * Este clase de test se usa para realizar tests en la actividad AltaContacto
	 * 
	 * Tests de la función validar comprobarContacto
	 * 		inicio --> 1 path
	 * 		existen 2 if's --> + 2 paths 
	 * 		existe 1 while --> + 1 path
	 * 		existe 1 if dentro del while --> + 1 path
	 * 		
	 * 		total paths ==> 5 
	 * 
	 * 		Paths:
	 * 			(1.1) Test AltaContacto (linea 201) retorna true
	 *			(1.2) Test AltaContacto (linea 201) retorna false
	 * 			(2.1) Test AltaContacto (linea 206) retorna true
	 * 			(2.2) Test AltaContacto (linea 206) retorna false
	 * 			(3.1) Test AltaContacto (linea 211) retorna true		
	 * 					(debido a que depende de los datos de la BD siempre retorna false)
	 * 			(3.2) Test AltaContacto (linea 211) retorna false
	 * 
	 * 			(debido a que depende de los datos de la BD no se pueden probar)
	 * 			(4.1) Test AltaContacto (linea 213) retorna true
	 * 			(4.2) Test AltaContacto (linea 213) retorna false
	 * 
	 * 
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
		/*
		 *
		 *  (1.1) Test AltaContacto (linea 201) retorna true
		 * 
		 */
		
		boolean result;

		contacto.setMail("");
		result = alta.comprobarContacto(contacto);
		
		assertEquals(contacto.getMail(), "");
		assertEquals(result, false);
	}
	
	
	
	public void testNombreVacio() throws Exception
	{
		/*
		 *
		 * 	(1.2) Test AltaContacto (linea 201) retorna false
		 * 	(2.1) Test AltaContacto (linea 206) retorna true
		 * 
		 */
		
		boolean result;

		contacto.setNombre("");
		contacto.setMail("mail");
		result = alta.comprobarContacto(contacto);
		
		assertEquals(contacto.getNombre(), "");
		assertEquals(contacto.getMail(), "mail");
		assertEquals(result, false);
	}
	
	public void testExistenRegistros() throws Exception
	{
		/*
		 *
		 * 	(1.2) Test AltaContacto (linea 201) retorna false
		 * 	(2.2) Test AltaContacto (linea 206) retorna false
		 *	(3.1) Test AltaContacto (linea 211) retorna true
		 *
		 * 
		 */
		
		boolean result;
		
		contacto.setNombre("Eduardo");
		contacto.setMail("eduardo@gmail.com");
		result = alta.comprobarContacto(contacto);
		
		assertEquals(contacto.getNombre(), "Eduardo");
		assertEquals(contacto.getMail(), "eduardo@gmail.com");
		assertEquals(result, false);
	}
	
	public void testNoExistenRegistros() throws Exception
	{
		/*
		 *
		 * 	(1.2) Test AltaContacto (linea 201) retorna false
		 * 	(2.2) Test AltaContacto (linea 206) retorna false
		 *	(3.2) Test AltaContacto (linea 211) retorna false
		 * 
		 */
		
		boolean result;
		
		contacto.setNombre("nombre");
		contacto.setMail("mail");
		result = alta.comprobarContacto(contacto);
		
		assertEquals(contacto.getNombre(), "nombre");
		assertEquals(contacto.getMail(), "mail");
		assertEquals(result, true);
	}
	
	public void testExisteContacto() throws Exception
	{
		/*
		 *
		 * 	(1.2) Test AltaContacto (linea 201) retorna false
		 * 	(2.2) Test AltaContacto (linea 206) retorna false
		 *	(3.1) Test AltaContacto (linea 211) retorna true
		 *	(4.1) Test AltaContacto (linea 213) retorna true
		 * 
		 */
		
		boolean result;
		
		contacto.setNombre("Eduardo");
		contacto.setMail("eduardo@gmail.com");
		result = alta.comprobarContacto(contacto);
		
		assertEquals(contacto.getNombre(), "Eduardo");
		assertEquals(contacto.getMail(), "eduardo@gmail.com");

		assertEquals(result, false);
	}
	
	public void testNoExisteContacto() throws Exception
	{
		/*
		 *
		 * 	(1.2) Test AltaContacto (linea 201) retorna false
		 * 	(2.2) Test AltaContacto (linea 206) retorna false
		 *	(3.1) Test AltaContacto (linea 211) retorna true
		 *	(4.2) Test AltaContacto (linea 213) retorna false
		 * 
		 */
		
		boolean result;
		
		contacto.setNombre("nombre");
		contacto.setMail("mail");
		result = alta.comprobarContacto(contacto);
		
		assertEquals(contacto.getNombre(), "nombre");
		assertEquals(contacto.getMail(), "mail");
		assertEquals(result, true);
	}
}
