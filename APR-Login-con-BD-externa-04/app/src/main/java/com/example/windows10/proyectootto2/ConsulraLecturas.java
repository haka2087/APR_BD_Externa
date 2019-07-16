package com.example.windows10.proyectootto2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConsulraLecturas extends AppCompatActivity {

    public Button btn_selec;
    public TextView ed_lectura;
    public TextView ed_Medidor;
    public EditText ed_Text;


  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulra_lecturas);


        btn_selec = findViewById(R.id.btnSelec);
        ed_lectura = findViewById(R.id.txtLectura);
        ed_Medidor = findViewById(R.id.txtMedidor);
        ed_Text = findViewById(R.id.editText);

        btn_selec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();

                String a = ed_Text.getText().toString();
                String lectura = databaseAccess.getConsulta(a);
                ed_lectura.setText(lectura);
                databaseAccess.close();

            }
        });
    }



}
