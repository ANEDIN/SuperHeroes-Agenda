package com.example.informacion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener, OnItemLongClickListener {
	
	/*Es buena práctica definir keys para los extras de los Intents usando el nombre del paquete como prefijo.
	Esto nos asegura que son únicos en el caso de que nuestra app interactúe con otras aplicaciones.*/
	public static final String EXTRA_MAIL = "com.example.informacion.MAIL";
	public static final String EXTRA_POSITION = "com.example.informacion.POSITION";
	
	private ListView lvContactos;
	private contactoAdapter adapter;

	private int PosicionActual=0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);//setContentView(R.layout.activity_main);
		

				AgendaGlobal.getInstance().miAgenda = BaseDatosGlobal.getInstance(this).agendaBaseDatos.recuperarTodosContactos();

				
				adapter = new contactoAdapter(this, AgendaGlobal.getInstance().miAgenda);
				
				lvContactos = (ListView) findViewById(R.id.lvItems);
				// Asignamos el Adapter al ListView, en este punto hacemos que el
				// ListView muestre los datos que queremos.
				
				
				lvContactos.setAdapter(adapter);
				// Asignamos el Listener al ListView para cuando pulsamos sobre uno de
				// sus items.
				lvContactos.setOnItemClickListener(this);
				
				lvContactos.setOnItemLongClickListener(this);
				
				registerForContextMenu(lvContactos);
							
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		 MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.main_menu, menu);
	//	getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Intent intent;
		
		int id = item.getItemId();
		 switch (item.getItemId()) {
	        case (R.id.btAnadirContacto):
	           Toast.makeText(getApplicationContext(), "Has pulasado añadir Contacto", Toast.LENGTH_SHORT).show();
	        intent = new Intent(this, AltaContacto.class);
		    startActivity(intent);
	           return true;
	        case R.id.btInfo:
	           Toast.makeText(getApplicationContext(), "Has pulsado la opción Acerca de", Toast.LENGTH_SHORT).show();
	           intent = new Intent(this, AcercaDe.class);
			    startActivity(intent);
	           return true;
	    /*    case R.id.btSalir:
		           Toast.makeText(getApplicationContext(), "Has pulsado la opción Salir", Toast.LENGTH_SHORT).show();
		           salir();
		           return true;*/
	        default:
	           return super.onOptionsItemSelected(item);
	    }
	}
	
	/*
	 * 	no se usa
	 */
	/*
	private void salir() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
	     builder.setMessage("¿Confirma que quiere salir de la app ?")
	    .setTitle("Confirmación")
	    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
	           public void onClick(DialogInterface dialog, int id) {
	                Log.i("Dialogos", "Confirmacion Aceptada.");
	                   //AgendaGlobal.getInstance().miAgenda.remove(PosicionActual);
	                   //lvContactos.setAdapter(adapter);
	                	AgendaGlobal.getInstance().miAgenda.clear();
	                    MainActivity.this.finish();
	                	//finish();
	               }
	           })
	    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                    Log.i("Dialogos", "Confirmacion Cancelada.");
	                    dialog.cancel();
	               }
	           });
	
	   AlertDialog alert=builder.create();
	   alert.show();
		// TODO Auto-generated method stub
		
	}
	*/



	/**
	 * Métotodo que rellena el ArrayList con los contactos que queremos mostrar en el ListView personalizado.
	 
private void rellenarArrayList() {
			
			
			new ServicioInicial(MainActivity.this).execute();
			
	}
*/
	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position,
			long Id) {
		// Al hacer click sobre uno de los items del ListView mostramos los
		// datos en los TextView.
		//tvNombre.setText(AgendaGlobal.getInstance().miAgenda.get(position).getNombre());
		//tvNumCelda.setText(String.valueOf(position));

	}
	@Override
	public boolean onItemLongClick(AdapterView<?> adapter, View view, int position,
			long Id) {
		// Al hacer click sobre uno de los items del ListView mostramos los
		// datos en los TextView.
		//tvNombre.setText("Looong en item numero" + position);
		PosicionActual=position;
		return false;
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	  //  if(v.getId()==R.id.lblEtiqueta)
	    //    getMenuInflater().inflate(R.menu.menucontextual, menu);
	    if(v.getId()==R.id.lvItems)
	        getMenuInflater().inflate(R.menu.menucontextualitem, menu);
	}
	

	public boolean onContextItemSelected(MenuItem item) {
	    
		switch (item.getItemId()) {
	    case R.id.opcionEditar:
	    	Intent intent = new Intent(MainActivity.this, MostrarContacto.class);
	    	String mail= AgendaGlobal.getInstance().miAgenda.get(PosicionActual).getMail();
		    intent.putExtra(EXTRA_MAIL,mail);
		    intent.putExtra(EXTRA_POSITION,PosicionActual);
	        startActivity(intent);
	        return true;
	    
	    case R.id.opcion2Lista:
	        if (AgendaGlobal.getInstance().miAgenda.size() > 1) EliminarContacto(PosicionActual);
	        else Toast.makeText(getApplicationContext(), "No se puede dejar la lista vacia", Toast.LENGTH_SHORT).show();
	        return true;
	    
	    default:
	        return super.onContextItemSelected(item);
	    }
	}



	private void EliminarContacto(int posicionActual) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
	     builder.setMessage("¿Confirma eliminar el contacto elegido ?" +" "+ AgendaGlobal.getInstance().miAgenda.get(PosicionActual).getNombre())
	    .setTitle("Confirmación")
	    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()  {
	           public void onClick(DialogInterface dialog, int id) {
	                Log.i("Dialogos", "Confirmacion Aceptada.");
	               // AgendaGlobal.getInstance().miAgenda.remove(PosicionActual);
	               // String mail=AgendaGlobal.getInstance().miAgenda.get(PosicionActual).getMail();
	               // mail="\""+mail+"\"";
	               // Log.i("Dialogos", mail);
	                Toast.makeText(getApplicationContext(),"Elemento a eliminar: " + AgendaGlobal.getInstance().miAgenda.get(PosicionActual).getMail(), Toast.LENGTH_LONG).show();
	                BaseDatosGlobal.getInstance(MainActivity.this).agendaBaseDatos.borrarContacto(AgendaGlobal.getInstance().miAgenda.get(PosicionActual).getMail());//mail
	                AgendaGlobal.getInstance().miAgenda.remove(PosicionActual);
	                lvContactos.setAdapter(adapter);
	                   // ControlLogin.this.finish();e
	               }
	           })
	    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                    Log.i("Dialogos", "Confirmacion Cancelada.");
	                    dialog.cancel();
	               }
	           });
	
	   AlertDialog alert=builder.create();
	   alert.show();
	   
	}


 public void onAcabeInicializacion() {
		//

		    Toast.makeText(this,"The result is ", Toast.LENGTH_LONG).show();
		}


/*
@Override
public void onAcabeInicializacion(Integer result) {
	// TODO Auto-generated method stub

	Toast.makeText(this,"Numero total de contactos es: "+ AgendaGlobal.getInstance().miAgenda.size(), Toast.LENGTH_LONG).show();
	
	lvContactos.setAdapter(adapter);
}
*/
@Override
public void onDestroy() {
  super.onStop();
 // AgendaGlobal.getInstance().miAgenda.clear();  //Clear ArrayList
}

public void onStart() {
	super.onStart();
	AgendaGlobal.getInstance().miAgenda = BaseDatosGlobal.getInstance(this).agendaBaseDatos.recuperarTodosContactos();
	lvContactos.setAdapter(adapter);
}

public void onRestart() {
	super.onRestart();
	AgendaGlobal.getInstance().miAgenda = BaseDatosGlobal.getInstance(this).agendaBaseDatos.recuperarTodosContactos();
	lvContactos.setAdapter(adapter);
}
}
