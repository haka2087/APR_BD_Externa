package com.example.windows10.App_APR;

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
import com.example.windows10.App_APR.R;

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


                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();

                int i = 1;
                while (i <= 3) {
                    String lectura = databaseAccess.getConExportar(i);
                    String num = Integer.toString(i);

                    //Toast.makeText(getApplicationContext(), lectura, Toast.LENGTH_LONG).show();


                    ver.setText(lectura);
                    //databaseAccess.close();

                    conexionphp(lectura, num);
                    i = i + 1;
                }


            }

        });

    }

    private void conexionphp(final String var1, final String var2){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://apragua.000webhostapp.com/php.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), var1, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),var2, Toast.LENGTH_LONG).show();


                Toast.makeText(getApplicationContext(), "conexion exitosa", Toast.LENGTH_LONG).show();

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
                parametros.put("var",var1);
                parametros.put("var2",var2);

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
