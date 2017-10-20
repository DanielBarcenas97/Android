package inter.proteco.com.intents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Segunda extends AppCompatActivity {

    private TextView saludo;
    private Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda2);
        //Flecha Hacia atras

        getSupportActionBar().setDisplayShowCustomEnabled(true);

        //Referecnaimos a los atributos

        saludo = (TextView) findViewById(R.id.saludo);
        btnShare =  (Button) findViewById(R.id.btnCpt);

        //Cachar el Bundle

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.getString("saludo")!=null){
            String saludos= bundle.getString("saludo");
            Toast.makeText(this, saludos, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Esta Vacio", Toast.LENGTH_SHORT).show();
        }
    }
}
