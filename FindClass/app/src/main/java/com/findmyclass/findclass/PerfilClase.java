package com.findmyclass.findclass;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PerfilClase extends Activity
{
    Spinner listaCiudades;

    String[] nombresCiudades = {"Ciudades", "Granada", "Sevilla", "Milán"};

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        listaCiudades = (Spinner)findViewById(R.id.busquedaAula);

        ArrayAdapter<String> adaptadorFacultades = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresCiudades);
        listaCiudades.setAdapter(adaptadorFacultades);
    }
}
