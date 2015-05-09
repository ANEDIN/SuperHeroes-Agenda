package com.example.informacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import com.example.informacion.R;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends Activity {
	
	public static final String OPERACION="Validar";
	public static final String AlgoritmoEncriptacion="MD5"; // Puedes elgir "MD2", "SHA-1", "SHA-256", "SHA-384", "SHA-512" Y "MD5"
	private static final String PREFS_NAME ="Usuarios";
	
	private EditText entradaUsuario;
	private EditText entradaPassword;
	private Button botonLogin;
	private TextView Mensaje;
	private TextView cuentaNueva;
	private Usuario usuarioaValidar;
	
	private NotificationCompat.Builder notification;
	private PendingIntent pIntent;
	private NotificationManager manager;
	private Intent resultIntent;
	private TaskStackBuilder stackBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		entradaUsuario = (EditText) findViewById(R.id.TxtUsuario);
        entradaPassword = (EditText) findViewById(R.id.TxtPassword);
        botonLogin = (Button) findViewById(R.id.BtnEntrar);
        Mensaje = (TextView) findViewById(R.id.LblMensaje);
        cuentaNueva = (TextView) findViewById(R.id.TextCuenta);
        
        entradaUsuario.setText("");
        entradaPassword.setText("");
        
        usuarioaValidar = new Usuario();         
	}
	
	//Se llama cuando clico en el Texto Cuenta Nueva
	public void ClickCuentaNueva(View view){
		Toast.makeText(getApplicationContext(),"To DO: No necesario en el problema", Toast.LENGTH_LONG).show();
	}
	
	public boolean validarUsuario(Usuario usuario)
	{
		//Validación de login y password lleno
    	if ((usuario.getNombre().equals("")==false ) && (usuario.getContrasenya().equals("")==false)){
    		return true;
    	}
    	//Validación del login vacio ( solo login)
    	else if( (usuario.getNombre().equals("")) && ( usuario.getContrasenya().equals("") == false)) {
    		Toast.makeText(getApplicationContext(),"Login vacio",Toast.LENGTH_SHORT).show();
		}
    	//Validación de password vacio ( solo password)
    	else if((usuario.getNombre().equals("")==false ) && (usuario.getContrasenya().equals(""))) {
    		Toast.makeText(getApplicationContext(), "Password vacio", Toast.LENGTH_SHORT).show();
		}
    	//Validación de ambos campos vacios
    	else { //if (usuario.getNombre().equals("") && usuario.getContrasenya().equals("")) {
	    	Toast.makeText(getApplicationContext(), "Login y Password vacios", Toast.LENGTH_SHORT).show();
		}
	    return false;
	}
	
	/** Llamado cuando pulsamos el botón Login */
	public void ClicLogin (View view){
		
		Integer resultado = 0;
		usuarioaValidar.setNombre(entradaUsuario.getText().toString());
	    usuarioaValidar.setContrasenya(entradaPassword.getText().toString());
	    
	    if (validarUsuario(usuarioaValidar)) {
	    	//llamada a la tarea asincrona
    		try {
				MiTarea tarea = new MiTarea();
				tarea.execute(usuarioaValidar);
				resultado= tarea.get(); // obtengo resultado de la tara no deberia ir pero bueno...asi pierde lo de asincroa
				if(resultado==1){
					// Contraseña y nombre usuario correcto. Pasamos actividad principal.Toast.makeText(getApplicationContext(), "Usuario" + usuarioaValidar.getNombre() + " con Contraeña\n"+ usuarioaValidar.getContraseña() + "correctos", Toast.LENGTH_SHORT).show();
					//Toast.makeText(getApplicationContext(), "Adelante", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(this, MainActivity.class);
					startActivity(intent);
				} else if (resultado==2)
					Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
				else if( resultado ==3){
					//No haces nada porque das de alta un usuario nuevo y ya lo notificas en la tarea
					// Dejo por si mas adelante hay que hacer alguna cosa mas.
				}
		
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	}
	
	class MiTarea extends AsyncTask<Usuario, String, Integer> {
		
		
		ProgressDialog mProgress;
		Usuario actual;
	
	
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
		super.onPreExecute();
		mProgress= new ProgressDialog(Login.this);
	    mProgress.setMessage("Verificando usuario...Por favor espere");
	    mProgress.show();
	   // mProgress.setMessage ("Tarea comenzada");
		    
		}
	    
	    @Override
	    protected Integer doInBackground(Usuario...usuarioaValidar) {
	    	
	    //   	Usuario actual;
		Integer resultado=0;
		
		SharedPreferences usuario = getSharedPreferences(PREFS_NAME, 0);    
		
		actual= new Usuario();
		
		try 
		{	
			Thread.sleep(1000);
			
			String nombre=usuarioaValidar[0].getNombre();
			actual.setNombre(StringEncriptacion.getStringMessageDigest(nombre,AlgoritmoEncriptacion));
			
			String contrasenya=usuarioaValidar[0].getContrasenya();
			actual.setContrasenya(StringEncriptacion.getStringMessageDigest(contrasenya,AlgoritmoEncriptacion));
			//Como primer parámetro "key" indicaremos la clave/palabra que queremos recuperar. Y como segundo parámetro "defValue" indicaremos un valor 
			//por defecto a devolver en caso de que el dato que queremos recuperar no exista. 
			String silent = usuario.getString(actual.getNombre(),"");
			
			if (!(silent.equals("")))//UsuariosGlobal.getInstance().misUsuarios.containsKey(actual.getNombre())
			{
				//El Hashmap contiene la clave ( usuario existe)
				
			     if(actual.getContrasenya().equals(silent) )   //actual.getContraseña().equals(UsuariosGlobal.getInstance().misUsuarios.get(actual.getNombre()).getContraseña())
			     {
			    	 //El usuario existe y la contraseña coincide. Nombre correcto y contraseña correcta.
			    	 return 1;
			     } else {
			    	 //La contraseña no coincide. Nombre correcto y contraseña incorrecta
			    	 return 2;
			     }
			} else {
				//El usuario no existe, por lo que se crea un usuario nuevo.
			 	notification = new NotificationCompat.Builder(Login.this);
	           //Title for Notification
	           notification.setContentTitle("Login actualización");
	           //Message in the Notification
	           notification.setContentText("Alta del usuario " + nombre +  " se ha producido correctamente!");
	           //Alert shown when Notification is received
	           notification.setTicker("Nueva notificación Android:");
	           //Icon to be set on Notification
	           notification.setSmallIcon(R.drawable.info_icon);
	          
	           manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	           manager.notify(0, notification.build());
	           
	           SharedPreferences.Editor editor = usuario.edit();
	           editor.putString(actual.getNombre(), actual.getContrasenya());
	
	           // Commit the edits!
	           editor.commit();
	           //  UsuariosGlobal.getInstance().misUsuarios.put(actual.getNombre(),actual);
		           return 3;
				}
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
			return resultado;
	    }
	
	    @Override
	    protected void onPostExecute(Integer res) {
	    	if(mProgress != null){
	    		mProgress.dismiss();
	    	}
	
			super.onPostExecute(null);
	    }
	}
}
