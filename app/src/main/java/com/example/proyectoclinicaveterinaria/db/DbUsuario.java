package com.example.proyectoclinicaveterinaria.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbUsuario extends DbHelper{

    Context context;
    public DbUsuario(@Nullable Context context) {
        super(context);
        this.context= context;

    }

    public  long insertarUsuario(String nombre , String apellidos , int dni , int celular,String direccion ,String genero,String correo,String contrasena){

        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("NOMBRE",nombre);
            values.put("APELLIDOS",apellidos);
            values.put("DNI",dni);
            values.put("CELULAR",celular);
            values.put("DIRECCION",direccion);
            values.put("GENERO",genero);
            values.put("CORREO",correo);
            values.put("CONTRASENA",contrasena);

            id = db.insert(TABLE_USUARIOS, null, values);

        }
        catch (Exception ex){
            ex.toString();
        }

        return  id;

    }
/*
    public boolean editarUsuario(int id, String nombre , String apellidos , int dni , int celular,String direccion ,String genero,String correo,String contrasena){

        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{



            db.execSQL("UPDATE "+ TABLE_CITAS + " SET nombre='"+nombre+"',apellidos='"+apellidos+"',dni='"+dni+"',celular='"+celular+"',direccion='"+direccion+"',genero='"+genero+"',correo='"+correo+"',contrasena='"+contrasena+"' WHERE nrocitas="+id+"");
            correcto=true;

        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }


        return  correcto;

    }

 */
}
