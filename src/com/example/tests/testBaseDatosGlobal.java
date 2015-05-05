package com.example.tests;

import com.example.informacion.BaseDatosGlobal;

import junit.framework.TestCase;

public class testBaseDatosGlobal extends TestCase {

	private BaseDatosGlobal bdGlobal;
	
	public void setUp() throws Exception
	{
		super.setUp();
		bdGlobal = BaseDatosGlobal.getInstance();
	}
	
	public void tearDown() throws Exception
	{
		super.tearDown();
		bdGlobal = null;
	}
	
	public void testConexion() throws Exception
	{
		assertNotSame(bdGlobal, null);	
	}
}
