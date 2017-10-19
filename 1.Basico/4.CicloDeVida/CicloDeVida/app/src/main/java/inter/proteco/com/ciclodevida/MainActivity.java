package inter.proteco.com.ciclodevida;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"On Create",Toast.LENGTH_SHORT).show();

    }

    @Override                     //Metodos de Main Activity  ///metodos de Callback
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(this,"On Restart",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){   //Quitar referencia de las bases de datos
        super.onResume();
        Toast.makeText(this,"On Resume",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(this,"On Stop",Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(this,"On Pause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(this,"On Destroy",Toast.LENGTH_SHORT).show();
    }




}
