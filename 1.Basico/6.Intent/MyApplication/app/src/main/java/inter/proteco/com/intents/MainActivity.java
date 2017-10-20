package inter.proteco.com.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSte;
    private final String GREETER = "Hola Estamos en CU";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Forzar y cargar el icono en el action bar:
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.iconito);
        //Referenciar el botón:
        btnSte = (Button) findViewById(R.id.btnSte);

        btnSte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Definimos el comportamiento del boton:

                Intent intent = new Intent(MainActivity.this,Segunda.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("saludo",GREETER);
                startActivity(intent);
            }
        });


    }
}
