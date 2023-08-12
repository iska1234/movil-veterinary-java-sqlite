package com.example.proyectoclinicaveterinaria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectoclinicaveterinaria.Entidades.Citas;

import java.util.ArrayList;

public class DbCitas extends DbHelper{

    Context context;
    public DbCitas(@Nullable Context context) {
        super(context);
        this.context= context;
    }

    public  long insertarCitas(String tipodecita,String nombremascota, String veterinario, String clinica , String fecha,String hora , String descripcion){

        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("TIPODECITA",tipodecita);
            values.put("NOMBREMASCOTA",nombremascota);
            values.put("VETERINARIO",veterinario);
            values.put("CLINICA",clinica);
            values.put("FECHACITA",fecha);
            values.put("HORACITA",hora);
            values.put("DESCRIPCION",descripcion);

            id = db.insert(TABLE_CITAS, null, values);

        }catch (Exception ex){
            ex.toString();
        }


        return  id;

    }

    public ArrayList<Citas>Mostrarcitas(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Citas> listacitas=new ArrayList<>();
        Citas cita=null;
        Cursor cursorCitas=null;

        cursorCitas=db.rawQuery("SELECT*FROM " + TABLE_CITAS,null);
        if(cursorCitas.moveToFirst()){
            do{
                cita=new Citas();
                cita.setNrocitas(cursorCitas.getInt(0));
                cita.setTIPODECITA("( " + cursorCitas.getString(1)+" )");
                cita.setNOMBREMASCOTA(cursorCitas.getString(2));
                cita.setVETERINARIO("Veterinario    :  " + cursorCitas.getString(3));
                cita.setCLINICA("Clinica            :  " + cursorCitas.getString(4));
                cita.setFECHACITA("Fecha            :  " +cursorCitas.getString(5));
                cita.setHORACITA("Hora              :  " +cursorCitas.getString(6));
                cita.setDESCRIPCION("Descripción    :  " +cursorCitas.getString(7));
                listacitas.add(cita);
            }while(cursorCitas.moveToNext());
        }
        cursorCitas.close();
        return listacitas;
    }

    public Citas vercitas(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Citas cita=null;
        Cursor cursorCitas;

        cursorCitas=db.rawQuery("SELECT * FROM " + TABLE_CITAS + " WHERE nrocitas=" + id + " LIMIT 1", null);
        if(cursorCitas.moveToFirst()){

            cita=new Citas();
            cita.setNrocitas(cursorCitas.getInt(0));
            cita.setTIPODECITA(cursorCitas.getString(1));
            cita.setNOMBREMASCOTA(cursorCitas.getString(2));
            cita.setVETERINARIO(cursorCitas.getString(3));
            cita.setCLINICA(cursorCitas.getString(4));
            cita.setFECHACITA(cursorCitas.getString(5));
            cita.setHORACITA(cursorCitas.getString(6));
            cita.setDESCRIPCION(cursorCitas.getString(7));


        }
        cursorCitas.close();
        return cita;
    }


    public boolean editarCitas(int id,String tipodecita,String nombremascota, String veterinario, String clinica , String fecha,String hora , String descripcion){

        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{



            db.execSQL("UPDATE "+ TABLE_CITAS + " SET tipodecita='"+tipodecita+"',nombremascota='"+nombremascota+"',veterinario='"+veterinario+"',clinica='"+clinica+"',fechacita='"+fecha+"',horacita='"+hora+"',descripcion='"+descripcion+"' WHERE nrocitas="+id+"");
            correcto=true;

        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }


        return  correcto;

    }



    public boolean eliminarCitas(int id){

        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{



            db.execSQL("DELETE FROM "+TABLE_CITAS+ " WHERE nrocitas="+id+"");
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
