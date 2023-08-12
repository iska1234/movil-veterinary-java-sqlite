package com.example.proyectoclinicaveterinaria.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.proyectoclinicaveterinaria.Entidades.VeterinarioyEspecialidad;

import java.util.ArrayList;

public class DbVeterinarioyEspecialidad extends DbHelper{
    Context context;

    public DbVeterinarioyEspecialidad(@Nullable Context context) {
        super(context);
        this.context= context;
    }

    public ArrayList<VeterinarioyEspecialidad> MostrarveterinariosA(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<VeterinarioyEspecialidad> listaveterinarios =new ArrayList<>();
        VeterinarioyEspecialidad veterinariosa =null;

        Cursor cursorVeterinarios =null;

        cursorVeterinarios =db.rawQuery("SELECT v.idveterinario,v.NOMBRE,v.APELLIDOS,v.DNI,v.CELULAR,v.DIRECCION,v.GENERO,v.CORREO,e.NOMBREDEESPECIALIDAD FROM " + TABLE_VETERINARIOS + " AS v "
                +" JOIN "+ TABLE_ESPECIALIDADES + " AS e " + " ON v."+ "IDESPECIALIDADES" + " = e."+ "idespecialidades",null);
        if(cursorVeterinarios.moveToFirst()){
            do{
                veterinariosa =new VeterinarioyEspecialidad();

                veterinariosa.setIdveterinario(cursorVeterinarios.getInt(0));
                veterinariosa.setNombre(cursorVeterinarios.getString(1));
                veterinariosa.setApellidos(cursorVeterinarios.getString(2));
                veterinariosa.setDni(cursorVeterinarios.getInt(3));
                veterinariosa.setCelular(cursorVeterinarios.getInt(4));
                veterinariosa.setDireccion(cursorVeterinarios.getString(5));
                veterinariosa.setGenero(cursorVeterinarios.getString(6));
                veterinariosa.setCorreo(cursorVeterinarios.getString(7));
                veterinariosa.setNombreespecialidad(cursorVeterinarios.getString(8));
                listaveterinarios.add(veterinariosa);
            }while(cursorVeterinarios.moveToNext());
        }
        cursorVeterinarios.close();
        return listaveterinarios;
    }



    public ArrayList<VeterinarioyEspecialidad> MostrarveterinariosporEspecialidad(Integer idespecialidad){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<VeterinarioyEspecialidad> listaveterinarios =new ArrayList<>();
        VeterinarioyEspecialidad veterinariosa =null;

        Cursor cursorVeterinarios =null;

        cursorVeterinarios =db.rawQuery("SELECT v.idveterinario,v.NOMBRE,v.APELLIDOS,v.DNI,v.CELULAR,v.DIRECCION,v.GENERO,v.CORREO,e.NOMBREDEESPECIALIDAD FROM " + TABLE_VETERINARIOS + " AS v "
                +" JOIN "+ TABLE_ESPECIALIDADES + " AS e " + " ON v."+ "IDESPECIALIDADES" + " = e."+ "idespecialidades" + " WHERE e." + "idespecialidades"+ "=" + idespecialidad,null);
        if(cursorVeterinarios.moveToFirst()){
            do{
                veterinariosa =new VeterinarioyEspecialidad();

                veterinariosa.setIdveterinario(cursorVeterinarios.getInt(0));
                veterinariosa.setNombre(cursorVeterinarios.getString(1));
                veterinariosa.setApellidos(cursorVeterinarios.getString(2));
                veterinariosa.setDni(cursorVeterinarios.getInt(3));
                veterinariosa.setCelular(cursorVeterinarios.getInt(4));
                veterinariosa.setDireccion(cursorVeterinarios.getString(5));
                veterinariosa.setGenero(cursorVeterinarios.getString(6));
                veterinariosa.setCorreo(cursorVeterinarios.getString(7));
                veterinariosa.setNombreespecialidad(cursorVeterinarios.getString(8));
                listaveterinarios.add(veterinariosa);
            }while(cursorVeterinarios.moveToNext());
        }
        cursorVeterinarios.close();
        return listaveterinarios;
    }
}
