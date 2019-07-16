package com.example.windows10.proyectootto2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RegistrarLectura extends AppCompatActivity {

    TextView resulta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_lectura);

        resulta= (TextView) findViewById(R.id.resulti);

        Bundle miBundle=this.getIntent().getExtras();

        if (miBundle!=null){

            String nombre=miBundle.getString("nombre");
            resulta.setText(nombre);
        }
    }
}