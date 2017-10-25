package inter.proteco.com.adaptadores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView seleccion;
    ListView lista;
    String[] nombres={"Edgar","Coral","Pedro","Rodrigo","Angel","Orlando","David","Bere","Nicky","Oscar","Erick","Angel","Pedro"};

    ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ArrayAdapter (Contexto, LAyout y Datos)
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombres);
        lista = (ListView) findViewById(R.id.lista);

        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                seleccion.setText(nombres[i]);
            }
        });
    }
}



