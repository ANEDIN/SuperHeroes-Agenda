package com.example.informacion;

import java.io.Serializable;


public class ContactoAgenda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8823618714701946043L;
	private String Nombre;
	private String Direccion;
	private String Telefono;
	private String mail;
	
	//Cambio boolean por int ya que no va bien en las bases de datos, true=1, false=0
	private int miembroFacebook;
	private int miembroTwitter;
	private int miembroGoogle;
	private int miembroLinnkedin;
	private int sexo; // sexo hombre=1, mujer =0
	private String tipoContacto;
	private int drawableImageId;
	/**
	 * @param nombre del contacto
	 * @param direccion  del contacto
	 * @param telefono del contacto
	 * @param mail del contacto
	 * @param miembroFacebook es el contacto miembro?
	 * @param miembroTwitter es el contacto miembro?
	 * @param miembroGoogle es el contacto miembro?
	 * @param miembroLinnkedin es el contacto miembro?
	 * @param sexo del contacto
	 * @param tipoContacto tipo del contacto
	 * @param drawableImageID foto del contacto
	 */
	public ContactoAgenda(String nombre, String direccion, String telefono,
			String mail, int miembroFacebook, int miembroTwitter,
			int miembroGoogle, int miembroLinnkedin, int sexo,
			String tipoContacto,int drawableImageId ) {
		super();
		Nombre = nombre;
		Direccion = direccion;
		Telefono = telefono;
		this.mail = mail;
		this.miembroFacebook = miembroFacebook;
		this.miembroTwitter = miembroTwitter;
		this.miembroGoogle = miembroGoogle;
		this.miembroLinnkedin = miembroLinnkedin;
		this.sexo = sexo;
		this.tipoContacto = tipoContacto;
		this.drawableImageId = drawableImageId;
	}
	/**
	 * @return the drawableImageID
	 */
	public int getDrawableImageId() {
		return drawableImageId;
	}
	/**
	 * @param drawableImageID the drawableImageID to set
	 */
	public void setDrawableImageId(int drawableImageId) {
		this.drawableImageId = drawableImageId;
	}
	/**
	 * 
	 */
	public ContactoAgenda() {
		super();
	}
	/**
	 * @param nombre del contacto
	 * @param direccion del contacto
	 * @param telefono del contacto
	 * @param mail del contacto
	 */
	public ContactoAgenda(String nombre, String direccion, String telefono,
			String mail) {
		super();
		Nombre = nombre;
		Direccion = direccion;
		Telefono = telefono;
		this.mail = mail;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return Nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return Direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return Telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the miembroFacebook
	 */
	public int isMiembroFacebook() {
		return miembroFacebook;
	}
	/**
	 * @param miembroFacebook the miembroFacebook to set
	 */
	public void setMiembroFacebook(int miembroFacebook) {
		this.miembroFacebook = miembroFacebook;
	}
	/**
	 * @return the miembroTwitter
	 */
	public int isMiembroTwitter() {
		return miembroTwitter;
	}
	/**
	 * @param miembroTwitter the miembroTwitter to set
	 */
	public void setMiembroTwitter(int miembroTwitter) {
		this.miembroTwitter = miembroTwitter;
	}
	/**
	 * @return the miembroGoogle
	 */
	public int isMiembroGoogle() {
		return miembroGoogle;
	}
	/**
	 * @param miembroGoogle the miembroGoogle to set
	 */
	public void setMiembroGoogle(int miembroGoogle) {
		this.miembroGoogle = miembroGoogle;
	}
	/**
	 * @return the miembroLinnkedin
	 */
	public int isMiembroLinnkedin() {
		return miembroLinnkedin;
	}
	/**
	 * @param miembroLinnkedin the miembroLinnkedin to set
	 */
	public void setMiembroLinnkedin(int miembroLinnkedin) {
		this.miembroLinnkedin = miembroLinnkedin;
	}
	/**
	 * @return the sexo
	 */
	public int isSexo() {
		return sexo;
	}
	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	/**
	 * @return the tipoContacto
	 */
	public String getTipoContacto() {
		return tipoContacto;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("contactoAgenda [Nombre=");
		builder.append(Nombre);
		builder.append(", Direccion=");
		builder.append(Direccion);
		builder.append(", Telefono=");
		builder.append(Telefono);
		builder.append(", mail=");
		builder.append(mail);
		builder.append(", miembroFacebook=");
		builder.append(miembroFacebook);
		builder.append(", miembroTwitter=");
		builder.append(miembroTwitter);
		builder.append(", miembroGoogle=");
		builder.append(miembroGoogle);
		builder.append(", miembroLinnkedin=");
		builder.append(miembroLinnkedin);
		builder.append(", sexo=");
		builder.append(sexo);
		builder.append(", tipoContacto=");
		builder.append(tipoContacto);
		builder.append("]");
		return builder.toString();
	}
	/**
	 * @param tipoContacto the tipoContacto to set
	 */
	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

}

