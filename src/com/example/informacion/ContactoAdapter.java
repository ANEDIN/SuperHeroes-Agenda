package com.example.informacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.informacion.R;

import java.util.ArrayList;


/**
 * Clase Adapter que personaliza las vistas de cada ítem a mostrar en el ListView.
 * 
 * @author Eduardo Gutierrez
 * 
 */
public class ContactoAdapter extends ArrayAdapter<ContactoAgenda> {

	private Context context;
	private ArrayList<ContactoAgenda> datos;

	/**
	 * Constructor del Adapter.
	 * 
	 * @param context
	 *            context de la Activity que hace uso del Adapter.
	 * @param datos
	 *            Datos que se pasan al ListView ( todo el array list). Holder son los datos a visualizar realmente de cada elementeo
	 *            del array list. obtengo getview de cada elemento del array list.
	 */
	public ContactoAdapter(Context context, ArrayList<ContactoAgenda> datos) {
		super(context, R.layout.listview_item, datos);
		// Guardamos los par�metros en variables de clase.
		this.context = context;
		this.datos = datos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View item = convertView; //convertview es a la vista a la que convertimos en este caso en la definida en layout listview_item
		ContactoHolder holder;

		if (item == null) {
			item = LayoutInflater.from(context).inflate(R.layout.listview_item,
					null);
			// Inicializamos el holder y guardamos las referencias a los
			// controles.
			holder = new ContactoHolder();
			holder.imgContacto = (ImageView) item.findViewById(R.id.imagenContacto);
			holder.tvMail = (TextView) item.findViewById(R.id.tvMail);
			holder.tvNombre = (TextView) item.findViewById(R.id.tvNombre);

			// Almacenamos el holder en el Tag de la vista.
			item.setTag(holder);
		}
		// Recuperamos el holder del Tag de la vista.
		holder = (ContactoHolder) item.getTag();
		
		// A partir del holder, asignamos los valores que queramos a los
		// controles.
		// Le asignamos una foto al ImegeView.
		
	   holder.imgContacto.setImageResource(datos.get(position).getDrawableImageId());

		// Asignamos los textos a los TextView.
		holder.tvNombre.setText(datos.get(position).getNombre());
		//holder.tvField.setText(String.valueOf(position));
		holder.tvMail.setText(datos.get(position).getDireccion());

		// Devolvemos la vista para que se muestre en el ListView.
		return item;
	}
}

