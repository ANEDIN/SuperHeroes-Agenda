package com.example.informacion;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {
	
	private String nombre;
	private String contraseña;
	private String mail;
	private int algoritmoEncriptacion;
	

	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub

	}
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String nombre, String contraseña, String mail) {
		super();
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.mail = mail;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	

}
