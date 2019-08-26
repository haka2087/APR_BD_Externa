package com.example.windows10.proyectootto2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Exportar extends AppCompatActivity {

    Button btnExportar;
    TextView ver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exportar);

        btnExportar= findViewById(R.id.btnActualizar);
        ver= findViewById(R.id.textV);

        btnExportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //consulUltId();
                verificarExportacion();


            }

        });


    }
    public String consulUltId() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://agua2.000webhostapp.com/consultId.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {



                Toast.makeText(getApplicationContext(), "Se exportarán '" + response + "'registros desde la Aplicación", Toast.LENGTH_LONG).show();
                //Se envia a la funcion el numero del utlimo registro.
                ExtraerTabla(response);
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
    public  String ExtraerTabla(String id){

        //Toast.makeText(getApplicationContext(), "Si, es verdad son '" + id + "'registros", Toast.LENGTH_LONG).show();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        int ide = Integer.parseInt(id);
        int num = 1;

       while (num <= ide){

            String medidor = databaseAccess.getConExportarMedidor(num);
       // Toast.makeText(getApplicationContext(), medidor, Toast.LENGTH_LONG).show();

        String lectura = databaseAccess.getConExportarLectura(num);
        //Toast.makeText(getApplicationContext(), lectura, Toast.LENGTH_LONG).show();

        //String pago = databaseAccess.getConExportarLectura(num);
            //String monto = databaseAccess.getConExportarLectura(num);

           String nombre = databaseAccess.getConExportarNombre(num);
        //Toast.makeText(getApplicationContext(), nombre, Toast.LENGTH_LONG).show();

            String apellido = databaseAccess.getConExportarApellido(num);
            String direccion = databaseAccess.getConExportarDireccion(num);
            String sector = databaseAccess.getConExportarSector(num);
            String fecha = databaseAccess.getConExportarDate(num);
            String rut = databaseAccess.getConExportarRut(num);
        //Toast.makeText(getApplicationContext(), "Extrayendo registro '"+num+"'", Toast.LENGTH_LONG).show();



        //String numero = Integer.toString(num);

           // Toast.makeText(getApplicationContext(), num, Toast.LENGTH_LONG).show();



            conexionphp(medidor,lectura,nombre,apellido,direccion,sector,fecha,rut);

            num = num +1;

       }

        //Toast.makeText(getApplicationContext(), "Registros cargados con éxito", Toast.LENGTH_LONG).show();

        return null;
    }


    public void conexionphp(final String medidor, final String lectura, final String nombre, final String apellido, final String direccion, final String sector,final String fecha, final String rut){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://agua2.000webhostapp.com/php.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
               Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros=new HashMap<String, String>();
                parametros.put("medidor",medidor);
                parametros.put("lectura",lectura);
                parametros.put("nombre",nombre);
                parametros.put("apellido",apellido);
                parametros.put("direccion",direccion);
                parametros.put("sector",sector);
                parametros.put("fecha",fecha);
                parametros.put("rut",rut);


                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void verificarExportacion() {

        consulUltId();
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://agua2.000webhostapp.com/verficaExportacion.php?", new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();

                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                    databaseAccess.open();

                   /* String fechas = databaseAccess.getConExportarDate(1);

                    String mes = fechas.substring(2,4);

                    Toast.makeText(getApplicationContext(),"FECHA: "+mes, Toast.LENGTH_LONG).show();

                    if(mes.equals(response)){
                        Toast.makeText(getApplicationContext(), "Ya se encuentran registros del mes actual", Toast.LENGTH_LONG).show();

                    }else{
                        //conexionphp(medidor,lectura,nombre,apellido,direccion,sector,fecha,rut);
                        consulUltId();

                    }*/

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

    }


}
