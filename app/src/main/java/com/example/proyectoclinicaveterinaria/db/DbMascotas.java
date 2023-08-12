package com.example.proyectoclinicaveterinaria.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.proyectoclinicaveterinaria.DAO.MascotaDAO;
import com.example.proyectoclinicaveterinaria.Entidades.Mascota;

import java.util.ArrayList;

public class DbMascotas extends DbHelper{

    Context context;
    public DbMascotas(@Nullable Context context) {
        super(context);
        this.context= context;

    }

    public  long insertarMascota(String xnombre , Double peso  , String raza , String especie ,String genero ,String edad){

        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("NOMBRE",xnombre);
            values.put("PESO",peso);
            values.put("RAZA",raza);
            values.put("ESPECIE",especie);
            values.put("GENERO",genero);
            values.put("EDAD",edad);

            id = db.insert(TABLE_MASCOTAS, null, values);

        }catch (Exception ex){
            ex.toString();
        }


        return  id;

    }

    public ArrayList<Mascota> mostrarMascota(MascotaDAO mascotaDAO){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Mascota> listaMascota = new ArrayList<>();
        Mascota mascota =null;
        Cursor cursorMascota=null;

        cursorMascota = db.rawQuery("SELECT * FROM "+TABLE_MASCOTAS,null);

        if(cursorMascota.moveToFirst()){
            do{
                mascota= new Mascota();
                mascota.setCodmas(cursorMascota.getInt(0));
                mascota.setNommas(cursorMascota.getString(1));
                mascota.setPesomas(cursorMascota.getString(2));
                mascota.setRazamas(cursorMascota.getString(3));
                mascota.setEspecie(cursorMascota.getString(4));
                mascota.setSexomas(cursorMascota.getString(5));
                mascota.setEdad(cursorMascota.getString(6));

                //mascota.setFotomas(cursorMascota.getString(7));
                mascotaDAO.MascotaGuardar(mascota);
                listaMascota.add(mascota);
            }while(cursorMascota.moveToNext());

        }
        cursorMascota.close();
        return listaMascota;
    }
    public boolean updateMascota(Mascota mascota){
        boolean r=false;
        SQLiteDatabase db= getWritableDatabase();
        //String queryString=" UPDATE " + TABLA_PIZZA + " SET nombre " + " = '" + pizza.getNombre() +"' , "+ DESCRIPCION + " = '" + pizza.getInformacion() + "' , "+ FOTO + " = '" + pizza.getImagenn() + "' WHERE " + CODIGOP + " = '" + pizza.getCodigo() +"' ";
        String queryString2=" UPDATE " + TABLE_MASCOTAS + " SET nombre = ?, PESO = ?, RAZA = ?, ESPECIE = ?,GENERO = ?, EDAD = ?" + " WHERE idmascotas = ?" ;
        SQLiteStatement statement = db.compileStatement(queryString2);

        statement.bindString(1,mascota.getNommas());
        statement.bindDouble(2, Double.parseDouble(mascota.getPesomas()));
        statement.bindString(3,mascota.getRazamas());
        statement.bindString(4,mascota.getEspecie());
        statement.bindString(5,mascota.getSexomas());
        statement.bindString(6,mascota.getEdad());
        statement.bindDouble(7,mascota.getCodmas());
        r= true;
        statement.execute();
        db.close();

        return r;
    }
    public boolean deleteOneMascota(int idMascota){
        boolean r=false;
        SQLiteDatabase db= this.getWritableDatabase();
        String queryString="DELETE FROM " + TABLE_MASCOTAS + " WHERE idmascotas" + " = '" + idMascota +"'";
        try (Cursor cursor = db.rawQuery(queryString, null)) {
            if (cursor.moveToFirst()) {
                r= true;
            } else {
                r= false;
            }
        }catch (Exception e){
            Log.d(e.getMessage(),".."+e.getMessage());
        }
        return r;
    }

    public Cursor mostarMascptaenSpinner() {
        try {
            SQLiteDatabase bd = this.getReadableDatabase();
            Cursor filas = bd.rawQuery("SELECT * FROM " + TABLE_MASCOTAS+ "  ", null);
            if (filas.moveToFirst()) {
                return filas;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
}
