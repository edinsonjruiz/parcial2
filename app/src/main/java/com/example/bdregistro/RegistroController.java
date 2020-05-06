package com.example.bdregistro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RegistroController {

    private BaseDatos db;

    public RegistroController(Context context) {
        this.db = new BaseDatos(context);
    }

    public long agregarRegistro(Registro e){
        try{

            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(DefDB.col_cedula, e.cedula);
            valores.put(DefDB.col_nombre, e.nombre);
            valores.put(DefDB.col_estrato, e.estrato);
            valores.put(DefDB.col_salario, e.salario);
            valores.put(DefDB.col_nivel, e.nivel);

            long id = database.insert(DefDB.tabla_reg,null, valores);
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al insertar");
            return 0;
        }
    }

    public boolean buscarRegistro(String cedula){
        String[] args = new String[] {cedula};
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor c = database.query(DefDB.tabla_reg, null, "cedula=?", args, null, null, null);
        if (c.getCount()>0) {
            database.close();
            return true;
        }
        else{
            database.close();
            return false;}
    }

    public void eliminarRegistro(String ced){
            SQLiteDatabase database = db.getReadableDatabase();
            database.execSQL("DELETE FROM registro where cedula=?", new String[]{ced});
    }

    public long actualizarRegistro(Registro e){
        try{
            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();
            String[] args = new String[] {e.cedula};
            valores.put(DefDB.col_nombre, e.nombre);
            valores.put(DefDB.col_estrato, e.estrato);
            valores.put(DefDB.col_salario, e.salario);
            valores.put(DefDB.col_nivel, e.nivel);
            long id = database.update(DefDB.tabla_reg, valores,"cedula=?",args);
            database.close();
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al actualizar");
            return 0;
        }

    }


    public Cursor allRegistros() {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            Cursor cur = database.rawQuery("select cedula as _id, nombre, estrato, salario, nivel from registro", null);
            return cur;
        } catch (Exception ex) {
            System.out.println("Error al consultar");
            return null;
        }
    }

    public Cursor buscarNuevo(String cedula) {
        String[] ced = new String[] {cedula};
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            Cursor cur3 = database.rawQuery("select cedula as _id, nombre, estrato, salario, nivel from registro where cedula=?",ced , null);
            return cur3;
        } catch (Exception ex) {
            System.out.println("Error al consultar");
            return null;
        }
    }

}