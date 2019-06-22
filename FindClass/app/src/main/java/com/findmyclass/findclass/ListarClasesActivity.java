package com.findmyclass.findclass;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListarClasesActivity extends AppCompatActivity {

    private ListView lista;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_clases);

        lista = findViewById(R.id.lista);
        try{
            DBHelper adminSQL = new DBHelper(this, SQLConstants.DB, null,1);
            SQLiteDatabase bd = adminSQL.getReadableDatabase();

            items = new ArrayList<String>();
            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_2, android.R.id.text2, items);
            lista.setAdapter(adapter);
            Cursor cursor = bd.rawQuery("SELECT * FROM clases", null);
            if (cursor.moveToFirst()){
                while (cursor != null){
                    items.add("Asignatura: " + cursor.getString(1) + "\n"
                            + "Dia: " + cursor.getString(11) + "\n"
                            + "Horario: " + cursor.getString(12) + "\n"
                            + "Direccion: " + cursor.getString(2) + "\n"
                            + "Planta: " + cursor.getString(3) + "\n"
                            + "Edificio: " + cursor.getString(4) + "\n"
                            + "Facultad: " + cursor.getString(5) + "\n"
                            + "Aula: " + cursor.getString(6) + "\n"
                            + "Pais: " + cursor.getString(7) + "\n"
                            + "Ciudad: " + cursor.getString(8) + "\n");
                    cursor.moveToNext();
                }
            }
            bd.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
