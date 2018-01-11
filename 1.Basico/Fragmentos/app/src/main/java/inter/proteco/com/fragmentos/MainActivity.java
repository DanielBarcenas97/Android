package inter.proteco.com.fragmentos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity  implements DataFragment.DataListener{

    public boolean isMultipanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMultipanel();
    }

    public void setMultipanel(){
        isMultipanel = (getSupportFragmentManager().findFragmentById(R.id.detailsFragment) != null);
    }


    @Override
    public void sendData(String text) {
        if(isMultipanel){
            DetailsFragment df = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
            df.renderText(text

        } else {
            Intent intent = new Intent(this,DetailsActivity);
            intent.putExtra("text",text);
            startActivity(intent);
        }

    }
}
