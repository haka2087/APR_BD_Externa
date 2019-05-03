package com.example.windows10.proyectootto2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    public EditText editText5;
    public EditText editText6;
    public Button buttonl;
    public TextView textView1;//Login user
    public TextView textView2;//login Pass


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText5= findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        buttonl = findViewById(R.id.buttonl);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        buttonl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();

                String user = editText5.getText().toString();
                String userget = databaseAccess.getUser(user);
                String pass = editText6.getText().toString();
                String passget = databaseAccess.getPass(pass);
                textView2.setText(passget);
                textView1.setText(userget);
                if (user.equals(userget)&&pass.equals(passget)) {
                    Toast.makeText(getApplicationContext(), "login valido", Toast.LENGTH_LONG).show();
                    Intent ven=new Intent(Login.this,MainActivity.class);
                    startActivity(ven);

                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }





        });
    }
}