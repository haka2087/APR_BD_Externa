package com.example.windows10.proyectootto2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText numMedidor;
    public Button query_button;
    public TextView result_name;
    public Button btnEnviar;
    public TextView result_dos;
    public TextView result_tres;
    public TextView result_cuatro;
    public Button btn_verifica;
    public TextView result_cinco;
    public Button exportar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numMedidor = findViewById(R.id.numMedidor);
        query_button = findViewById(R.id.query_button);
        result_name = findViewById(R.id.result);
        btnEnviar = findViewById(R.id.btnEnviar);
        result_dos = findViewById(R.id.result2);
        result_tres = findViewById(R.id.result3);
        result_cuatro = findViewById(R.id.result4);
        btn_verifica= findViewById(R.id.btnvVerifica);
        exportar= findViewById(R.id.exportar);




        query_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();

                String n = numMedidor.getText().toString();
                String medidor = databaseAccess.getMedidor(n);
                result_name.setText(medidor);
                String nombre=databaseAccess.getNombre(n);
                result_dos.setText(nombre);

                String apellido=databaseAccess.getApellido(n);
                result_tres.setText(apellido);

                String rut=databaseAccess.getRut(n);
                result_cuatro.setText(rut);

                databaseAccess.close();

            }

        });


    }

    public void Enviar (View view) {
        switch (view.getId()) {

            case R.id.btnEnviar:
                Intent miIntent = new Intent(MainActivity.this, RegistrarLectura.class);

                Bundle miBundle = new Bundle();
                miBundle.putString("nombre", result_name.getText().toString());

                miIntent.putExtras(miBundle);

                startActivity(miIntent);
                break;
        }
    }

    public void consultar (View view) {
        Intent consultar = new Intent(this, ConsulraLecturas.class);
        startActivity(consultar);


    }

    public void Exportar (View view){
        Intent Exportar = new Intent(this, Exportar.class);
        startActivity(Exportar);
    }

    public void Importar (View view){
        Intent Exportar = new Intent(this, Importar.class);
        startActivity(Exportar);
    }
}