package inter.proteco.com.app1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { //Barra de Arriba


    EditText total, personas;

    Button calcular;

    TextView resultado;

    double dinero,cuantoMeToca;

    int amigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {  //primer metodo que se ejecuta
        //Bundle objeto que sirve para manipular
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //R archivo autogenerado  //Set content View hace que funcione la app

        total = (EditText) findViewById(R.id.et_cuenta);  //Regresa un View perop queremos meterlo en un EditText

        personas = (EditText) findViewById(R.id.et_personas);

        calcular = (Button) findViewById(R.id.btn_calcular);

        resultado = (TextView) findViewById(R.id.tv_resultado);

        calcular.setOnClickListener(new View.OnClickListener() {  //new O
            @Override
            public void onClick(View view) {

                if(personas.getText().length() > 0 && total.getText().length()>0){
                    amigos = Integer.parseInt (personas.getText().toString());
                    dinero = Double.parseDouble(total.getText().toString());

                    cuantoMeToca = (dinero/amigos)*1.15;

                    resultado.setText("Te Toca pagar $" + cuantoMeToca);

                }
                else{

                    Toast.makeText(MainActivity.this, "Tienes que ingresar ambos datos", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}


