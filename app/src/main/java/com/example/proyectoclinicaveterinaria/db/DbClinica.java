package com.example.proyectoclinicaveterinaria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectoclinicaveterinaria.Entidades.Clinica;

import java.util.ArrayList;

public class DbClinica extends DbHelper {


    Context context;
    public DbClinica(@Nullable Context context) {
        super(context);
        this.context= context;
    }

    public  long insertarClinica(String sede , String direccion, Integer telefono){

        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("SEDE",sede);
            values.put("DIRECCION",direccion);
            values.put("TELEFONO",telefono);

            id = db.insert(TABLE_CLINICA, null, values);

        }catch (Exception ex){
            ex.toString();
        }


        return  id;

    }

    public ArrayList<Clinica> Mostrarclinica(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Clinica> listaclinica=new ArrayList<>();
        Clinica clinica =null;
        Cursor cursorClinica =null;

        cursorClinica =db.rawQuery("SELECT*FROM " + TABLE_CLINICA,null);
        if(cursorClinica.moveToFirst()){
            do{
                clinica =new Clinica();
                clinica.setIdclinica(cursorClinica.getInt(0));
                clinica.setSede(cursorClinica.getString(1));
                clinica.setDireccion(cursorClinica.getString(2));
                clinica.setTelefono(cursorClinica.getInt(3));

                listaclinica.add(clinica);
            }while(cursorClinica.moveToNext());
        }
        cursorClinica.close();
        return listaclinica;
    }



    public Clinica verclinica(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Clinica clinica =null;
        Cursor cursorClinica;

        cursorClinica =db.rawQuery("SELECT * FROM " + TABLE_CLINICA + " WHERE idclinica=" + id + " LIMIT 1", null);
        if(cursorClinica.moveToFirst()){

            clinica =new Clinica();
            clinica.setIdclinica(cursorClinica.getInt(0));
            clinica.setSede(cursorClinica.getString(1));
            clinica.setDireccion(cursorClinica.getString(2));
            clinica.setTelefono(cursorClinica.getInt(3));


        }
        cursorClinica.close();
        return clinica;
    }


    public boolean editarClinica(int idclinica,String sede,String direccion, Integer telefono){

        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{



            db.execSQL("UPDATE "+ TABLE_CLINICA + " SET sede='"+sede+"',direccion='"+direccion+"',telefono='"+telefono+"' WHERE idclinica="+idclinica+"");
            correcto=true;

        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }


        return  correcto;

    }



    public boolean eliminarClinica(int id){

        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{



            db.execSQL("DELETE FROM "+TABLE_CLINICA+ " WHERE idclinica="+id+"");
            correcto=true;

        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }


        return  correcto;

    }





}
