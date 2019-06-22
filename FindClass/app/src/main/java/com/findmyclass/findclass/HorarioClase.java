package com.findmyclass.findclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HorarioClase extends Activity
{
    int[] idIncluidos = new int[10];
    int numeroAsignaturas = 0;

    protected void onCreate(Bundle savedInstanceState)
    {
        String infoLunes = "Lunes: ";
        String infoMartes = "Martes: ";
        String infoMiercoles = "Miércoles: ";
        String infoJueves = "Jueves: ";
        String infoViernes = "Viernes: ";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario);

        Bundle datos = getIntent().getExtras();

        numeroAsignaturas = datos.getInt("numeroAsignaturas");
        idIncluidos = datos.getIntArray("idIncluidos");


        if(numeroAsignaturas != 0)
        {
            for(int i = 0; i < numeroAsignaturas; i++)
            {
                if(idIncluidos[i] == 1)
                {
                    infoLunes += " 10:30~12:30 - F.1.1 Edificio 8 Planta: 1 \n";
                    infoViernes += " 8:30~12:30 - D.1.2 Edificio 25 Planta: 1 \n";
                }
                else if(idIncluidos[i] == 2)
                {
                    infoJueves += " 11:30~13:30 - E.G.4 Edificio 21 Planta: -1 \n";
                    infoMiercoles += " 8:30~10:30 - E.G.2 Edificio 21 Planta: -1 \n";
                }
                else
                {
                    infoLunes += " 15:30~17:30 - 2.1 Edificio: Aulas - Plata: 2 \n";
                }
            }

        }

        TextView textoLunes = (TextView) findViewById(R.id.Lunes);
        textoLunes.setText(infoLunes);
        TextView textoMartes = (TextView) findViewById(R.id.Martes);
        textoMartes.setText(infoMartes);
        TextView textoMiercoles = (TextView) findViewById(R.id.Miercoles);
        textoMiercoles.setText(infoMiercoles);
        TextView textoJueves = (TextView) findViewById(R.id.Jueves);
        textoJueves.setText(infoJueves);
        TextView textoViernes = (TextView) findViewById(R.id.Viernes);
        textoViernes.setText(infoViernes);
    }



    public void Volver(View vista)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
