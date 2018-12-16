package com.findmyclass.findclass;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BusquedaClase extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda);

        Bundle datos = getIntent().getExtras();

        String busqueda = datos.getString("busqueda");
        //int busqueda = datos.getInt("numero1");

        TextView textoBuscado = (TextView)findViewById(R.id.textoBusqueda);

        textoBuscado.setText("" + busqueda);
    }
}
