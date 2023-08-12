package com.example.proyectoclinicaveterinaria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectoclinicaveterinaria.Entidades.Veterinarios;
import com.example.proyectoclinicaveterinaria.Entidades.VeterinarioyEspecialidad;

import java.util.ArrayList;

public class DbVeterinarios extends DbHelper{
    Context context;
    public DbVeterinarios(@Nullable Context context) {
        super(context);
        this.context= context;
    }


    public  long insertarveterinarios(String nombre,String apellidos, Integer dni, Integer celular , String direccion,String genero , String correo,Integer idespecialidades){

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
            values.put("IDESPECIALIDADES",idespecialidades);

            id = db.insert(TABLE_VETERINARIOS, null, values);

        }catch (Exception ex){
            ex.toString();
        }


        return  id;

    }

    /*
    public ArrayList<Veterinarios> Mostrarveterinarios(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Veterinarios> listaveterinarios =new ArrayList<>();
        Veterinarios veterinarios =null;
        Cursor cursorVeterinarios =null;

        cursorVeterinarios =db.rawQuery("SELECT*FROM " + TABLE_VETERINARIOS,null);
        if(cursorVeterinarios.moveToFirst()){
            do{
                veterinarios =new Veterinarios();
                veterinarios.setIdveterinario(cursorVeterinarios.getInt(0));
                veterinarios.setNombre(cursorVeterinarios.getString(1));
                veterinarios.setApellidos(cursorVeterinarios.getString(2));
                veterinarios.setDni(cursorVeterinarios.getInt(3));
                veterinarios.setCelular(cursorVeterinarios.getInt(4));
                veterinarios.setDireccion(cursorVeterinarios.getString(5));
                veterinarios.setGenero(cursorVeterinarios.getString(6));
                veterinarios.setCorreo(cursorVeterinarios.getString(7));
                veterinarios.setFoto(cursorVeterinarios.getInt(8));
                veterinarios.setIdespecialidades(cursorVeterinarios.getInt(9));
                listaveterinarios.add(veterinarios);
            }while(cursorVeterinarios.moveToNext());
        }
        cursorVeterinarios.close();
        return listaveterinarios;
    }

     */




    public VeterinarioyEspecialidad verveterinarios(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        VeterinarioyEspecialidad veterinarios =null;
        Cursor cursorVeterinarios;

        cursorVeterinarios =db.rawQuery( "SELECT v.idveterinario,v.NOMBRE,v.APELLIDOS,v.DNI,v.CELULAR,v.DIRECCION,v.GENERO,v.CORREO,e.NOMBREDEESPECIALIDAD FROM " + TABLE_VETERINARIOS + " AS v "
                +" JOIN "+ TABLE_ESPECIALIDADES + " AS e " + " ON v."+ "IDESPECIALIDADES" + " = e."+ "idespecialidades" + "  WHERE idveterinario=" + id + " LIMIT 1", null);
        if(cursorVeterinarios.moveToFirst()){

            veterinarios =new VeterinarioyEspecialidad();
            veterinarios.setIdveterinario(cursorVeterinarios.getInt(0));
            veterinarios.setNombre(cursorVeterinarios.getString(1));
            veterinarios.setApellidos(cursorVeterinarios.getString(2));
            veterinarios.setDni(cursorVeterinarios.getInt(3));
            veterinarios.setCelular(cursorVeterinarios.getInt(4));
            veterinarios.setDireccion(cursorVeterinarios.getString(5));
            veterinarios.setGenero(cursorVeterinarios.getString(6));
            veterinarios.setCorreo(cursorVeterinarios.getString(7));
            veterinarios.setNombreespecialidad(cursorVeterinarios.getString(8));


        }
        cursorVeterinarios.close();
        return veterinarios;
    }


    public boolean editarVeterinarios(int idveterinario,String nombre,String apellidos, Integer dni, Integer celular , String direccion,String genero , String correo,Integer idespecialidades){

        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{



            db.execSQL("UPDATE "+ TABLE_VETERINARIOS + " SET nombre='"+nombre+"',apellidos='"+apellidos+"',dni='"+dni+"',celular='"+celular+"',direccion='"+direccion+"',genero='"+genero+"',correo='"+correo+"',idespecialidades='"+idespecialidades+"' WHERE idveterinario="+idveterinario+"");
            correcto=true;

        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }


        return  correcto;

    }



    public boolean eliminarVeterinario(int id){

        boolean correcto=false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{



            db.execSQL("DELETE FROM "+TABLE_VETERINARIOS+ " WHERE idveterinario="+id+"");
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
