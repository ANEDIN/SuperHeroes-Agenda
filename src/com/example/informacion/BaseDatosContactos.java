package com.example.informacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class BaseDatosContactos extends SQLiteOpenHelper {
	/* 
	 NOMBRE_BASEDATOS: sera el nombre de nuestro archivo de base de datos
	 VERSION_BASEDATOS: la versión de nuestra base de datos
	 TABLA_CONTACTOS: un string que contiene una sentencia SQL para la creación de una tabla llamada contactos. Lo que tenemos en 
					  parentesis son las columnas, un índice INT primario (no es obligatorio pero si muy recomendable para 
					  no tener problemas a la hora de insertar, modificar o borrar alguna fila ya que es un valor único) */
	
	private static final int VERSION_BASEDATOS = 4;
	
	// Nombre de nuestro archivo de base de datos
    private static final String NOMBRE_BASEDATOS = "contactos.db";
    
    // Sentencia SQL para la creación de una tabla
    private static final String TABLA = "contactos";
    private static final String CREAR_TABLA = "CREATE TABLE " + TABLA 
    		+ "(nombre TEXT, direccion TEXT, telefono TEXT,email TEXT PRIMARY KEY UNIQUE NOT NULL,miembrofacebook INT, miembrotwitter INT, miembrogoogle INT, miembrolinkedin INT, sexo INT, tipocontacto TEXT, imagen INT)";

	private static final String TAG ="BaseDatosContactos";
    
    /*	La clase ContactoAgenda que quiero almacenar es:
    private String Nombre;
	private String Direccion;
	private String Telefono;
	private String mail;
	
	//Cambio boolean por int ya que no va bien en las bases de datos, true=1, false=0
	private int miembroFacebook;
	private int miembroTwitter;
	private int miembroGoogle;
	private int miembroLinnkedin;
	private int sexo;
	private String tipoContacto;
	private int drawableImageID; */
    
    public BaseDatosContactos(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREAR_TABLA); //db.execSQL(TABLA_CONTACTOS);
		insertarDatosPrueba(db);
	}
	
	private void insertarDatosPrueba(SQLiteDatabase db)
	{
		//Inicializo la base de datos con algun contacto
		ContactoAgenda contacto;
		
		contacto = new ContactoAgenda();
		contacto.setNombre("Belen");
		contacto.setDireccion("c/ Diego Madrazo");
		contacto.setTelefono("1");
		contacto.setMail("belen@mail.com");
		contacto.setMiembroFacebook(1);
		contacto.setMiembroTwitter(1);
		contacto.setMiembroGoogle(0);
		contacto.setMiembroLinnkedin(0);
		contacto.setSexo(0);
		contacto.setTipoContacto("Familia");
		contacto.setDrawableImageId(R.drawable.hulk);
		insertarContacto(contacto);
		
		contacto = new ContactoAgenda();
		contacto.setNombre("Daniel");
		contacto.setDireccion("c/ Diego Madrazo");
		contacto.setTelefono("2");
		contacto.setMail("daniel@mail.com");
		contacto.setMiembroFacebook(0);
		contacto.setMiembroTwitter(0);
		contacto.setMiembroGoogle(0);
		contacto.setMiembroLinnkedin(0);
		contacto.setSexo(1);
		contacto.setTipoContacto("Laboral");
		contacto.setDrawableImageId(R.drawable.spidey);
		insertarContacto(contacto);
		
		contacto = new ContactoAgenda();
		contacto.setNombre("Eduardo");
		contacto.setDireccion("c/ Segovia");
		contacto.setTelefono("973221100");
		contacto.setMail("eduardo@mail.com");
		contacto.setMiembroFacebook(0);
		contacto.setMiembroTwitter(0);
		contacto.setMiembroGoogle(0);
		contacto.setMiembroLinnkedin(1);
		contacto.setSexo(1);
		contacto.setTipoContacto("Amigo");
		contacto.setDrawableImageId(R.drawable.ironman);
		insertarContacto(contacto);

		contacto = new ContactoAgenda();
		contacto.setNombre("Asuncion");
		contacto.setDireccion("c/ Paseo de Ronda");
		contacto.setTelefono("999888777");
		contacto.setMail("asuncion@mail.com");
		contacto.setMiembroFacebook(1);
		contacto.setMiembroTwitter(1);
		contacto.setMiembroGoogle(1);
		contacto.setMiembroLinnkedin(0);
		contacto.setSexo(0);
		contacto.setTipoContacto("Laboral");
		contacto.setDrawableImageId(R.drawable.thor);
		insertarContacto(contacto);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLA);
		onCreate(db);
	}
	
	/*
	Creamos un método "insertarCONTACTO" y como parámetros los datos que queremos insertar en la tabla (id, nombre, telefono, email ....). 
	Dentro del método creamos una instancia de la clase "SQLiteDatabase" y usamos su método "getWritableDatabase()" para poder 
	escribir en la base de datos. Encapsulamos todo en un if por si acaso la base de datos no existe y ya dentro del if creamos 
	una instancia de la clase "ContentValues" que como su nombre indica es un almacenador de un conjunto de datos. 
	Usamos el metodo "put(key, value)" que nos pide como primer parámetro "key" el nombre donde establecer el valor almacenado 
	y como segundo parámetro el valor que queremos almacenar. Una vez almacenamos los datos insertamos una fila en la 
	tabla usamos el método "insert(table, nullColumnHack, values)" 
*/	
/*	public boolean insertarContacto(String nombre, String direccion, String telefono, String email,int miembrofacebook, int miembrotwitter, int miembrogoogle, int miembrolinkedin, int sexo, String tipocontacto, int imagen){
		SQLiteDatabase db = getWritableDatabase();
		if (db != null) {
			ContentValues valores = new ContentValues();
			valores.put("nombre", nombre);
			valores.put("direccion", direccion);
			valores.put("telefono", telefono);
			valores.put("email", email);
			valores.put("miembrofacebook", miembrofacebook);
			valores.put("miembrotwitter", miembrotwitter);
			valores.put("miembrogoogle", miembrogoogle);
			valores.put("miembrolinkedin", miembrolinkedin);
			valores.put("sexo", sexo);
			valores.put("tipocontacto", tipocontacto);
			valores.put("imagen", imagen);
			//db.insert("contactos", null, valores);
			try {
				db.insertOrThrow(TABLA, null, valores); //TABLA por "contactos"
				return true;
			} catch (SQLiteConstraintException e) {
				Log.d(TAG, "Fallo en la insercion: seguramente la clave ya existe.");
			}
		}
		db.close();   
		return false;
	}*/
	
	
	
	//Creo un insertarcontacto propio pasandole un contacto
	public boolean insertarContacto(ContactoAgenda contacto){
		SQLiteDatabase db = getWritableDatabase();
		if (db != null) {
			ContentValues valores = insertarValores(contacto);
			//db.insert("contactos", null, valores);
			
			try {
		        db.insertOrThrow(TABLA, null, valores); //TABLA por "contactos"
		        return true;
		    } catch (SQLiteConstraintException e) {
		    	Log.d(TAG, "Fallo en la insercion: seguramente la clave ya existe.");
		    }   
		}
		db.close();   
		return false;
	}

	private ContentValues insertarValores(ContactoAgenda contacto) {
		ContentValues valores = new ContentValues();
		valores.put("nombre", contacto.getNombre());
		valores.put("direccion", contacto.getDireccion());
		valores.put("telefono", contacto.getTelefono());
		valores.put("email", contacto.getMail());
		valores.put("miembrofacebook", contacto.isMiembroFacebook());
		valores.put("miembrotwitter", contacto.isMiembroTwitter());
		valores.put("miembrogoogle", contacto.isMiembroGoogle());
		valores.put("miembrolinkedin", contacto.isMiembroLinnkedin());
		valores.put("sexo", contacto.isSexo());
		valores.put("tipocontacto", contacto.getTipoContacto());
		valores.put("imagen", contacto.getDrawableImageId());
		return valores;
	}
	
	/*El método "update(table, values, whereClause, whereArgs)" para actualizar/modificar registros de nuestra tabla. 
	 * Este método nos pide el nombre de la tabla "table", los valores a modificar/actualizar "values" (ContentValues), 
	 * una condición WHERE "whereClause" que nos sirve para indicarle que valor queremos que actualice 
	 * (en este caso cogemos como referencia la id de nuestro contacto) y como ultimo parámetro "whereArgs" podemos pasarle 
	 * los valores nuevos a insertar, en este caso no lo vamos a necesitar por lo tanto lo ponemos a null. 
	 * Para terminar deberemos cerrar siempre nuestra base de datos con el método "close()".
	*/

	
	public boolean modificarContacto(ContactoAgenda contacto){
		try {
		    SQLiteDatabase db = getWritableDatabase();
		    ContentValues valores = insertarValores(contacto);
		    db.update(TABLA, valores, "email=" + contacto.getMail(), null);
		    //db.update("contactos", valores, "_id=" + id, null); ---> db.update("contactos", valores, "_id=" + id, null);
		    db.close();   
		    return true;
		} catch (Exception  e) {
			Log.d(TAG, "Fallo en la modificaci�n.");
		}
		return false;
	}

	/*
	Para borrar registros usaremos el método "delete(table, whereClause, whereArgs)" que nos pide el nombre de la tabla "table", 
	el registro a borrar "whereClause" que tomaremos como referencia su id y como ultimo parámetro "whereArgs" los valores a 
	borrar.
	*/
	
	public boolean borrarContacto(String email) 
	{
		try {
		    SQLiteDatabase db = getWritableDatabase();
		    db.delete(TABLA, "email= '" + email+"'", null);
		    db.close();  
		    return true;
		} catch (Exception  e) {
			Log.d(TAG, "Fallo en la modificaci�n.");
		}
		return false;
	}
	
	public boolean borrarContacto( ContactoAgenda contacto) 
	{
		try {
		    SQLiteDatabase db = getWritableDatabase();
		    db.delete(TABLA, "email= '" + contacto.getMail()+"'", null);
		    db.close();  
		    return true;
		} catch (Exception  e) {
			Log.d(TAG, "Fallo en la modificaci�n.");
		}
		return false;
	}
	
	/*Este método devuelve un objeto Contactos con los datos del contacto (id, nombre, telefono, email). En este caso como 
	 * queremos leer hacemos uso del método "getReadableDatabase()". Creamos una variable "valores_recuperar" con las columnas 
	 * que queremos recuperar, en este caso vamos a recuperar todos los datos de un registro. 
	 * Continuamos creando un "Cursor" que se encarga de devolver el resultado de un registro de la tabla y lo almacena en 
	 * la memoria, le aplicamos el método: 
	 * 		query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)
		Con este método conseguimos leer un registro de la tabla. Como primer parámetro "table" nos pide el nombre de la tabla, 
		"columns" las columnas que queremos recuperar, con "selection" le indicamos el registro a recuperar (en este caso 
		recuperamos con el id), o los registros a recuperar "selectionArgs", "groupBy" para agrupar los registros consultados, 
		"having" es un filtro para incluir los registros en el cursor (este parámetro se usaría con groupBy), "orderBy" para 
		 ordenar las filas y "limit" para limitar el numero de filas consultadas.

		Con el método "moveToFirst()" ponemos el cursor al inicio de los datos almacenados. 
		Lo encapsulamos en un if por si acaso no hay datos almacenados.

		Continuamos creando un objeto "Contactos" para almacenar los datos consultados de un registro, y los vamos recuperando 
		del cursor con métodos get indicando la posición de la columna.

		Para terminar debemos cerrar la base de datos y el cursor.*/
	
	public ContactoAgenda recuperarContacto(String email) {
		SQLiteDatabase db = getReadableDatabase();
	    
		String[] valores_recuperar = {"nombre","direccion","telefono","email", "miembrofacebook","miembrotwitter","miembrogoogle","miembrolinkedin","sexo","tipocontacto","imagen"};	

		Cursor c = db.query(TABLA, valores_recuperar, "email= '" + email+"'", null, null, null, null,null);

	    if(c != null) {
	        c.moveToFirst();
	    }
	    ContactoAgenda contactos = new ContactoAgenda(c.getString(0), c.getString(1), c.getString(2), c.getString(3),c.getInt(4), c.getInt(5), c.getInt(6), c.getInt(7),c.getInt(8), c.getString(9), c.getInt(10));
        db.close();
        c.close();
        return contactos;
	}

	/* 
	 * This is an advice most than anything: when you load a cursor from database ask for the getCount() > 0 before make the loop. Something like this:

Cursor c = db.query("contactos", valores_recuperar, null, null, null, null, null, null);

if (c.getCount() > 0) {
    c.moveToFirst();
    while (cursor.moveToNext()) {
        contactoAgenda contactos = new contactoAgenda(c.getString(0), c.getString(1), c.getString(2), c.getString(3),c.getInt(4), c.getInt(5), c.getInt(6), c.getInt(7),c.getInt(8), c.getString(9), c.getInt(10));
        lista_contactos.add(contactos);
    }
}
c.close();
	 * 
	 * 
	 * Recuperar contacto a partir de clave entero no lo usamos pero puede ser util mas adelante...
	public Contactos recuperarCONTACTO(int id) {
	    SQLiteDatabase db = getReadableDatabase();
	    String[] valores_recuperar = {"_id", "nombre", "telefono", "email"};
	    Cursor c = db.query("contactos", valores_recuperar, "_id=" + id, null, null, null, null, null);
	    if(c != null) {
	        c.moveToFirst();
	    }
	    Contactos contactos = new Contactos(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3));
        db.close();
        c.close();
        return contactos;
	}
*/
	
	/*
	El método que usamos en este caso es muy similar al de leer un registro pero en este caso no especificamos que registro 
	queremos recuperar, por lo tanto ponemos su parámetro a null. A parte creamos una variable "lista_contactos" donde 
	almacenaremos todos los registros de la tabla en objetos contactos. En el bucle do-while usamos el método "moveToNext()" 
	como parámetro que se encargara de pasar al siguiente registro de la tabla y por lo tanto recorrer todos los registros 
	de la tabla.
*/	
	public ArrayList<ContactoAgenda> recuperarTodosContactos() {
	    SQLiteDatabase db = getReadableDatabase();
	    ArrayList<ContactoAgenda> lista_contactos = new ArrayList<ContactoAgenda>();
	    String[] valores_recuperar = {"nombre","direccion","telefono","email","miembrofacebook","miembrotwitter","miembrogoogle","miembrolinkedin","sexo","tipocontacto","imagen"};
	    Cursor c = db.query(TABLA, valores_recuperar, null, null, null, null, null, null);
	    c.moveToFirst();
	    do {
	    	ContactoAgenda contactos = new ContactoAgenda(c.getString(0), c.getString(1), c.getString(2), c.getString(3),c.getInt(4), c.getInt(5), c.getInt(6), c.getInt(7),c.getInt(8), c.getString(9), c.getInt(10));
	    	lista_contactos.add(contactos);
		} while (c.moveToNext());
	   
	   
        db.close();
        c.close();
        return lista_contactos;
	}
	
	/*Para cuando haga falta copiar una base de datos
	 * En ocasiones será necesario incluir a nuestro proyecto un archivo de base de datos ya creado. O podriamos crearlo 
	 * nosotros mismos con cualquiera de estos dos gestores de bases de datos:
			SQLite Database Browser (Gratuita, multiplataforma y de código abierto)
			Valentina estudio (Aplicación multiplataforma recomendada en los comentarios)
		En este caso incluiremos el archivo de base de datos en la carpeta assets de nuestro proyecto. Y por ejemplo crear 
	un método personalizado en nuestra Activity principal y copiar la base de datos de la carpeta assets a la carpeta 
	privada de base de datos de nuestra aplicación.
	 
	 private void copiarBaseDatos() {
	        String ruta = "/data/data/com.example.sqlite/databases/";
	        String archivo = "contactos.db";
	        File archivoDB = new File(ruta + archivo);
	        if (!archivoDB.exists()) {
	        try {
	            InputStream IS = getApplicationContext().getAssets().open(archivo);
	            OutputStream OS = new FileOutputStream(archivoDB);
	            byte[] buffer = new byte[1024];
	            int length = 0;
	            while ((length = IS.read(buffer))>0){
	                OS.write(buffer, 0, length);
	            }
	            OS.flush();
	            OS.close();
	            IS.close();
	        } catch (FileNotFoundException e) {
	            Log.e("ERROR", "Archivo no encontrado, " + e.toString());
	        } catch (IOException e) {
	            Log.e("ERROR", "Error al copiar la Base de Datos, " + e.toString());
	        }*/
}
