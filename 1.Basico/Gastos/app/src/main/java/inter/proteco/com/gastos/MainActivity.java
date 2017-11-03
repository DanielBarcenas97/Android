package inter.proteco.com.gastos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    ArrayAdapter <Gasto> adaptador;
    ArrayList <Gasto> gastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gastos = new ArrayList<>();

        gastos.add(new Gasto("Chilaquiles",30));
        gastos.add(new Gasto("Galletas",30));
        gastos.add(new Gasto("Gomitas",30));

        lista=(ListView) findViewById(R.id.lista_adaptador);
        adaptador = new GastoAdaptador();
        lista.setAdapter(adaptador);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intento = new Intent(MainActivity.this, EditarActivity.class);
                intento.putExtra("gasto",gastos.get(i));
                gastos.remove(i);
                startActivityForResult(intento,69);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 69){
            if(resultCode == RESULT_OK){
                Gasto gastoNuevo = (Gasto) data.getSerializableExtra("gastoActualizado");
                gastos.add(gastoNuevo);
                adaptador.notifyDataSetChanged();
            }
        }
    }

    class  GastoAdaptador extends ArrayAdapter<Gasto>{
        GastoAdaptador(){
            super(MainActivity.this,R.layout.gasto_row,gastos);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = getLayoutInflater().inflate(R.layout.gasto_row,parent,false);

            Gasto gastoActual = gastos.get(position);

            TextView cantidad = (TextView) row.findViewById(R.id.cantidad);
            TextView descripcion= (TextView) row.findViewById(R.id.descripcion);

            cantidad.setText("$" + gastoActual.getCantidad());
            descripcion.setText(gastoActual.getDescripcion());

            return row;
        }
    }
}
