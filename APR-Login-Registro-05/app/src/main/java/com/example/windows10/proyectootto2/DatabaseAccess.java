package com.example.windows10.proyectootto2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenhelper(context);

    }


    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);

        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();

    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public String getMedidor(String num_medidor) {
        c = db.rawQuery("select distinct usuarios.rut, usuarios.nombre, usuarios.apellido, medidor.num_medidor from usuarios, medidor where medidor.rut=usuarios.rut and medidor.num_medidor='" + num_medidor + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String numMedidor = c.getString(3);
            String nombre = c.getString(1);
            String apellido = c.getString(2);
            String rut = c.getString(0);


            buffer.append(numMedidor);

        }
        return buffer.toString();


    }

    public String getNombre(String num_medidor) {
        c = db.rawQuery("select distinct usuarios.rut, usuarios.nombre, usuarios.apellido, medidor.num_medidor from usuarios, medidor where medidor.rut=usuarios.rut and medidor.num_medidor='" + num_medidor + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String numMedidor = c.getString(3);
            String nombre = c.getString(1);
            String apellido = c.getString(2);
            String rut = c.getString(0);


            buffer.append(nombre);

        }
        return buffer.toString();


    }

    public String getApellido(String num_medidor) {
        c = db.rawQuery("select distinct usuarios.rut, usuarios.nombre, usuarios.apellido, medidor.num_medidor from usuarios, medidor where medidor.rut=usuarios.rut and medidor.num_medidor='" + num_medidor + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String numMedidor = c.getString(3);
            String nombre = c.getString(1);
            String apellido = c.getString(2);
            String rut = c.getString(0);


            buffer.append(apellido);

        }
        return buffer.toString();


    }

    public String getRut(String num_medidor) {
        c = db.rawQuery("select distinct usuarios.rut, usuarios.nombre, usuarios.apellido, medidor.num_medidor from usuarios, medidor where medidor.rut=usuarios.rut and medidor.num_medidor='" + num_medidor + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String numMedidor = c.getString(3);
            String nombre = c.getString(1);
            String apellido = c.getString(2);
            String rut = c.getString(0);


            buffer.append(rut);

        }
        return buffer.toString();


    }

    public String getVerificar(String verificar) {
        c = db.rawQuery("select num_medidor, lectura from enero2019 ", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String numMedidor = c.getString(0);
            String lectura = c.getString(1);


            buffer.append(numMedidor);

        }
        return buffer.toString();


    }

    public String getConsulta(String consulta) {
        c = db.rawQuery("select num_medidor, lectura from enero2019 where num_medidor='" + consulta + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String numMedidor = c.getString(0);
            String lectura = c.getString(1);

            buffer.append(lectura);

        }
        return buffer.toString();
    }

    public String getUser(String user, String pass) {
        c = db.rawQuery("select nombre, password from operador where nombre='" + user + "' and password='" + pass + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String nombre = c.getString(0);
            String password = c.getString(1);


            buffer.append(nombre);
            buffer.append(password);
        }
        return buffer.toString();
    }

   /* public String getPass(String pass) {
        c = db.rawQuery("select password from operador where password='" + pass + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String password = c.getString(0);


            buffer.append(password);

        }
        return buffer.toString();
    } */

    public String getRegistro(String reg, String num) {
        db.execSQL("update enero2019 set lectura ='" + reg + "' where num_medidor = '"+num +"' ");


        return reg;
    }

}
    /*public String getRegistro(String Registro) {
        c = db.rawQuery()
}*/

   // insert into enero2019 (num_medidor) values (9876) where (num_medidor = '11111')
