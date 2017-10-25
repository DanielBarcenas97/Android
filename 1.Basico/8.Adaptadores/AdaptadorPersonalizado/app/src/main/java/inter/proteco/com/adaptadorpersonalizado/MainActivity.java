package inter.proteco.com.adaptadorpersonalizado;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Disco> discos;
    ListView lista;

    //Array adapter, Single Adapter y Apuntador Adapter




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        discos = new ArrayList<>();

        discos.add(new Disco("Little Dark Age","MGMT",R.drawable.mgmt,99.90));

        discos.add(new Disco("Houdini","Foster The People",R.drawable.foster,65.90));

        discos.add(new Disco("The Man","The Killers",R.drawable.killers,10.90));

        lista = (ListView) findViewById(R.id.listaDiscos);

        lista.setAdapter(new DiscoAdaptador());

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


                Toast.makeText(MainActivity.this, ""+i, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


    class DiscoAdaptador extends ArrayAdapter<Disco>{

        DiscoAdaptador(){
            super(MainActivity.this,R.layout.discos_row,discos);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row =  getLayoutInflater().inflate(R.layout.discos_row,parent,false);
            ImageView portada = (ImageView) row.findViewById(R.id.caratula);
            TextView titulo = (TextView) row.findViewById(R.id.tituloDisco);
            TextView artista = (TextView) row.findViewById(R.id.artista);
            TextView precio = (TextView) row.findViewById(R.id.precio);

            Disco discoActual = discos.get(position);
            portada.setImageResource(discoActual.getPortada());
            titulo.setText(discoActual.getDisco());
            artista.setText(discoActual.getArtista());
            precio.setText("$"+discoActual.getPrecio());
            return row;

        }
    }


}
