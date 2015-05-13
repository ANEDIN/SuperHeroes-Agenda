package com.example.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.example.informacion.Login;
import com.example.informacion.R;
import com.example.informacion.Usuario;
import com.robotium.solo.Solo;

public class LoginTest extends ActivityInstrumentationTestCase2<Login> {

	/*
	 * 
	 * Este clase de test se usa para realizar tests en la actividad Login
	 * 
	 * Tests de la función validar validarUsuario
	 * 		inicio --> 1 path
	 * 		existen 3 if's (+ 1 else) --> + 3 paths 
	 * 		
	 * 		cada if continene un and --> + 3 paths
	 * 		total paths ==> 7 
	 * 
	 * 		Paths:
	 * 			(1) Test Login (linea 72) retorna true
	 *			(2) Test Login (linea 72) retorna false
	 * 			(3) Test Login (linea 76) retorna true
	 * 			(4) Test Login (linea 76) retorna false
	 * 			(5) Test Login (linea 80) retorna true
	 * 			(6) Test Login (linea 80) retorna false
	 * 			(7) Test Login (linea 84) retorna true
	 * 
	 * 
	 * 
	 * A su vez también se estan realizando tests 
	 * a las funciones getter/setter de la clase Usuario
	 */
	
	private Solo solo;
	private Login login;
	private Usuario usuario;

	public LoginTest() {
		super(Login.class);
		
	}
	
	public LoginTest(Class<Login> activityClass) {
		super(activityClass);
		
	}
	
	 @Override
	 protected void setUp() throws Exception {
		 super.setUp();
		 login = getActivity();
		 usuario = new Usuario();
		 solo = new Solo(getInstrumentation(), getActivity());
	 }
	
	public void tearDown() throws Exception
	{
		super.tearDown();
		if(solo != null)
			solo.finishOpenedActivities();
		login = null;
	}
	
	public void testUsuarioRellenado() throws Exception
	{
		/*
		 *
		 *  (1) Test Login (linea 72) retorna true
		 * 
		 */
		
		boolean result;

		usuario.setNombre("admin");
		usuario.setContrasenya("12345");
		
		result = login.validarUsuario(usuario);
		
		assertEquals(usuario.getNombre(), "admin");
		assertEquals(usuario.getContrasenya(), "12345");
		assertEquals(result, true);
	}
	
	
	
	public void testNombreUsuarioVacio() throws Exception
	{
		/*
		 *
		 * 	(2) Test Login (linea 72) retorna false
		 * 	(3) Test Login (linea 76) retorna true
		 * 
		 */
		
		boolean result;

		usuario.setNombre("");
		usuario.setContrasenya("1234");
		result = login.validarUsuario(usuario);
		assertEquals(usuario.getNombre(), "");
		assertEquals(usuario.getContrasenya(), "1234");
		assertEquals(result, false);
	}
	
	public void testContrasenyaUsuarioVacia() throws Exception
	{
		/*
		 *
		 *	(2) Test Login (linea 72) retorna false
		 *	(4) Test Login (linea 76) retorna false
		 *	(5) Test Login (linea 80) retorna true
		 * 
		 */
		
		boolean result;
		
		usuario.setNombre("admin");
		usuario.setContrasenya("");
		result = login.validarUsuario(usuario);
		assertEquals(usuario.getNombre(), "admin");
		assertEquals(usuario.getContrasenya(), "");
		assertEquals(result, false);
	}
	
	public void testUsuarioVacio() throws Exception
	{
		/*
		 *
		 * 	(2) Test Login (linea 72) retorna false
		 * 	(4) Test Login (linea 76) retorna false
		 * 	(6) Test Login (linea 80) retorna false
		 * 	(7) Test Login (linea 84) retorna true
		 * 
		 */
		
		boolean result;

		usuario.setNombre("");
		usuario.setContrasenya("");
		result = login.validarUsuario(usuario);
		
		assertEquals(usuario.getNombre(), "");
		assertEquals(usuario.getContrasenya(), "");
		assertEquals(result, false);
	}
	
	public void testLoginOk()
	{
		usuario.setNombre("admin");
		usuario.setContrasenya("12345");
		
		//Access First value (edit-filed) and putting firstNumber value in it
		EditText entradaUsuario = (EditText) solo.getView(R.id.TxtUsuario);
		solo.clearEditText(entradaUsuario);
		solo.enterText(entradaUsuario, String.valueOf(usuario.getNombre()));
		
		//Access Second value (edit-filed) and putting SecondNumber value in it
		EditText entradaPassword = (EditText) solo.getView(R.id.TxtPassword);
		solo.clearEditText(entradaPassword);
		solo.enterText(entradaPassword, String.valueOf(usuario.getContrasenya()));
		
		Button botonLogin = (Button)solo.getCurrentActivity().findViewById(R.id.BtnEntrar);
		
		//Click on button
		solo.clickOnButton(botonLogin.getText().toString()); 
		solo.waitForActivity("com.example.informacion.MainActivity", 3000);
		solo.assertCurrentActivity("The activity should be MainActivity ", "MainActivity");
	}
}
