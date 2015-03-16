package com.example.informacion;

import java.util.Arrays;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MostrarContacto extends Activity {
	
	public static final String EXTRA_MAIL = "com.example.informacion.MAIL";
	public static final String EXTRA_POSITION = "com.example.informacion.POSITION";
	
	contactoAgenda contactoActual;
	private TextView edtxtNombre;
	private TextView edtxtDireccion;
	private TextView edtxtMail;
	private TextView edtxtTelefono;
	
	
	private Button botonCancelar;
	private Button botonAceptar;
	
	private ImageView imgContacto;
	private Spinner tipocontacto;
	private CheckBox chkFacebook;
	private CheckBox chkTwitter;
	private CheckBox chkGoogle;
	private CheckBox chkLinkedin;
	private RadioGroup sexo;
	//private VideoView intro;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_contacto);
		
		String Mail = null;
		int posicion = 0;
		contactoActual = new contactoAgenda();
		
		edtxtNombre = (TextView) findViewById(R.id.txtNombre);
		edtxtDireccion = (TextView) findViewById(R.id.txtDireccion);
		edtxtMail= (TextView) findViewById(R.id.txtMail);
		edtxtTelefono = (TextView) findViewById(R.id.TxtTelefono);
		
		imgContacto = (ImageView) findViewById(R.id.imagenUsuario);
		
		tipocontacto = ( Spinner) findViewById(R.id.tipoContacto);
		chkFacebook = ( CheckBox) findViewById(R.id.facebook);
		chkTwitter= ( CheckBox) findViewById(R.id.twitter);
		chkGoogle = ( CheckBox) findViewById(R.id.google);
		chkLinkedin= ( CheckBox) findViewById(R.id.linkedin);
		
		sexo = (RadioGroup) findViewById(R.id.grupo);
		
		//This is GetExtra 
		Intent intent = getIntent();
		Bundle extras= intent.getExtras();
		if (extras != null) {
		  Mail = extras.getString(EXTRA_MAIL);
		  posicion =extras.getInt(EXTRA_POSITION);
		}
		
		
	//	Toast.makeText(getApplicationContext(), "Hemos recibido a"+ posicion + Mail, Toast.LENGTH_SHORT).show();
			
		if ( AgendaGlobal.getInstance().miAgenda.get(posicion).getNombre().equals("") ) edtxtNombre.setText("Nombre no entrado");
		else edtxtNombre.setText(AgendaGlobal.getInstance().miAgenda.get(posicion).getNombre());
		
		if ( AgendaGlobal.getInstance().miAgenda.get(posicion).getDireccion().equals("") ) edtxtDireccion.setText("Direccion no entrado");
		else edtxtDireccion.setText(AgendaGlobal.getInstance().miAgenda.get(posicion).getDireccion());
		
		if ( AgendaGlobal.getInstance().miAgenda.get(posicion).getMail().equals("")) edtxtMail.setText("Mail no entrado");
		else edtxtMail.setText(AgendaGlobal.getInstance().miAgenda.get(posicion).getMail());
		
		if ( AgendaGlobal.getInstance().miAgenda.get(posicion).getTelefono().equals("")) edtxtTelefono.setText("Telefono no disponible");
		else edtxtTelefono.setText(AgendaGlobal.getInstance().miAgenda.get(posicion).getTelefono());
	
		imgContacto.setImageResource(AgendaGlobal.getInstance().miAgenda.get(posicion).getDrawableImageID());
		
		if (AgendaGlobal.getInstance().miAgenda.get(posicion).isMiembroFacebook()==1) chkFacebook.setChecked(true);
		else chkFacebook.setChecked(false);
		
		if (AgendaGlobal.getInstance().miAgenda.get(posicion).isMiembroTwitter() ==1 )  chkTwitter.setChecked(true);
		else chkTwitter.setChecked(false);
		
		if (AgendaGlobal.getInstance().miAgenda.get(posicion).isMiembroLinnkedin() ==1 )  chkLinkedin.setChecked(true);
		else chkLinkedin.setChecked(false);
		
		if (AgendaGlobal.getInstance().miAgenda.get(posicion).isMiembroGoogle() ==1 )  chkGoogle.setChecked(true);
		else chkGoogle.setChecked(false);
		
		
		String[] arraytipocontactos = getResources().getStringArray(R.array.tipoContacto_array);
		tipocontacto.setSelection(Arrays.asList(arraytipocontactos).indexOf(AgendaGlobal.getInstance().miAgenda.get(posicion).getTipoContacto()));
	//	tipocontacto.setSelection(((ArrayAdapter<String>)tipocontacto.getAdapter()).getPosition("Familia");
		if (AgendaGlobal.getInstance().miAgenda.get(posicion).isSexo() == 1) sexo.check(R.id.hombre);
		else sexo.check(R.id.mujer);
	}

}
