package com.example.windows10.proyectootto2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegistrarLectura extends AppCompatActivity {

    TextView resulta;
    EditText lectura;
    Button btnRegistrar;
    TextView viewRegistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_lectura);

        resulta = findViewById(R.id.resulti);
        lectura = findViewById(R.id.txt_lectura);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        viewRegistro = findViewById(R.id.viewRegistro);


        Bundle miBundle = this.getIntent().getExtras();

        if (miBundle != null) {

            String nombre = miBundle.getString("nombre");
            resulta.setText(nombre);
        }

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();

                SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy", Locale.getDefault());
                Date date = new Date();



                String lect = lectura.getText().toString();
                String num = resulta.getText().toString();
                String fecha = dateFormat.format(date);
                String lectget = databaseAccess.getRegistro(lect,num,fecha);
                viewRegistro.setText(lectget);

                Toast.makeText(getApplicationContext(), fecha, Toast.LENGTH_LONG).show();

            }
        });
    }

}
