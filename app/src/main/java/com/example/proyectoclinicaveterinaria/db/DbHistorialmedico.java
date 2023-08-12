package com.example.proyectoclinicaveterinaria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectoclinicaveterinaria.Entidades.HistorialMedicacionyMascotas;
import com.example.proyectoclinicaveterinaria.Entidades.Historialmedico;

import java.util.ArrayList;

public class DbHistorialmedico extends DbHelper{
    Context context;
    public DbHistorialmedico(@Nullable Context context) {
        super(context);
        this.context= context;
    }



    public  long insertarhistorialmedico(String enfermedadpadecida,String tratamiento,String fecha,Integer idmedicacion,Integer idmascotas){

        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("ENFERMEDADPADECIDA",enfermedadpadecida);
            values.put("TRATAMIENTO",tratamiento);
            values.put("FECHA",fecha);
            values.put("IDMEDICACION",idmedicacion);
            values.put("IDMASCOTAS",idmascotas);
            id = db.insert(TABLE_HISTORIALMEDICO, null, values);

        }catch (Exception ex){
            ex.toString();
        }


        return  id;

    }


    public ArrayList<HistorialMedicacionyMascotas> Mostrarhistorialmedico(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<HistorialMedicacionyMascotas> listahistorialmedico=new ArrayList<>();
        HistorialMedicacionyMascotas historialmedico=null;
        Cursor cursorHistorialmedico =null;

        cursorHistorialmedico =db.rawQuery("SELECT h.idhistorial , h.ENFERMEDADPADECIDA , h.TRATAMIENTO , h.FECHA, m.NOMBRE ,mas.NOMBRE  FROM " + TABLE_HISTORIALMEDICO + " AS h "
                +" JOIN "+ TABLE_MEDICACION + " AS m " + " ON h."+ "IDMEDICACION" + " = m."+ "idmedicacion" + " JOIN " + TABLE_MASCOTAS + " AS mas "+ "ON  h."+"IDMASCOTAS"
                +" =mas."+ "idmascotas",null);
        if(cursorHistorialmedico.moveToFirst()){
            do{
                historialmedico=new HistorialMedicacionyMascotas();
                historialmedico.setIdhistorial(cursorHistorialmedico.getInt(0));
                historialmedico.setEnfermedadpadecida(cursorHistorialmedico.getString(1));
                historialmedico.setTratamiento(cursorHistorialmedico.getString(2));
                historialmedico.setFecha(cursorHistorialmedico.getString(3));
                historialmedico.setNombremedicacion(cursorHistorialmedico.getString(4));
                historialmedico.setNombremascotas(cursorHistorialmedico.getString(5));
                listahistorialmedico.add(historialmedico);
            }while(cursorHistorialmedico.moveToNext());
        }
        cursorHistorialmedico.close();
        return listahistorialmedico;
    }



    public HistorialMedicacionyMascotas verhistorialmedico(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        HistorialMedicacionyMascotas historialmedico=null;
        Cursor cursorHistorialmedico;

        cursorHistorialmedico =db.rawQuery("SELECT h.idhistorial , h.ENFERMEDADPADECIDA , h.TRATAMIENTO , h.FECHA, m.NOMBRE ,mas.NOMBRE  FROM " + TABLE_HISTORIALMEDICO + " AS h "
                +" JOIN "+ TABLE_MEDICACION + " AS m " + " ON h."+ "IDMEDICACION" + " = m."+ "idmedicacion" + " JOIN " + TABLE_MASCOTAS + " AS mas "+ "ON  h."+"IDMASCOTAS"
                +" =mas."+ "idmascotas"+ " WHERE idhistorial=" + id + " LIMIT 1", null);
        if(cursorHistorialmedico.moveToFirst()){

            historialmedico=new HistorialMedicacionyMascotas();
            historialmedico.setIdhistorial(cursorHistorialmedico.getInt(0));
            historialmedico.setEnfermedadpadecida(cursorHistorialmedico.getString(1));
            historialmedico.setTratamiento(cursorHistorialmedico.getString(2));
            historialmedico.setFecha(cursorHistorialmedico.getString(3));
            historialmedico.setNombremedicacion(cursorHistorialmedico.getString(4));
            historialmedico.setNombremascotas(cursorHistorialmedico.getString(5));

        }
        cursorHistorialmedico.close();
        return historialmedico;
    }


    public boolean editarhistorialmedico(Integer idhistorial, String enfermedadpadecida,String tratamiento,String fecha,Integer idmedicacion,Integer idmascotas ){

        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{



            db.execSQL("UPDATE "+ TABLE_HISTORIALMEDICO + " SET enfermedadpadecida='"+enfermedadpadecida+"',tratamiento='"+tratamiento+"',fecha='"+fecha+"',idmedicacion='"+idmedicacion+"',idmedicacion='"+idmascotas+"' WHERE idhistorial="+idhistorial+"");
            correcto=true;

        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }


        return  correcto;

    }



    public boolean eliminarhistorialmedico(int id){

        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{



            db.execSQL("DELETE FROM "+TABLE_HISTORIALMEDICO+ " WHERE idhistorial="+id+"");
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
