package com.findmyclass.findclass;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListaAulasClase extends Activity
{
    String[] prueba1 = {"prueba1", "prueba2", "prueba3", "prueba4", "prueba5", "prueba6", "prueba7", "prueba8"}; //lista edificios
    String[] prueba2 = {"prueba11", "prueba12", "prueba13", "prueba14", "prueba15", "prueba16", "prueba17", "prueba18"}; //lista aulas
    ListView lista;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_clases);

        Bundle datos = getIntent().getExtras();

        int facultad = datos.getInt("facultad");
        int edificio = datos.getInt("edificio");
        int aula = datos.getInt("aula");

        if(facultad != 0)
        {
            if(edificio != 0)
            {
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, prueba2);
                lista.setAdapter(adaptador);
            }
            else
            {
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, prueba1);
                lista.setAdapter(adaptador);
            }
        }
        else if (edificio != 0)
        {
            if(aula != 0)
            {
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, prueba2);
                lista.setAdapter(adaptador);
            }
            else
            {
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, prueba2);
                lista.setAdapter(adaptador);
            }
        }
        else
        {
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, prueba2);
            lista.setAdapter(adaptador);
        }

    }
}
