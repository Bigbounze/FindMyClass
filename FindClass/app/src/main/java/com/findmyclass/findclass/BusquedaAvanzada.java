package com.findmyclass.findclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class BusquedaAvanzada extends Activity
{
    Spinner listaFacultades;
    Spinner listaEdificios;
    Spinner listaAulas;
    Spinner listaCiudades;

    String[] nombreCiudades = {"Ciudades", "Milán", "Granada"};
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
}
