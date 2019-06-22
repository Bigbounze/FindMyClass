package com.findmyclass.findclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    int[] idIncluidos = new int[10];
    int numeroAsignaturas = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle datos = getIntent().getExtras();

        if(datos != null)
        {
            numeroAsignaturas = datos.getInt("numeroAsignaturas");
            idIncluidos = datos.getIntArray("idIncluidos");
        }
    }



    public void Ejecutar_busqueda(View vista)
    {
        EditText cuadroBusqueda = findViewById(R.id.resultado);
        Intent i = new Intent(this, BusquedaClase.class);
        String busqueda = cuadroBusqueda.getText().toString();

        i.putExtra("busqueda", busqueda);
        i.putExtra("numeroAsignaturas", numeroAsignaturas);
        i.putExtra("idIncluidos", idIncluidos);
        startActivity(i);
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