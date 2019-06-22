package com.findmyclass.findclass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MostrarClase extends AppCompatActivity {

    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_clase);

        datos = getIntent().getExtras();
        TextView mostrarClase = findViewById(R.id.textView);
        String datosObtenidos = "Asignatura: " + datos.getString("asignatura") + "\n"
                    + "Dia: " + datos.getString("dia") + "\n"
                    + "Horario: " + datos.getString("horario") + "\n"
                    + "Direccion: " + datos.getString("direccion") + "\n"
                    + "Planta: " + datos.getString("planta") + "\n"
                    + "Edificio: " + datos.getString("edificio") + "\n"
                    + "Facultad: " + datos.getString("facultad") + "\n"
                    + "Aula: " + datos.getString("aula") + "\n"
                    + "Pais: " + datos.getString("pais") + "\n"
                    + "Ciudad: " + datos.getString("ciudad") + "\n";

        mostrarClase.setText(datosObtenidos);

    }
}
