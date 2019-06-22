package com.findmyclass.findclass;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity
{
    int[] idIncluidos = new int[10];
    int numeroAsignaturas = 0;

    private EditText editBusqueda;
    private Button buscar;
    private Button all;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //CREAR BASE DE DATOS
                //SQLiteDatabase bd = openOrCreateDatabase("dbClases.db", MODE_PRIVATE, null);


                //Open your local db as the input stream
                String packageName = getApplicationContext().getPackageName();
                String DB_PATH = "/data/data/" + packageName + "/databases/";
                //Create the directory if it does not exist
                File directory = new File(DB_PATH);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String DB_NAME = "dbClases.db"; //The name of the source sqlite file

                InputStream myInput = getAssets().open("dbClases.db");

                // Path to the just created empty db
                String outFileName = DB_PATH + DB_NAME;

                //Open the empty db as the output stream
                OutputStream myOutput = new FileOutputStream(outFileName);

                //transfer bytes from the inputfile to the outputfile
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                //Close the streams
                myOutput.flush();
                myOutput.close();
                myInput.close();



        } catch (Exception e){
            e.printStackTrace();
        }


        Bundle datos = getIntent().getExtras();

        editBusqueda = findViewById(R.id.editBusqueda);
        buscar = findViewById(R.id.buscar);
        all = findViewById(R.id.mostrar);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListarClasesActivity.class));
            }
        });

        if(datos != null)
        {
            numeroAsignaturas = datos.getInt("numeroAsignaturas");
            idIncluidos = datos.getIntArray("idIncluidos");
        }
    }


    public void Buscar(View view) {
        //SQLite
        try {
            String asignatura = editBusqueda.getText().toString().toUpperCase();
            DBHelper adminSQL = new DBHelper(this, SQLConstants.DB, null, 1);
            SQLiteDatabase bd = adminSQL.getReadableDatabase();
            String sql = "SELECT * FROM clases WHERE asignatura = '" + asignatura + "'";
            Log.i("BDSQL", sql);
            Cursor cursor = bd.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                //editBusqueda.setText(cursor.getString(1));
                Intent i = new Intent(this, BusquedaClase.class);
                i.putExtra("asignatura", cursor.getString(1));
                i.putExtra("direccion", cursor.getString(2));
                i.putExtra("planta", cursor.getString(3));
                i.putExtra("edificio", cursor.getString(4));
                i.putExtra("facultad", cursor.getString(5));
                i.putExtra("aula", cursor.getString(6));
                i.putExtra("pais", cursor.getString(7));
                i.putExtra("ciudad", cursor.getString(8));
                i.putExtra("latitud", cursor.getDouble(9));
                i.putExtra("longitud", cursor.getDouble(10));
                i.putExtra("dia", cursor.getString(11));
                i.putExtra("horario", cursor.getString(12));
                startActivity(i);

            } else {
                Toast.makeText(this, "NO existe la ASIGNATURA", Toast.LENGTH_SHORT).show();
            }
            bd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BusquedaAvanzada(View vista)
    {
        Intent i = new Intent(this, BusquedaAvanzada.class);
        startActivity(i);
    }


    public boolean onCreateOptionsMenu(Menu mimenu)
    {
        getMenuInflater().inflate(R.menu.menu_ajustes, mimenu);

        return true;
    }


    public void Ejecutar_perfil(View vista)
    {
        Intent i = new Intent(this, PerfilClase.class);

        startActivity(i);
    }



    public void Ejecutar_horario(View vista)
    {
        Intent i = new Intent(this, HorarioClase.class);

        i.putExtra("numeroAsignaturas", numeroAsignaturas);
        i.putExtra("idIncluidos", idIncluidos);

        startActivity(i);
    }



    public void Ejecutar_configuracion(View vista)
    {
        Intent i = new Intent(this, ConfiguracionClase.class);

        startActivity(i);
    }



    public void Ejecutar_info(View vista)
    {
        Intent i = new Intent(this, InfoClase.class);

        startActivity(i);
    }



    public boolean onOptionsItemSelected (MenuItem opcion_menu)
    {
        int id = opcion_menu.getItemId();

        if(id == R.id.perfil)
        {
            Ejecutar_perfil(null);
            return true;
        }
        else if(id == R.id.horario)
        {
            Ejecutar_horario(null);
            return true;
        }
        else if(id == R.id.configuracion)
        {
            Ejecutar_configuracion(null);
            return true;
        }
        else if (id == R.id.info)
        {
            Ejecutar_info(null);
            return true;
        }

        return super.onOptionsItemSelected(opcion_menu);
    }
}