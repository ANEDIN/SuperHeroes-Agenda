package com.example.informacion;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {
	
	private String nombre;
	private String contrasenya;
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
	
	public Usuario(String nombre, String contrasenya, String mail) {
		super();
		this.nombre = nombre;
		this.contrasenya = contrasenya;
		this.mail = mail;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
