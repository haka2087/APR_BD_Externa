package com.example.windows10.proyectootto2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Importar extends AppCompatActivity {

    Button btnImportar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importar);

        btnImportar = findViewById(R.id.btnImportar);

        btnImportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //consuta el id del ultimo registro de la tabal ususario.
                consulUltId();

            }

        });

    }
    //funcion para consultar el id del ultimo registros de la tabla usuario
    private String consulUltId() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://agua2.000webhostapp.com/consultId.php", new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    //Se envia a la funcion el numero del utlimo registro.
                    conexionUnophp(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        return null;
    }
    //Funcion que consulta los registros de la tabla usuario.
    private void conexionUnophp(String id) {

        //lo primero es vaciar la tabla de SQLite con esta funcio:
        borrartabla();
        Toast.makeText(getApplicationContext(), "Se importarán '"+id+"' registros desde el Servidor", Toast.LENGTH_LONG).show();

        int ide = Integer.parseInt(id);
        Integer num = 1;

        //While permite parar el ciclo hasta el ultimo id de la tabla.
        while (num <= ide ){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://agua2.000webhostapp.com/importar.php?var='"+num+"'", new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                    String[] respuesta = response.split(",");

                        String medidor =respuesta[0];
                        String nombre =respuesta[1];
                        String apellido =respuesta[2];
                        String rut =respuesta[3];
                        String direccion =respuesta[4];
                        String id = respuesta[5];
                        //Se envian los datos consultados a la funcion crear tabla en SQLite
                        crearTabla(medidor, nombre,apellido,rut,direccion,id);


                   /* Toast.makeText(getApplicationContext(), respuesta[0], Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), respuesta[1], Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), respuesta[2], Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), respuesta[3], Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), respuesta[4], Toast.LENGTH_LONG).show();*/




                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

            num = num + 1;
        }

        Toast.makeText(getApplicationContext(), "Datos importados correctamente", Toast.LENGTH_LONG).show();

    }

    private void crearTabla(String medidor, String nombre, String apellido,String rut,String direccion, String id){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        String insertar = databaseAccess.getInsetarTabla(medidor,nombre,apellido,rut,direccion,id);



    }
    private void borrartabla(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        //vaciar tabla de registros
        String borrar = databaseAccess.getVaciarTabla("registro");

        Toast.makeText(getApplicationContext(), "tabla borrada", Toast.LENGTH_LONG).show();


        databaseAccess.close();


    }

}

