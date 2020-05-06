package com.example.bdregistro;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class RegistroCursorAdapter extends CursorAdapter {
    public RegistroCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.dato_registro, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView cedula = (TextView) view.findViewById(R.id.txtcedula);
        TextView nombre = (TextView) view.findViewById(R.id.txtnombre);
        TextView estrato = (TextView) view.findViewById(R.id.txtestrato);
        TextView salario = (TextView) view.findViewById(R.id.txtsalario);
        TextView nivel = (TextView) view.findViewById(R.id.txtnivel);
        // Extract properties from cursor
        String ced = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String nom = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
        String estr = cursor.getString(cursor.getColumnIndexOrThrow("estrato"));
        String sala = cursor.getString(cursor.getColumnIndexOrThrow("salario"));
        String niv = cursor.getString(cursor.getColumnIndexOrThrow("nivel"));
        // Populate fields with extracted properties
        cedula.setText(ced);
        nombre.setText(nom);
        estrato.setText(estr);
        salario.setText(sala);
        nivel.setText(niv);

    }
}
