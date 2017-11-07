package inter.proteco.com.appproteco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
                                                      //implementar metodos de onclick
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnIngresar;
    EditText txtUsu,txtPas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsu=(EditText)findViewById(R.id.txtusu);
        txtPas=(EditText)findViewById(R.id.txtpas);
        btnIngresar=(Button)findViewById(R.id.btn_Ingresar);

        btnIngresar.setOnClickListener(this); //Repetir implementacion

        //Con This Enviamos el parametro que recibira el metodo onClick

    }

    //Llamar metodos de enviarDatosGET y  obtDatosJSON
    @Override
    public void onClick(View v) {  //cuando demos click en el boton wnviaremos nuestro pas y usu

        Thread tr=new Thread(){  //implementar un Hilo
            @Override
            public void run() { //metodo RUN -> permite ejecutar el codigo en el hilo
                                                       //Con metodo GET Enviamos nuestros datos como cadena
                final String resultado=enviarDatosGET(txtUsu.getText().toString(), txtPas.getText().toString());
                 //Enviaremos nuestro usuario y password

                //Si ya validamos que existe entonces pasaremos a otra ventana y para eso  ocuparemos el metodo runOnUiThread(new Runnable()
                runOnUiThread(new Runnable() { //permite trbajar con la interfaz grafica desde un hilo
                    @Override
                    public void run() {
                        int r = obtDatosJSON(resultado);  //checar si el JSON tiene datos !
                        Toast.makeText(getApplicationContext(), r+"", Toast.LENGTH_LONG).show();

                        if (r > 0) {  //existe el usuario y contraseña y lo pasamos a la sig ventana
                            Intent i = new Intent(getApplicationContext(), registroNotas.class);
                            i.putExtra("cod", txtUsu.getText().toString()); //el cod lo ocuparemos en la nueva ventana
                            startActivity(i); //lanzamos la nueva actividad
                        } else {
                            //si no existe mensaje de error
                            Toast.makeText(getApplicationContext(), "Usuario o Pas Incorrectos", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        };
        tr.start(); //larzar el Hilo !!
    }

    //Metodo para Enviar Datos //Le enviamos el usuario y la contraseña

    public String enviarDatosGET(String usu, String pas){

        URL url=null;  // Var tipo URL -> permite almacenar al url al que nos vamos a ir // Sera ocupado para obtener el URL de nuestro localhost
        String linea="";
        int respuesta = 0;
        StringBuilder resul=null;

        try{                                                           //pas y usu entran como parametro
            url=new URL("http://192.168.64.2/Web/valida.php?usu="+usu+"&pas="+pas);  //Llenamos nuestra var URL
                                ///Cambiar Direccion IP             //mandamos usuario y constraseña para ver si existe en la BD
            HttpURLConnection conection=(HttpURLConnection)url.openConnection(); //abir coneccion para enviar la DATA
            //Clase HTTPURLConecction                                ///A la variable le aplicamos un open conecction
            respuesta=conection.getResponseCode();// La respuesta es guardada en respuesta

            //Si es exitosa la conecccion nos regresa un numero 200
            //Si no otra cosa

            resul=new StringBuilder();  //Si hay una respuesta entonces estamos consumiendo ese JSon

            if(respuesta==HttpURLConnection.HTTP_OK){ //Si tenemos respuesta ==  200 entonces
                InputStream in=new BufferedInputStream(conection.getInputStream()); //tomamos la respuesta y la traemos
                BufferedReader reader=new BufferedReader(new InputStreamReader(in)); //con buffered Reader la leemos

                while((linea=reader.readLine())!=null){ //llenamos result con los datos que hemos traido
                    resul.append(linea); //retorna una linea o puede que mas
                }
            }

        }catch (Exception e){}

        return resul.toString(); //retornamos Result como una cadena porque nuestro metodo es de tipo String
                                 //estamos retornando el JSON

    }
     //Como parametro recibe la respuesta del metodo anterior

    //Metodo para saber si nuestro JSON esta Vacio o Trae DAtos
    public int obtDatosJSON(String response){   //tiene que regresar un Cero o  un uno
        int res=0;
        try{
            JSONArray json=new JSONArray(response);  //objeto JSON ARRAY que tendra la respuesta del metodo anteiror
                      ///contamos si el JSON tiene Datos
            if(json.length()>0){  // si su long es mayor a CERO la respuesta es uno porque tengo un dato
                res=1;
            }
            //si no entro entonces regresa un 0 que quiere decir que el password es cero
        }catch(Exception e){}
        return res; //regresa 1 si Existe y 0 si el usu y/o pas son incorrectos
    }

}