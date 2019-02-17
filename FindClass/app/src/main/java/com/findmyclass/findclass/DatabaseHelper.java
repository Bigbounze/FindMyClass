package com.findmyclass.findclass;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB.db";
    private static final String DB_TABLE = "DB_Table";

    //COLUMNS
    private static final String DIRECCION = "Direccion";
    private static final String PLANTA = "Planta";
    private static final String EDIFICIO = "Edificio";
    private static final String FACULTAD = "Facultad";
    private static final String AULA = "Aula";
    private static final String PAIS = "Pais";
    private static final String CIUDAD = "Ciudad";

    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" + DIRECCION + " CHAR, " + PLANTA + " INTEGER, " + EDIFICIO + " CHAR, " +  FACULTAD + " CHAR, " + AULA + " CHAR, "
        + PAIS + " CHAR, " + CIUDAD + " CHAR " + ")";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);

        onCreate(sqLiteDatabase);
    }

    //Create method for view Date
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
}
