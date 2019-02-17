package com.findmyclass.findclass;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    Spinner listaFacultades;
    Spinner listaEdificios;
    Spinner listaAulas;

    String[] nombresFacultades = {"Facultades", "UGR", "US", "Polimi"};
    String[] nombresEdificios = {"Edificios", "0", "1", "2"};
    String[] nombresAulas = {"Aulas", "0", "1"};

    int indiceFacultadSeleccionada = 0;
    int indiceEdificioSeleccionado = 0;
    int indiceAulaSeeccionado = 0;

    DatabaseHelper db;

    ListView aulaList;

    ArrayList<String> listItems;
    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        listaFacultades = (Spinner)findViewById(R.id.busquedaFacultad);
        listaEdificios = (Spinner)findViewById(R.id.busquedaEdificio);
        listaAulas = (Spinner)findViewById(R.id.busquedaAula);

        ArrayAdapter<String> adaptadorFacultades = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresFacultades);
        listaFacultades.setAdapter(adaptadorFacultades);

        ArrayAdapter<String> adaptadorEdificios = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresEdificios);
        listaEdificios.setAdapter(adaptadorEdificios);

        ArrayAdapter<String> adaptadorAulas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresAulas);
        listaAulas.setAdapter(adaptadorAulas);

        listItems = new ArrayList<>();

        //aulaList = findViewById((R.id.));

        viewData();

        listaAulas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = listaAulas.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, ""+text, Toast.LENGTH_SHORT).show();
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Cambiar el contenido del spinner en funcion de las opciones anteriores y el de la facultad dependiendo de la ciudad del perfil
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
        listaFacultades.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                indiceFacultadSeleccionada = position;
            }
        });

        listaEdificios.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                indiceEdificioSeleccionado = position;
            }
        });

        listaAulas.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                indiceAulaSeeccionado = position;
            }
        });
*/
    }

    private void viewData(){
        Cursor cursor = db.viewData();

        if(cursor.getCount() == 0 ){
            Toast.makeText(this,"No data to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                listItems.add(cursor.getString(1));
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
            listaAulas.setAdapter(adapter);
        }
    }

    public void ejecutar_busqueda(View vista)
    {
        if(indiceFacultadSeleccionada != 0 || indiceEdificioSeleccionado != 0 || indiceAulaSeeccionado != 0)
        {
            Intent i = new Intent(this, ListaAulasClase.class);

            i.putExtra("facultad", indiceFacultadSeleccionada);
            i.putExtra("edificio", indiceEdificioSeleccionado);
            i.putExtra("aula", indiceAulaSeeccionado);

            startActivity(i);

        }
        else
        {
            EditText cuadroBusqueda = (EditText)findViewById(R.id.textoBusqueda);
            Intent i = new Intent(this, BusquedaClase.class);
            String busqueda = cuadroBusqueda.getText().toString();

            i.putExtra("busqueda", busqueda);
            startActivity(i);
        }

    }



    public void ejecutar_perfil(View vista)
    {
        Intent i = new Intent(this, PerfilClase.class);

        startActivity(i);
    }



    public void ejecutar_horario(View vista)
    {
        Intent i = new Intent(this, HorarioClase.class);

        startActivity(i);
    }



    public void ejecutar_configuracion(View vista)
    {
        Intent i = new Intent(this, ConfiguracionClase.class);

        startActivity(i);
    }



    public void ejecutar_info(View vista)
    {
        Intent i = new Intent(this, InfoClase.class);

        startActivity(i);
    }



    public void salirApp(View view)
    {
        finish();
    }



    public boolean onCreateOptionsMenu(Menu mimenu)
    {
        getMenuInflater().inflate(R.menu.menu_ajustes, mimenu);

        return true;
    }



    public boolean onOptionsItemSelected (MenuItem opcion_menu)
    {
        int id = opcion_menu.getItemId();

        if(id == R.id.perfil)
        {
            ejecutar_perfil(null);
            return true;
        }
        else if(id == R.id.horario)
        {
            ejecutar_horario(null);
            return true;
        }
        else if(id == R.id.busqueda_avanzada)
        {
            return true;
        }
        else if(id == R.id.configuracion)
        {
            ejecutar_configuracion(null);
            return true;
        }
        else if (id == R.id.info)
        {
            ejecutar_info(null);

            return true;
        }

        return super.onOptionsItemSelected(opcion_menu);
    }
}