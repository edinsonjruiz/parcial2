package com.example.bdregistro;

public class DefDB {

    public static final String nameDb = "registroEducativo";
    public static final String tabla_reg = "registro";
    public static final String col_cedula = "cedula";
    public static final String col_nombre = "nombre";
    public static final String col_estrato = "estrato";
    public static final String col_salario = "salario";
    public static final String col_nivel = "nivel";

    public static final String create_tabla_reg = "CREATE TABLE IF NOT EXISTS " + DefDB.tabla_reg + " (" +
            DefDB.col_cedula + " number," +
            DefDB.col_nombre + " text," +
            DefDB.col_estrato + " text," +
            DefDB.col_salario + " number," +
            DefDB.col_nivel + " text"+
            ");";

}
