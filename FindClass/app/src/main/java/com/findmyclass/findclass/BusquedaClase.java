package com.findmyclass.findclass;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BusquedaClase extends Activity
{
    int[] idIncluidos = new int[10];
    int pos;
    int numeroAsignaturas;
    int id;
    int idUniversidad;
    int numeroAulas;

    String nombreAsignatura;
    String[] descripciones;

    double[] latitudes;
    double[] longitudes;

    Bundle datos;

    private ListView lista;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda);

        datos = getIntent().getExtras();

        /*String datosObtenidos = "Asignatura: " + datos.getString("asignatura") + "\n"
                + "Dia: " + datos.getString("dia") + "\n"
                + "Horario: " + datos.getString("horario") + "\n"
                + "Direccion: " + datos.getString("direccion") + "\n"
                + "Planta: " + datos.getString("planta") + "\n"
                + "Edificio: " + datos.getString("edificio") + "\n"
                + "Facultad: " + datos.getString("facultad") + "\n"
                + "Aula: " + datos.getString("aula") + "\n"
                + "Pais: " + datos.getString("pais") + "\n"
                + "Ciudad: " + datos.getString("ciudad") + "\n";

        TextView mostrarClase = findViewById(R.id.infoHorario);
        mostrarClase.setText(datosObtenidos);*/

        lista = findViewById(R.id.list);
        try{
            DBHelper adminSQL = new DBHelper(this, SQLConstants.DB, null,1);
            SQLiteDatabase bd = adminSQL.getReadableDatabase();

            items = new ArrayList<>();
            adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_2, android.R.id.text2, items);
            lista.setAdapter(adapter);
            Cursor cursor = bd.rawQuery("SELECT * FROM clases WHERE asignatura = '"
                    + datos.getString("asignatura") + "'", null);
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


    public void Aniadir(View vista)
    {
        idIncluidos[numeroAsignaturas] = id;
        numeroAsignaturas++;

        Button boton = (Button) findViewById(R.id.aniadir);
        boton.setEnabled(false);

        Button botonQuitar = (Button) findViewById(R.id.quitar);
        botonQuitar.setEnabled(true);
    }



    public void Quitar(View vista)
    {
        int aux = pos - 1;

        if(aux != numeroAsignaturas)
        {
            for(int i = pos; i < numeroAsignaturas - 1; i++)
            {
                idIncluidos[i] = idIncluidos[i+1];
            }
        }
        numeroAsignaturas--;

        Button boton = (Button) findViewById(R.id.aniadir);
        boton.setEnabled(true);

        Button botonQuitar = (Button) findViewById(R.id.quitar);
        botonQuitar.setEnabled(false);
    }



    public void onBackPressed()
    {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("numeroAsignaturas", numeroAsignaturas);
        i.putExtra("idIncluidos", idIncluidos);
        startActivity(i);
    }



    public void AbrirMapa(View vista)
    {
        Intent i = new Intent(this, MapaUniversidad.class);
        //i.putExtra("latitudes", datos.getDouble());
        i.putExtra("longitudes", datos.getDouble("longitud"));
        i.putExtra("latitudes", datos.getDouble("latitud"));
        i.putExtra("nombreAsignatura", datos.getString("asignatura"));
        i.putExtra("nombreAula", datos.getString("aula"));


        startActivity(i);
    }
}
