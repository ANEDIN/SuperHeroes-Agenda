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

	}

	public void testNombreUsuarioVacio() throws Exception
	{
		
	}
	
	public void testContrasenyaUsuarioVacia() throws Exception
	{

	}
	
	public void testUsuarioVacio() throws Exception
	{

	}
	
	public void testLoginOk()
	{

	}
}
