package com.example.proyectoclinicaveterinaria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectoclinicaveterinaria.Entidades.Medicacion;

import java.util.ArrayList;

public class DbMedicacion extends DbHelper{
    Context context;
    public DbMedicacion(@Nullable Context context) {
        super(context);
        this.context= context;
    }

    public Cursor mostarMedicamentoenSpinner() {
        try {
            SQLiteDatabase bd = this.getReadableDatabase();
            Cursor filas = bd.rawQuery("SELECT * FROM " + TABLE_MEDICACION+ "  ", null);
            if (filas.moveToFirst()) {
                return filas;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }


    public  long insertarmedicacion(String nombre,Integer precio){

        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("NOMBRE",nombre);
            values.put("PRECIO",precio);

            id = db.insert(TABLE_MEDICACION, null, values);

        }catch (Exception ex){
            ex.toString();
        }


        return  id;

    }

    public ArrayList<Medicacion> Mostrarmedicacion(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Medicacion> listamedicacion=new ArrayList<>();
        Medicacion medicacion=null;
        Cursor cursorMedicacion =null;

        cursorMedicacion =db.rawQuery("SELECT*FROM " + TABLE_MEDICACION,null);
        if(cursorMedicacion.moveToFirst()){
            do{
                medicacion=new Medicacion();
                medicacion.setIdmedicacion(cursorMedicacion.getInt(0));
                medicacion.setNombre(cursorMedicacion.getString(1));
                medicacion.setPrecio(cursorMedicacion.getInt(2));
                listamedicacion.add(medicacion);
            }while(cursorMedicacion.moveToNext());
        }
        cursorMedicacion.close();
        return listamedicacion;
    }



    public Medicacion vermedicacion(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Medicacion medicacion=null;
        Cursor cursorMedicacion;

        cursorMedicacion=db.rawQuery("SELECT * FROM " + TABLE_MEDICACION + " WHERE idmedicacion=" + id + " LIMIT 1", null);
        if(cursorMedicacion.moveToFirst()){

            medicacion=new Medicacion();
            medicacion.setIdmedicacion(cursorMedicacion.getInt(0));
            medicacion.setNombre(cursorMedicacion.getString(1));
            medicacion.setPrecio(cursorMedicacion.getInt(2));

        }
        cursorMedicacion.close();
        return medicacion;
    }


    public boolean editarmedicacion(int idmedicacion, String nombre,Integer precio ){

        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{



            db.execSQL("UPDATE "+ TABLE_MEDICACION + " SET nombre='"+nombre+"',precio='"+precio+"' WHERE idmedicacion="+idmedicacion+"");
            correcto=true;

        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }


        return  correcto;

    }



    public boolean eliminarmedicacion(int id){

        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{



            db.execSQL("DELETE FROM "+TABLE_MEDICACION+ " WHERE idmedicacion="+id+"");
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
