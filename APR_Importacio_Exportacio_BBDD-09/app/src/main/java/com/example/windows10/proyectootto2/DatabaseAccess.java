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

    public String getMedidor(String numMedidor) {
        c = db.rawQuery("select medidor from registro where medidor ='"+numMedidor+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String medidor = c.getString(0);
            //String nombre = c.getString(1);
          // String apellido = c.getString(2);
          // String rut = c.getString(0);


            buffer.append(medidor);

        }
        return buffer.toString();


    }

    public String getNombre(String numMedidor) {
        c = db.rawQuery("select medidor, nombre from registro where medidor ='"+numMedidor+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            //String numMedidor = c.getString(3);
            String nombre = c.getString(1);
            //String apellido = c.getString(2);
           // String rut = c.getString(0);

            buffer.append(nombre);

        }
        return buffer.toString();


    }

    public String getApellido(String numMedidor) {
        c = db.rawQuery("select apellido, medidor from registro where medidor ='"+numMedidor+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            //String numMedidor = c.getString(3);
            //String nombre = c.getString(1);
            String apellido = c.getString(0);
            //String rut = c.getString(0);


            buffer.append(apellido);

        }
        return buffer.toString();


    }

    public String getRut(String numMedidor) {
        c = db.rawQuery("select rut, medidor from registro where medidor ='"+numMedidor+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            //String numMedidor = c.getString(3);
            //String nombre = c.getString(1);
            //String apellido = c.getString(2);
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
        c = db.rawQuery("select medidor, lectura from registro where medidor='" + consulta + "'", new String[]{});
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

    public String getRegistro(String reg, String num, String date) {
        db.execSQL("update registro set lectura ='" + reg + "' where medidor = '"+num +"' ");
        db.execSQL("update registro set fecha ='" + date + "' where medidor = '"+num +"' ");


        return reg;
    }
    public String getConExportarMedidor(int numero) {
        c = db.rawQuery("select medidor, lectura from registro where id='" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String medidor = c.getString(0);
            String lectura = c.getString(1);

            buffer.append(medidor);

        }
        return buffer.toString();


    }

    public String getConExportarLectura(int numero) {
        c = db.rawQuery("select medidor, lectura from registro where id='" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String numMedidor = c.getString(0);
            String lectura = c.getString(1);

            buffer.append(lectura);

        }
        return buffer.toString();


    }

    public String getConExportarNombre(int numero) {
        c = db.rawQuery("select nombre, lectura from registro where id='" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String nombre = c.getString(0);
            String lectura = c.getString(1);

            buffer.append(nombre);

        }
        return buffer.toString();


    }

    public String getConExportarApellido(int numero) {
        c = db.rawQuery("select apellido, lectura from registro where id='" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String apellido = c.getString(0);
            String lectura = c.getString(1);

            buffer.append(apellido);

        }
        return buffer.toString();


    }

    public String getConExportarDireccion(int numero) {
        c = db.rawQuery("select direccion, lectura from registro where id='" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String direccion = c.getString(0);
            String lectura = c.getString(1);

            buffer.append(direccion);

        }
        return buffer.toString();


    }

    public String getConExportarSector(int numero) {
        c = db.rawQuery("select sector, lectura from registro where id='" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String sector = c.getString(0);
            String lectura = c.getString(1);

            buffer.append(sector);

        }
        return buffer.toString();


    }
    public String getConExportarDate(int numero) {
        c = db.rawQuery("select medidor, fecha from registro where id='" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String numMedidor = c.getString(0);
            String fecha = c.getString(1);

            buffer.append(fecha);

        }
        return buffer.toString();


    }

    public String getConExportarRut(int numero) {
        c = db.rawQuery("select rut, lectura from registro where id='" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String rut = c.getString(0);
            String lectura = c.getString(1);

            buffer.append(rut);

        }
        return buffer.toString();


    }

    public String getVaciarTabla(String registro) {

        db.execSQL("delete from'"+registro+"' ");


        return registro;
    }

    public String getInsetarTabla(String medidor, String nombre, String apellido, String rut, String direccion, String id) {
        db.execSQL("insert into registro (medidor, lectura, pago, monto, nombre, apellido, direccion, sector, fecha, rut, id) " + "values ('"+medidor+"','"+null+"','"+null+"','"+null+"','"+nombre+"','"+apellido+"','"+direccion +"','"+null+"','"+null+"','"+rut+"','"+id+"')");


        return null;
    }
}

    /*public String getRegistro(String Registro) {
        c = db.rawQuery()
}*/

   // insert into enero2019 (num_medidor) values (9876) where (num_medidor = '11111')
