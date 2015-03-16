package com.example.informacion;



import java.util.Iterator;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class AltaContacto extends Activity {

	contactoAgenda contactoActual;
	private EditText edtxtNombre;
	private EditText edtxtDireccion;
	private EditText edtxtMail;
	private EditText edtxtTelefono;
	
	
//	private Button botonCancelar;
	//private Button botonAceptar;
	
	private ImageView imgContacto;
	private Spinner tipoContacto;
	private RadioButton sexo;

	private NotificationCompat.Builder notification;
	private PendingIntent pIntent;
	private NotificationManager manager;
	private Intent resultIntent;
	private TaskStackBuilder stackBuilder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alta_contacto);
		
		contactoActual = new contactoAgenda();
		contactoActual.setDrawableImageID(R.drawable.info_icon); // Ponemos una imagen por defecto
		
		edtxtNombre = (EditText) findViewById(R.id.nombre);
		edtxtDireccion = (EditText) findViewById(R.id.direccion);
		edtxtMail= (EditText) findViewById(R.id.mail);
		edtxtTelefono = (EditText) findViewById(R.id.telefono);
		
		imgContacto = (ImageView) findViewById(R.id.imagenUsuario);
		
		tipoContacto = ( Spinner)findViewById(R.id.tipoContacto);
		
		sexo = (RadioButton) findViewById(R.id.hombre);
		
	 
	    imgContacto.setImageResource(R.drawable.addcontacts);
	    registerForContextMenu(imgContacto);
	    sexo.setChecked(true);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alta_usuario, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onCheckboxClicked(View view) {
	    // Is the view now checked?
	    boolean checked = ((CheckBox) view).isChecked();
	    
	    // Check which checkbox was clicked
	    switch(view.getId()) {
	        case R.id.facebook:
	            if (checked) {
	                contactoActual.setMiembroFacebook(1);
	            	Toast.makeText(this,"Eres miembro de Facebook", Toast.LENGTH_LONG).show();
	            }
	            else {
	                contactoActual.setMiembroFacebook(0);
	            	Toast.makeText(this,"Ya no eres miembro de Facebook", Toast.LENGTH_LONG).show();
	            }
	            break;
	        case R.id.google:
	            if (checked){
	                contactoActual.setMiembroGoogle(1);
	            	
	            }
	            else {
	            	contactoActual.setMiembroGoogle(0);// I'm lactose intolerant
	            	
	            }
	            break;
	        case R.id.linkedin:
	            if (checked)
	            	contactoActual.setMiembroLinnkedin(1);
	            else
	            	contactoActual.setMiembroLinnkedin(0);// Remove the meat
	            break;
	        case R.id.twitter:
	            if (checked)
	            	contactoActual.setMiembroTwitter(1);
	            else
	            	contactoActual.setMiembroTwitter(0);
	            break;
	        // TODO: Veggie sandwich
	    }
	}
	
	public void OnRadioButtonClick(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.hombre:
	            if (checked)
	            	contactoActual.setSexo(1);
	            break;
	        case R.id.mujer:
	            if (checked)
	            	contactoActual.setSexo(0);
	            break;
	    }
	}
	
	public void OnClickAceptar (View view){
		
		
		contactoActual.setNombre(edtxtNombre.getText().toString());
		contactoActual.setMail(edtxtMail.getText().toString());
		contactoActual.setDireccion(edtxtDireccion.getText().toString());
		contactoActual.setTelefono(edtxtTelefono.getText().toString());
		
		contactoActual.setTipoContacto(tipoContacto.getSelectedItem().toString());
		
		if (sexo.isChecked()) contactoActual.setSexo(1);
		
		if (comprobarContacto(contactoActual)) {
									BaseDatosGlobal.getInstance(AltaContacto.this).agendaBaseDatos.insertarContacto(contactoActual);//mail
						//			insertarContacto( BaseDatosGlobal.getInstance(AltaContacto.this).agendaBaseDatos,contactoActual);//mail

									AgendaGlobal.getInstance().miAgenda.add(contactoActual);
									notification = new NotificationCompat.Builder(AltaContacto.this);
							           //Title for Notification
							           notification.setContentTitle("Interfaces actualización");
							           //Message in the Notification
							           notification.setContentText("Alta del contacto " + contactoActual.getNombre()+  " correcta!");
							           //Alert shown when Notification is received
							           notification.setTicker("Nueva notificación Android:");
							           //Icon to be set on Notification
							           notification.setSmallIcon(R.drawable.ironman);
							           
							           //PARTTE DE TOCAR NOTIFICACION TE ENVIA A UNA ACTIVIDAD: 
							           //Creating new Stack Builder
							           
							       /*    stackBuilder = TaskStackBuilder.create(AltaContacto.this);
							           stackBuilder.addParentStack(MainActivity.class);
							           //Intent which is opened when notification is clicked
							           resultIntent = new Intent(AltaContacto.this, MainActivity.class);
							           stackBuilder.addNextIntent(resultIntent);
							           pIntent =  stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
							               notification.setContentIntent(pIntent);*/
							             manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
							               manager.notify(0, notification.build());
							            Intent intent = new Intent(this, MainActivity.class);
							            startActivity(intent);
									}
								 
		else Toast.makeText(this,"Error en el alta", Toast.LENGTH_LONG).show();
		
     }
	
	private boolean comprobarContacto(contactoAgenda contactoActual) {
		
		Iterator<contactoAgenda> itrContactos = AgendaGlobal.getInstance().miAgenda.iterator();
		if (contactoActual.getMail().equals("")) 
		{
		 	Toast.makeText(this,"Error en el alta, debe haber mail", Toast.LENGTH_LONG).show();
		 	return false;
		}
		
		if (contactoActual.getNombre().equals("")) 
		{
		 	Toast.makeText(this,"Error en el alta, debe haber nombre", Toast.LENGTH_LONG).show();
		 	return false;
		}
		
		while(itrContactos.hasNext()){
			contactoAgenda contacto = itrContactos.next();
			if ( contactoActual.getMail().equals(contacto.getMail()))
					{
					 	Toast.makeText(this,"El mail esta dado de alta", Toast.LENGTH_LONG).show();
					 	return false;
					}
		}
		
	return true;

	}



	public void OnClickCancelar (View view){
		
		
		
		Intent intent = new Intent(this,MainActivity.class);
		Toast.makeText(this,"Eres una nenaza", Toast.LENGTH_LONG).show();
		
	    startActivity(intent);
     }

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	                            ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);

	    if (v.getId() == R.id.imagenUsuario) {
	        getMenuInflater().inflate(R.menu.menuimagenes, menu);
	    }
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {

	    switch (item.getItemId()) {
	        
				case R.id.menuimagen1:
					imgContacto.setImageResource(R.drawable.thor);
					contactoActual.setDrawableImageID(R.drawable.thor);
					return true;

				case R.id.menuimagen2:
					imgContacto.setImageResource(R.drawable.spidey);
					contactoActual.setDrawableImageID(R.drawable.spidey);
					return true;
				
				case R.id.menuimagen3:
					imgContacto.setImageResource(R.drawable.hulk);
					contactoActual.setDrawableImageID(R.drawable.hulk);
					return true;

				case R.id.menuimagen4:
					imgContacto.setImageResource(R.drawable.ironman);
					contactoActual.setDrawableImageID(R.drawable.ironman);
					return true;
				
				case R.id.menuimagen5:
					imgContacto.setImageResource(R.drawable.capitanamerica);
					contactoActual.setDrawableImageID(R.drawable.capitanamerica);
					return true;

				default:
					imgContacto.setImageResource(R.drawable.info_icon);
					contactoActual.setDrawableImageID(R.drawable.info_icon);
					return super.onContextItemSelected(item);
				
	    }

	}

	}	

