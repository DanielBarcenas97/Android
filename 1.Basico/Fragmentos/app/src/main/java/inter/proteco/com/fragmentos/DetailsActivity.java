package inter.proteco.com.fragmentos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    //dispositivos pequeños


    private  String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(getIntent().getExtras() != null){
            text = getIntent().getStringExtra("text");

        }

        DetailsFragment df = (DetailsFragment)getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        df.renderText(text);


    }
}
