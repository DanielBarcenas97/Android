package inter.proteco.com.agendaprebe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;



import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Contacto> conversaciones;
    private Adaptador adaptador;
    private ListView listView;
    private String nombre;
    private String imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializamos ActiveAndroid
        ActiveAndroid.initialize(this);
        //Colocamos los elementos de la BD en el adaptador
        actualizarAdaptador();
    }

    public void actualizarAdaptador(){
        //Creamos un objeto de nuestro ListView
        listView = (ListView) findViewById(R.id.listView);
        //Seleccionamos los elementos de la tabla. Se nos regresa una lista.
        conversaciones = new Select().from(Contacto.class).execute();
        //Construimos un adaptador a partir lista de conversaciones. le pasamos el xml de cada elemento
        adaptador = new Adaptador(conversaciones, this, R.layout.elemento);
        //Asignamos el adaptador a su respectiva vista.
        listView.setAdapter(adaptador);
    }

    public void crearConversacion(View v) {
        //Creamos una alerta
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Ingresa los datos del usuario.");

        //Hacemos un linear layout vertical dentro de la alerta con dos EditText
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText ETnombre = new EditText(this);
        ETnombre.setHint("Nombre");
        layout.addView(ETnombre);
        final EditText ETUrlImagen = new EditText(this);
        ETUrlImagen.setHint("URL de imagen");
        layout.addView(ETUrlImagen);
        //Le ponemos el linearLayout a la alerta
        alerta.setView(layout);

        alerta.setPositiveButton("AGREGAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                //Si se le da aceptar, creamos un objeto de Conversacion
                Contacto contacto = new Contacto();
                //Le colocamos los datos de los EditText.
                contacto.nombre = ETnombre.getText().toString();
                contacto.imagen = ETUrlImagen.getText().toString();
                //Guardamos los datos en la base de datos
                contacto.save();
                //Actualizamos el adaptador
                actualizarAdaptador();
            }
        });
        alerta.setNegativeButton("CANCELAR", null);
        alerta.show();
    }

    public void borrarTodo(View v){
        SQLiteUtils.execSql("DELETE FROM conversacion");
        actualizarAdaptador();
    }


}