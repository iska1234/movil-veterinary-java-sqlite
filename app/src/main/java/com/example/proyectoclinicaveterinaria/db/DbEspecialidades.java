package com.example.proyectoclinicaveterinaria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectoclinicaveterinaria.Entidades.Especialidades;

import java.util.ArrayList;

public class DbEspecialidades extends DbHelper {
    Context context;
    public DbEspecialidades(@Nullable Context context) {
        super(context);
        this.context= context;
    }

    public Cursor mostarEspecialidadenSpinner() {
        try {
            SQLiteDatabase bd = this.getReadableDatabase();
            Cursor filas = bd.rawQuery("SELECT * FROM " + TABLE_ESPECIALIDADES+ " ORDER BY NOMBREDEESPECIALIDAD ASC", null);
            if (filas.moveToFirst()) {
                return filas;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public  long insertarespecialidad(String nombredeespecialidad,String descripcion){

        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("NOMBREDEESPECIALIDAD",nombredeespecialidad);
            values.put("DESCRIPCION",descripcion);


            id = db.insert(TABLE_ESPECIALIDADES, null, values);

        }catch (Exception ex){
            ex.toString();
        }


        return  id;

    }

    public ArrayList<Especialidades> Mostrarespecialidad(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Especialidades> listaespecialidad=new ArrayList<>();
        Especialidades especialidades=null;
        Cursor cursorEspecialidad =null;

        cursorEspecialidad =db.rawQuery("SELECT*FROM " + TABLE_ESPECIALIDADES,null);
        if(cursorEspecialidad.moveToFirst()){
            do{
                especialidades=new Especialidades();
                especialidades.setIdespecialidades(cursorEspecialidad.getInt(0));
                especialidades.setNombredeespecialidad(cursorEspecialidad.getString(1));
                especialidades.setDescripcion(cursorEspecialidad.getString(2));
                listaespecialidad.add(especialidades);
            }while(cursorEspecialidad.moveToNext());
        }
        cursorEspecialidad.close();
        return listaespecialidad;
    }



    public Especialidades verespecialidades(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        Especialidades especialidades=null;
        Cursor cursorEspecialidad;

        cursorEspecialidad =db.rawQuery("SELECT * FROM " + TABLE_ESPECIALIDADES + " WHERE idespecialidades=" + id + " LIMIT 1", null);
        if(cursorEspecialidad.moveToFirst()){

            especialidades=new Especialidades();
            especialidades.setIdespecialidades(cursorEspecialidad.getInt(0));
            especialidades.setNombredeespecialidad(cursorEspecialidad.getString(1));
            especialidades.setDescripcion(cursorEspecialidad.getString(2));

        }
        cursorEspecialidad.close();
        return especialidades;
    }


    public boolean editarespecialidad(int idespecialidades, String nombredeespecialidad,String descripcion ){

        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{



            db.execSQL("UPDATE "+ TABLE_ESPECIALIDADES + " SET nombredeespecialidad='"+nombredeespecialidad+"',descripcion='"+descripcion+"' WHERE idespecialidades="+idespecialidades+"");
            correcto=true;

        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }


        return  correcto;

    }



    public boolean eliminarespecialidad(int id){

        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{



            db.execSQL("DELETE FROM "+TABLE_ESPECIALIDADES+ " WHERE idespecialidades="+id+"");
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
