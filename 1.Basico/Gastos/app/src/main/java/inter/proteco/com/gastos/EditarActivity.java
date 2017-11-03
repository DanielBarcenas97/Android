package inter.proteco.com.gastos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by proteco on 27/10/17.
 */

public class EditarActivity extends AppCompatActivity {

    EditText cantidad,descripcion;

    Button actualizar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.editar);

        Gasto gastoRecibido = (Gasto)getIntent().getSerializableExtra("gasto");
        cantidad = (EditText) findViewById(R.id.editarCantidad);

        descripcion= (EditText) findViewById(R.id.editarDescripcion);

        cantidad.setText(""+gastoRecibido.getCantidad());
        descripcion.setText(gastoRecibido.getDescripcion());

        actualizar = (Button) findViewById(R.id.actualizar);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();

                 Gasto gActualizado= new Gasto(descripcion.getText().toString(),Integer.parseInt(cantidad.getText().toString()));

                data.putExtra("gastoActualizado",gActualizado);
                setResult(RESULT_OK,data);
                finish();
            }
        });
    }
}
