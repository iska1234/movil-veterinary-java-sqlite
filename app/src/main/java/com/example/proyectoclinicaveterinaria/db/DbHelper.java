package com.example.proyectoclinicaveterinaria.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "Cita6.db";
    public static final String TABLE_USUARIOS = "t_Usuario";
    public static final String TABLE_MASCOTAS = "t_Mascota";
    public static final String TABLE_CITAS = "t_Citas";
    public static  final  String TABLE_CLINICA = "t_Clinica";
    public static  final  String TABLE_ESPECIALIDADES = "t_Especialidades";
    public static  final  String TABLE_VETERINARIOS = "t_Veterinarios";
    public static  final  String TABLE_HISTORIALMEDICO = "t_Historialmedico";
    protected static  final  String TABLE_MEDICACION = "t_Medicacion";


    Context context;
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE "+ TABLE_USUARIOS +"(" +"idusuario INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "NOMBRE TEXT NOT NULL,"+
                "APELLIDOS TEXT NOT NULL,"+
                "DNI  INT NOT NULL," +
                "CELULAR INT NOT NULL," +
                "DIRECCION TEXT NOT NULL," +
                "GENERO TEXT NOT NULL," +
                "CORREO TEXT NOT NULL," +
                "CONTRASENA TEXT NOT NULL)");

        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_MASCOTAS + "(" + "idmascotas INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE TEXT NOT NULL," +
                "PESO  DOUBLE NOT NULL," +
                "RAZA  TEXT NOT NULL," +
                "ESPECIE TEXT NOT NULL," +
                "GENERO TEXT NOT NULL," +
                "EDAD  TEXT NOT NULL)");

        sqLiteDatabase.execSQL(" CREATE TABLE "+ TABLE_CITAS +"(" +"nrocitas INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "TIPODECITA TEXT NOT NULL,"+
                "NOMBREMASCOTA TEXT NOT NULL,"+
                "VETERINARIO  TEXT NOT NULL,"+
                "CLINICA  TEXT NOT NULL," +
                "FECHACITA TEXT NOT NULL," +
                "HORACITA TEXT NOT NULL," +
                "DESCRIPCION TEXT NOT NULL)");


        sqLiteDatabase.execSQL(" CREATE TABLE "+ TABLE_CLINICA +"(" +"idclinica INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "SEDE TEXT NOT NULL,"+
                "DIRECCION  TEXT NOT NULL,"+
                "TELEFONO  INT NOT NULL)");

        sqLiteDatabase.execSQL(" CREATE TABLE "+ TABLE_ESPECIALIDADES +"(" +"idespecialidades INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "NOMBREDEESPECIALIDAD TEXT NOT NULL,"+
                "DESCRIPCION  TEXT NOT NULL)");


        sqLiteDatabase.execSQL(" CREATE TABLE "+ TABLE_VETERINARIOS +"(" +"idveterinario INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "NOMBRE TEXT NOT NULL,"+
                "APELLIDOS TEXT NOT NULL,"+
                "DNI  INT NOT NULL," +
                "CELULAR INT NOT NULL," +
                "DIRECCION TEXT NOT NULL," +
                "GENERO TEXT NOT NULL," +
                "CORREO TEXT NOT NULL," +
                "IDESPECIALIDADES INT,"+
                " FOREIGN KEY('IDESPECIALIDADES') REFERENCES "+TABLE_ESPECIALIDADES+"('idespecialidades'))");

        sqLiteDatabase.execSQL(" CREATE TABLE "+ TABLE_HISTORIALMEDICO +"(" +"idhistorial INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "ENFERMEDADPADECIDA TEXT NOT NULL,"+
                "TRATAMIENTO TEXT NOT NULL,"+
                "FECHA TEXT NOT NULL," +
                "IDMEDICACION INT NOT NULL,"+
                "IDMASCOTAS INT NOT NULL,"+
                " FOREIGN KEY('IDMEDICACION') REFERENCES "+TABLE_MEDICACION+"('idmedicacion'),"+
                " FOREIGN KEY('IDMASCOTAS') REFERENCES "+TABLE_MASCOTAS+"('idmascotas'))");

        sqLiteDatabase.execSQL(" CREATE TABLE "+ TABLE_MEDICACION +"(" +"idmedicacion INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "NOMBRE TEXT NOT NULL,"+
                "PRECIO INT NOT NULL)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public Boolean checkcorreocontra(String CORREO, String CONTRASENA){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from t_Usuario where CORREO=? and CONTRASENA=?", new String[]{CORREO,CONTRASENA});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public int updatePass(String email, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CONTRASENA",pass);

        return db.update("t_Usuario",contentValues,"CORREO=?", new String[]{email});
    }


}


