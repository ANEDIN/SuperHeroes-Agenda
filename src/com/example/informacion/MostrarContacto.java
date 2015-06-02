package com.example.informacion;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MostrarContacto extends Activity {
	
	public static final String EXTRA_MAIL = "com.example.informacion.MAIL";
	public static final String EXTRA_POSITION = "com.example.informacion.POSITION";
	
	ContactoAgenda contactoActual;
	ContactoHandler contactoHandler;
	private TextView edtxtNombre;
	private TextView edtxtDireccion;
	private TextView edtxtMail;
	private TextView edtxtTelefono;
	//private Button botonCancelar;
	//private Button botonAceptar;
	private ImageView imgContacto;
	private Spinner tipocontacto;
	private CheckBox chkFacebook;
	private CheckBox chkTwitter;
	private CheckBox chkGoogle;
	private CheckBox chkLinkedin;
	private RadioGroup sexo;
	//private VideoView intro;
	
	private int posicion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_contacto);
		
		posicion = 0;
		contactoActual = new ContactoAgenda();
		contactoHandler = new ContactoHandler();
		inicializarVariablesGlobales();
		
		obtenerExtras();
		rellenarFormularioContacto(posicion);
	}
	
	private void inicializarVariablesGlobales() {
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
	}
	
	private void rellenarFormularioContacto(int posicion) {
		contactoActual = contactoHandler.recuperarContacto(posicion);
		
		edtxtNombre.setText(contactoActual.getNombre());
		edtxtDireccion.setText(contactoActual.getDireccion());
		edtxtMail.setText(contactoActual.getMail());
		edtxtTelefono.setText(contactoActual.getTelefono());
		imgContacto.setImageResource(contactoActual.getDrawableImageId());
		chkFacebook.setChecked(contactoActual.isMiembroFacebook()==1);
		chkTwitter.setChecked(contactoActual.isMiembroTwitter()==1);
		chkLinkedin.setChecked(contactoActual.isMiembroLinnkedin()==1);
		chkGoogle.setChecked(contactoActual.isMiembroGoogle()==1);

		String[] arraytipocontactos = getResources().getStringArray(R.array.tipoContacto_array);
		tipocontacto.setSelection(Arrays.asList(arraytipocontactos).indexOf(contactoActual.getTipoContacto()));
		
		if (contactoActual.isSexo() == 1) sexo.check(R.id.hombre);
		else sexo.check(R.id.mujer);
	}
	
	
	private void obtenerExtras() {
		//This is GetExtra 
		//String Mail;
		Intent intent = getIntent();
		Bundle extras= intent.getExtras();
		if (extras != null) {
			//Mail = extras.getString(EXTRA_MAIL);
			posicion = extras.getInt(EXTRA_POSITION);
			/* Toast.makeText(getApplicationContext(), "Hemos recibido a"+ posicion + Mail, 
								Toast.LENGTH_SHORT).show(); */
		}
	}

}
