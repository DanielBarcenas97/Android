package inter.proteco.com.appproteco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class registroNotas extends AppCompatActivity {

    TextView txtAlumno;
    Spinner spCiclos;
    ImageView btnSalir,btnEditar;
    ListView lstCursos;

    String recuperado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_notas);

        //recuperando codigo
        //Recupera alacena el valor que viene desde el intent (pantalla logeo)
        final Bundle recupera=getIntent().getExtras();
        if(recupera!=null){ //Hemos encontrado un dato
            recuperado=recupera.getString("cod"); //recuperado es el codigo del alumno
        }
        //Enlazar txtAlumno
        txtAlumno=(TextView)findViewById(R.id.txtAlumnoLogeado);
        //Hilo 2 !!!!
        Thread tr2=new Thread(){
            @Override
            public void run() {        //Metodo RegistroGET
                final String resultado=RegistroGET(recuperado,"","obtenerAlumno");
                final String resultado2=RegistroGET(recuperado,"primero","listarCiclo");
                final String resultado3=RegistroGET(recuperado,"primero","listarAlu");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MostrarAlumno(resultado);
                        //Metodo para cargar el Spinner
                        cargarSpiner(ArregloSpiner(resultado2));
                        cargarListView(ArregloLista(resultado3));
                    }
                });
            }
        };
        tr2.start();

        //Activar el Spinner

        spCiclos=(Spinner)findViewById(R.id.spCiclos);
        spCiclos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                Thread tr = new Thread() {
                    @Override
                    public void run() {
                        //Cambiar el ciclo de acuerdo a la posicion seleccionada
                        final String resultado4 = RegistroGET(recuperado, spCiclos.getItemAtPosition(pos) + "", "listarAlu");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cargarListView(ArregloLista(resultado4));
                            }
                        });
                    }
                };
                tr.start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       btnEditar=(ImageView)findViewById(R.id.btnActualizar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Clase a la que nos vamos !!!!
                Intent i=new Intent(getApplicationContext(),Configuracion.class);
                i.putExtra("cod",recuperado);
                startActivity(i);
            }
        });

    }
    ///Nuevo Metodo! Checar los PHP.  //Codigo //Ciclo Escolar  //Accion para los IF
    //Enviamos por metodo GET
    //Spinner llena los ciclos
    public String RegistroGET(String cod,String ciclo,String accion){

        URL url=null;    //declaramos nuestras variables
        String linea="";
        int respuesta=0;
        StringBuilder resul=null;
        //Conectarse a la base de Datos mediante PHP
        //Nota Checar los PHP ¿Que es lo que no esta pidiendo cada uno ?
        //Hacer practicamente lo mismo que en el ActivityMain
        try{
            if(accion.equals("obtenerAlumno")){
                //Recuperar alumno y mostrar su nombre
                //recuerda revizar tu php y tu htdocs
                url = new URL("http://192.168.64.2/Web/listaralucod.php?alu="+cod); //solo se pide el cod
            }else if(accion.equals("listarCiclo")){
                //Listar ciclos
                url = new URL("http://192.168.64.2/Web/listarciclos.php?alu="+cod);
            }else if(accion.equals("listarAlu")){
                //listar Alumnos!
                url = new URL("http://192.168.64.2/Web/listar.php?alu="+cod+"&ciclo="+ciclo);
            }
            //Clase HTTPUrlConection para establecer conexion
            HttpURLConnection conection=(HttpURLConnection)url.openConnection();
            //Checar si tenemos respuesta
            respuesta=conection.getResponseCode();
            //Como retorna un JSON
            resul=new StringBuilder();
            //Si la respuesta es que existe coneccion
            if(respuesta==HttpURLConnection.HTTP_OK){
                //Traer los datos
                InputStream in=new BufferedInputStream(conection.getInputStream());
                //Leer los datos que se trajeron
                BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                //Mientras tengamos una linea que no sea null entonces lo paso a linea y lleno resul
                while((linea=reader.readLine())!=null){
                    resul.append(linea);
                }
            }

        }catch(Exception e){}
        //Retorno el JSON
        return resul.toString();
    }

    //METODO QUE PERMITE MOSTRAR EL NOMBRE DEL ALUMNO LOGEADO
    //RegistroGET nos da la respuesta un json que estra como parametro a mostrarAlumno
    //RegistroGET consume un JSON que descomponemos y solo tomamos el nombreALU y luego lo mostramos
    public void MostrarAlumno(String response){
        try{
            JSONArray json=new JSONArray(response);
            for(int i=0;i<json.length();i++){ //recorremos el JSON
                //                            //lo que tomamos del JSON    //Campo a Tomar
                txtAlumno.setText("Alumno: "+json.getJSONObject(i).getString("nombreAlu"));
            }
        }catch(Exception e){}
    }

    //METODO QUE PERMITE CREAR UN ARRAYLIST CON LOS CICLOS
    public ArrayList<String> ArregloSpiner(String response){
        ArrayList<String> listado=new ArrayList<String>();
        try{
            JSONArray json=new JSONArray(response); //JSON
            String texto="";
            for(int i=0; i<json.length();i++){  //Descomponer el JSON
                //Posicion i       //Campo a traer
                texto=json.getJSONObject(i).getString("descripcion");
                listado.add(texto);
            }
        }catch(Exception e){}
        return listado;  //Regresamos el listado de descirpcion
    }

    //METODO QUE PERMITE CARGAR EL SPINNER
    public void cargarSpiner(ArrayList<String> datos){ //ingresa un arrayList                              //Lista simple
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);

        spCiclos.setAdapter(adaptador);
    }

    //METODO QUE PERMITE CREAR UN ARRAYLIST CON LA LISTA
    public ArrayList<String> ArregloLista(String response){
        ArrayList<String> listado=new ArrayList<String>();
        try{
            JSONArray json=new JSONArray(response);
            String texto="";
            for(int i=0; i<json.length();i++){
                texto=json.getJSONObject(i).getString("codcurso")+" - "+
                        json.getJSONObject(i).getString("Descripcion")+" -> "+
                        json.getJSONObject(i).getString("promedio");
                listado.add(texto);
            }
        }catch(Exception e){}
        return listado;
    }

    //METODO QUE PERMITE CARGAR LISTVIEW
    public void cargarListView(ArrayList<String> datos){
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);

        lstCursos=(ListView)findViewById(R.id.lstCursos);
        lstCursos.setAdapter(adaptador);
    }

}