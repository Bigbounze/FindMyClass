package com.findmyclass.findclass;

public class SQLConstants {
    //DB
    public static final String DB = "dbClases.db";

    //TABLES
    public static final String tableClases = "clases";

    //COLUMNS
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DIRECCION = "direccion";
    public static final String COLUMN_PLANTA = "planta";
    public static final String COLUMN_EDIFICIO = "edificio";
    public static final String COLUMN_FACULTAD = "facultad";
    public static final String COLUMN_AULA = "aula";
    public static final String COLUMN_PAIS = "pais";
    public static final String COLUMN_CIUDAD = "ciudad";

    //QUERY
    public static final String SQL_CREATE_TABLE_CLASES = "CREATE TABLE IF NOT EXISTS " + tableClases + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_DIRECCION + " TEXT," +
            COLUMN_PLANTA + " TEXT," +
            COLUMN_EDIFICIO + " TEXT," +
            COLUMN_FACULTAD + " TEXT," +
            COLUMN_AULA + " TEXT," +
            COLUMN_PAIS + " TEXT," +
            COLUMN_CIUDAD + " TEXT" + ");";

    public static final String SQL_DELETE = "DROP TABLE " + tableClases;

    public static final String[] ALL_COLUMNS = {COLUMN_ID,COLUMN_DIRECCION,COLUMN_PLANTA,
            COLUMN_EDIFICIO,COLUMN_FACULTAD,COLUMN_AULA,COLUMN_PAIS,COLUMN_CIUDAD};
}

