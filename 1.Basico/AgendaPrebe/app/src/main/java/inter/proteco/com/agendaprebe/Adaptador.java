package inter.proteco.com.agendaprebe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Root on 26/06/2017.
 * Esta es la clase "puente" entre cada uno de los elementos de la clase Conversacion y sus
 * respectivos objetos y vistas del layout.
 */

public class Adaptador extends BaseAdapter {
    private List<Contacto> conversaciones;
    private Context contexto;
    private int layout;

    //Clase que describe los widgets que contiene cada elemento de la lista.
    static class ViewHolder {
        private TextView nombreTV;
        private ImageView imagenIV;
    }

    //Constructor de la clase Adaptador.
    public Adaptador(List<Contacto> conversaciones, Context contexto, int layout) {
        this.conversaciones = conversaciones;
        this.contexto = contexto;
        this.layout = layout;
    }

    @Override //Método que permite obtener el objeto asociado a una posición.
    public Object getItem(int position) {
        return this.conversaciones.get(position);
    }

    @Override //Método que permite obtener el tamaño de la lista.
    public int getCount() {
        return this.conversaciones.size();
    }

    @Override //Método que permite obtener el ID asociado a una posición (no necesario).
    public long getItemId(int position) {
        return 0;
    }

    @Override //Método que permite obtener la vista y datos asociados a un elemento de la lista.
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder elemento; //Instanciamos ViewHolder. Esto representa nuestro xml de elemento

        if (convertView == null) { //Si la vista de un elemento no contiene nada, la inflamos.
            LayoutInflater layoutInflater = LayoutInflater.from(this.contexto);
            convertView = layoutInflater.inflate(this.layout, null);

            elemento = new ViewHolder();
            //Instanciamos ViewHolder e inflamos cada una de las vistas asociadas al elemento.
            elemento.nombreTV = (TextView) convertView.findViewById(R.id.nombre);
            elemento.imagenIV = (ImageView) convertView.findViewById(R.id.imagen);
            //Le asignamos el ViewHolder a la vista actual (convertView) de la lista.
            convertView.setTag(elemento);
        }
        else //Si ya existe el convertView, sólo la mandamos llamar.
            elemento = (ViewHolder) convertView.getTag();

        Contacto contactoActual = conversaciones.get(position);
        //Obtenemos el elemento de la lista actual y le asignamos los datos a la vista correspondiente.
        elemento.nombreTV.setText(contactoActual.getNombre());
        Picasso.with(contexto)		//Inicializo Picasso,
                .load(contactoActual.getImagen())	//cargo una imagen,
                .fit()
                .into(elemento.imagenIV);	//y la coloco en la vista que corresponde.

        return convertView; //Regresamos la vista completa con todos los datos asociados.
    }
}