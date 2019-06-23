package com.findmyclass.findclass;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class BusquedaAvanzada extends Activity
{
    Spinner comboCiudades;
    Spinner comboFacultades;
    Spinner comboEdificios;
    Spinner comboAulas;

    ArrayList<String> listaCiudades;
    ArrayList<String> listaFacultades;
    ArrayList<String> listaEdificios;
    ArrayList<String> listaAulas;

    String ciudad;
    String facultad;
    String edificio;
    String aula;


    DBHelper adminSQL;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busquedaavanzada);

        comboCiudades = findViewById(R.id.busquedaCiudades);
        comboFacultades = findViewById(R.id.busquedaFacultad);
        comboEdificios = findViewById(R.id.busquedaEdificio);
        comboAulas = findViewById(R.id.busquedaAula);

        comboFacultades.setEnabled(false);


        adminSQL = new DBHelper(getApplicationContext(), SQLConstants.DB, null, 1);

        // CIUDADES
        consultarListaCiudades();
        ArrayAdapter<String> adaptadorCiudades = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,listaCiudades);
        comboCiudades.setAdapter(adaptadorCiudades);

        // FACULTADES
        consultarListaFacultades();
        ArrayAdapter<String> adaptadorFacultades = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,listaFacultades);
        comboFacultades.setAdapter(adaptadorFacultades);

        // EDIFICIOS
        consultarListaEdificios();
        ArrayAdapter<String> adaptadorEdificios = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,listaEdificios);
        comboEdificios.setAdapter(adaptadorEdificios);

        // AULAS
        consultarListaAulas();
        ArrayAdapter<String> adaptadorAulas = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,listaAulas);
        comboAulas.setAdapter(adaptadorAulas);

        comboAulas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aula = listaAulas.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void consultarListaCiudades(){
        SQLiteDatabase bd = adminSQL.getReadableDatabase();
        listaCiudades = new ArrayList<>();
        listaCiudades.add("Select City");

        Cursor cursor = bd.rawQuery("SELECT * FROM " + SQLConstants.tableClases, null);

        while (cursor.moveToNext()){
            if(!listaCiudades.contains(cursor.getString(8))){
                listaCiudades.add(cursor.getString(8));
            }
        }
        bd.close();
    }

    private void consultarListaFacultades(){
        final SQLiteDatabase bd = adminSQL.getReadableDatabase();
        listaFacultades = new ArrayList<>();
        listaFacultades.add("Select School");

        comboCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0){
                    comboFacultades.setEnabled(false);
                } else {
                    comboFacultades.setEnabled(true);
                    ciudad = listaCiudades.get(position);

                    Cursor cursor;
                    cursor = bd.rawQuery("SELECT * FROM " + SQLConstants.tableClases + " WHERE ciudad = '"
                            + listaCiudades.get(position) + "'", null);

                    while (cursor.moveToNext()){
                        if(!listaFacultades.contains(cursor.getString(5))){
                            listaFacultades.add(cursor.getString(5));
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void consultarListaEdificios(){
        final SQLiteDatabase bd = adminSQL.getReadableDatabase();
        listaEdificios = new ArrayList<>();
        listaEdificios.add("Select Building");

        comboFacultades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (position == 0){
                    comboEdificios.setEnabled(false);
                } else {
                    comboEdificios.setEnabled(true);
                    facultad = listaFacultades.get(position);

                    Cursor cursor;
                    cursor = bd.rawQuery("SELECT * FROM " + SQLConstants.tableClases + " WHERE ciudad = '"
                            + ciudad + "' AND facultad = '" + listaFacultades.get(position) + "'", null);

                    while (cursor.moveToNext()){
                        if(!listaEdificios.contains(cursor.getString(4))){
                            listaEdificios.add(cursor.getString(4));
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*Cursor cursor = bd.rawQuery("SELECT * FROM " + SQLConstants.tableClases + " WHERE ciudad = '"
                + ciudad + "'", null);

        while (cursor.moveToNext()){
            if(!listaFacultades.contains(cursor.getString(5))){
                listaFacultades.add(cursor.getString(5));
            }
        }*/
    }

    private void consultarListaAulas(){
        final SQLiteDatabase bd = adminSQL.getReadableDatabase();
        listaAulas = new ArrayList<>();
        listaAulas.add("Select Classroom");

        comboEdificios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (position == 0){
                    comboAulas.setEnabled(false);
                } else {
                    comboAulas.setEnabled(true);
                    edificio = listaEdificios.get(position);

                    Cursor cursor;
                    cursor = bd.rawQuery("SELECT * FROM " + SQLConstants.tableClases + " WHERE ciudad = '"
                            + ciudad + "' AND facultad = '" + facultad + "' AND edificio = '"
                            + listaEdificios.get(position) + "'", null);

                    while (cursor.moveToNext()){
                        if(!listaAulas.contains(cursor.getString(6))){
                            listaAulas.add(cursor.getString(6));
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void Buscar(View view) {
        //SQLite
        try {
            DBHelper adminSQL = new DBHelper(this, SQLConstants.DB, null, 1);
            SQLiteDatabase bd = adminSQL.getReadableDatabase();
            String sql = "SELECT * FROM clases WHERE ciudad = '" + ciudad + "' AND facultad = '"
                    + facultad + "' AND edificio = '" + edificio + "' AND aula = '" + aula + "'";

            Cursor cursor = bd.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
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

    /*public void Ejecutar_busqueda(View vista)
    {
        if(indiceFacultadSeleccionada != 0 || indiceEdificioSeleccionado != 0 || indiceAulaSeleccionado != 0)
        {
            Intent i = new Intent(this, ListaAulasClase.class);

            i.putExtra("facultad", indiceFacultadSeleccionada);
            i.putExtra("edificio", indiceEdificioSeleccionado);
            i.putExtra("aula", indiceAulaSeleccionado);

            startActivity(i);

        }
        else
        {
            EditText cuadroBusqueda = (EditText)findViewById(R.id.resultado);
            Intent i = new Intent(this, BusquedaClase.class);
            String busqueda = cuadroBusqueda.getText().toString();

            i.putExtra("busqueda", busqueda);
            startActivity(i);
        }

    }*/
}




    /*Spinner listaFacultades;
    Spinner listaEdificios;
    Spinner listaAulas;
    Spinner listaCiudades;

    String[] nombreCiudades = {"Ciudades", "Milano", "Granada"};
    String[] nombresFacultadesGranada = {"Facultades", "ETSIIT", "Ciencias", "Cartuja"};
    String[] nombresFacultadesMilan = {"Facultades", "Leonardo", "Bovisa", "Unimi"};
    String[] nombresEdificiosGranada = {"Edificios", "Aulas", "Biblioteca", "Barracón"};
    String[] nombresEdificiosMilan = {"Edificios", "8", "21", "25"};
    String[] nombresAulasGranada = {"Clase", "0.1", "0.2", "1.1", "1.2", "2.1"};
    String[] nombresAulasMilan = {"Clase", "D.1.2", "F.1.1", "E.G.2", "E.G.4"};

    int indiceFacultadSeleccionada = 0;
    int indiceEdificioSeleccionado = 0;
    int indiceAulaSeleccionado = 0;
    int indiceCiudadSeleccionado = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busquedaavanzada);

        Log.i("etiqueta", "empieza");

        listaFacultades = (Spinner)findViewById(R.id.busquedaFacultad);
        listaEdificios = (Spinner)findViewById(R.id.busquedaEdificio);
        listaAulas = (Spinner)findViewById(R.id.busquedaAula);
        listaCiudades = (Spinner)findViewById(R.id.busquedaCiudades);

        ArrayAdapter<String> adaptadorCiudades = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombreCiudades);
        listaCiudades.setAdapter(adaptadorCiudades);

        ArrayAdapter<String> adaptadorFacultades = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresFacultadesGranada);
        listaFacultades.setClickable(false);
        listaFacultades.setEnabled(false);
        listaFacultades.setAdapter(adaptadorFacultades);

        ArrayAdapter<String> adaptadorEdificios = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresEdificiosGranada);
        listaEdificios.setClickable(false);
        listaEdificios.setEnabled(false);
        listaEdificios.setAdapter(adaptadorEdificios);

        ArrayAdapter<String> adaptadorAulas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresAulasGranada);
        listaAulas.setClickable(false);
        listaAulas.setEnabled(false);
        listaAulas.setAdapter(adaptadorAulas);

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Cabiar el contenido del spinner en funcion de las opciones anteriores y el de la facultad dependiendo de la ciudad del perfil
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        listaCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                int aux = indiceCiudadSeleccionado;
                indiceCiudadSeleccionado = position;

                if(aux != indiceCiudadSeleccionado)
                {
                    if(indiceCiudadSeleccionado == 0)
                    {
                        ArrayAdapter<String> adaptadorFacultades = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, nombresFacultadesGranada);
                        listaFacultades.setClickable(false);
                        listaFacultades.setEnabled(false);
                        listaFacultades.setAdapter(adaptadorFacultades);
                    }
                    else if (indiceCiudadSeleccionado == 1)
                    {
                        ArrayAdapter<String> adaptadorFacultades = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, nombresFacultadesMilan);
                        listaFacultades.setClickable(true);
                        listaFacultades.setEnabled(true);
                        listaFacultades.setAdapter(adaptadorFacultades);
                    }
                    else
                    {
                        ArrayAdapter<String> adaptadorFacultades = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, nombresFacultadesGranada);
                        listaFacultades.setClickable(true);
                        listaFacultades.setEnabled(true);
                        listaFacultades.setAdapter(adaptadorFacultades);
                    }

                    ArrayAdapter<String> adaptadorEdificios = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, nombresEdificiosGranada);
                    listaEdificios.setClickable(false);
                    listaEdificios.setEnabled(false);
                    listaEdificios.setAdapter(adaptadorEdificios);

                    ArrayAdapter<String> adaptadorAulas = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, nombresAulasGranada);
                    listaAulas.setClickable(false);
                    listaAulas.setEnabled(false);
                    listaAulas.setAdapter(adaptadorAulas);

                    indiceFacultadSeleccionada = 0;
                    indiceEdificioSeleccionado = 0;
                    indiceAulaSeleccionado = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        listaFacultades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indiceFacultadSeleccionada = position;

                if(indiceFacultadSeleccionada == 0)
                {
                    ArrayAdapter<String> adaptadorEdificios = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, nombresEdificiosGranada);
                    listaEdificios.setClickable(false);
                    listaEdificios.setEnabled(false);
                    listaEdificios.setAdapter(adaptadorEdificios);
                }
                else if (indiceFacultadSeleccionada == 1)
                {
                    if(indiceCiudadSeleccionado == 1)
                    {
                        ArrayAdapter<String> adaptadorEdificios = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, nombresEdificiosMilan);
                        listaEdificios.setClickable(true);
                        listaEdificios.setEnabled(true);
                        listaEdificios.setAdapter(adaptadorEdificios);
                    }
                    else if (indiceCiudadSeleccionado == 2)
                    {
                        ArrayAdapter<String> adaptadorEdificios = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, nombresEdificiosGranada);
                        listaEdificios.setClickable(true);
                        listaEdificios.setEnabled(true);
                        listaEdificios.setAdapter(adaptadorEdificios);
                    }
                }

                ArrayAdapter<String> adaptadorAulas = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, nombresAulasGranada);
                listaAulas.setClickable(false);
                listaAulas.setEnabled(false);
                listaAulas.setAdapter(adaptadorAulas);

                indiceEdificioSeleccionado = 0;
                indiceAulaSeleccionado = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        listaEdificios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indiceEdificioSeleccionado = position;

                if(indiceEdificioSeleccionado == 0)
                {
                    ArrayAdapter<String> adaptadorAulas = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, nombresAulasGranada);
                    listaAulas.setClickable(false);
                    listaAulas.setEnabled(false);
                    listaAulas.setAdapter(adaptadorAulas);
                }
                else
                {
                    if(indiceCiudadSeleccionado == 1)
                    {
                        if (indiceFacultadSeleccionada == 1)
                        {
                            if(indiceFacultadSeleccionada == 1)
                            {
                                ArrayAdapter<String> adaptadorAulas = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, nombresAulasMilan);
                                listaAulas.setClickable(true);
                                listaAulas.setEnabled(true);
                                listaAulas.setAdapter(adaptadorAulas);
                            }
                        }
                    }
                    else if (indiceCiudadSeleccionado == 2)
                    {
                        if (indiceFacultadSeleccionada == 1)
                        {
                            if(indiceFacultadSeleccionada == 1)
                            {
                                ArrayAdapter<String> adaptadorAulas = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, nombresAulasGranada);
                                listaAulas.setClickable(true);
                                listaAulas.setEnabled(true);
                                listaAulas.setAdapter(adaptadorAulas);
                            }
                        }
                    }
                }

                indiceAulaSeleccionado = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        listaAulas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                indiceAulaSeleccionado = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }




    public void Ejecutar_busqueda(View vista)
    {
        if(indiceFacultadSeleccionada != 0 || indiceEdificioSeleccionado != 0 || indiceAulaSeleccionado != 0)
        {
            Intent i = new Intent(this, ListaAulasClase.class);

            i.putExtra("facultad", indiceFacultadSeleccionada);
            i.putExtra("edificio", indiceEdificioSeleccionado);
            i.putExtra("aula", indiceAulaSeleccionado);

            startActivity(i);

        }
        else
        {
            EditText cuadroBusqueda = (EditText)findViewById(R.id.resultado);
            Intent i = new Intent(this, BusquedaClase.class);
            String busqueda = cuadroBusqueda.getText().toString();

            i.putExtra("busqueda", busqueda);
            startActivity(i);
        }

    }



    public void Volver(View vista)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}*/
