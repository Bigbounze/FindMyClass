package com.findmyclass.findclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BusquedaClase extends Activity
{
    int[] idIncluidos = new int[10];
    int pos;
    int numeroAsignaturas;
    int id;
    int idUniversidad;
    int numeroAulas;

    String nombreAsignatura;
    String[] descripciones;

    double[] latitudes;
    double[] longitudes;

    Bundle datos;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda);

        datos = getIntent().getExtras();
        /*String datosObtenidos = "Asignatura: " + datos.getString("asignatura") + "\n"
                + "Direccion: " + datos.getString("direccion") + "\n"
                + "Planta: " + datos.getString("planta") + "\n"
                + "Edificio: " + datos.getString("edificio") + "\n"
                + "Facultad: " + datos.getString("facultad") + "\n"
                + "Aula: " + datos.getString("aula") + "\n"
                + "Pais: " + datos.getString("pais") + "\n"
                + "Ciudad: " + datos.getString("ciudad") + "\n";*/

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
        TextView mostrarClase = findViewById(R.id.infoHorario);
        mostrarClase.setText(datosObtenidos);

        /*Bundle datos = getIntent().getExtras();

        String busqueda = datos.getString("busqueda");
        busqueda = busqueda.toUpperCase();
        numeroAsignaturas = datos.getInt("numeroAsignaturas");
        idIncluidos = datos.getIntArray("idIncluidos");

        TextView asignatura = (TextView)findViewById(R.id.asignatura);
        TextView infoHorario = (TextView)findViewById(R.id.infoHorario);

        boolean incluido = false;
        boolean encontrado = false;
        id = 0;

        nombreAsignatura = busqueda;

        if(busqueda.equals("EOA"))
        {
            encontrado = true;
            id = 1;
            asignatura.setText("" + busqueda);
            infoHorario.setText("Lunes: 10:30~12:30 - F.1.1 Edificio 8 Planta: 1 \n Viernes: 8:30~12:30 - D.1.2 Edificio 25 Planta: 1");

            numeroAulas = 2;
            idUniversidad = 1;

            descripciones = new String[numeroAulas];
            latitudes = new double[numeroAulas];
            longitudes = new double[numeroAulas];

            descripciones[0] = "Lunes: 10:30~12:30 - F.1.1 Edificio 8 Planta: 1";
            latitudes[0] = 45.478878;
            longitudes[0] = 9.229413;
            descripciones[1] = "Viernes: 8:30~12:30 - D.1.2 Edificio 25 Planta: 1";
            latitudes[1] = 45.477281;
            longitudes[1] = 9.235106;
        }
        else if(busqueda.equals("WI"))
        {
            encontrado = true;
            id = 2;
            idUniversidad = 1;
            asignatura.setText("" + busqueda);
            infoHorario.setText("Miércoles: 8:30~10:30 - E.G.2 Edificio 21 Planta: -1  \n Jueves: 11:30~13:30 - E.G.4 Edificio 21 Planta: -1");

            numeroAulas = 2;

            descripciones = new String[numeroAulas];
            latitudes = new double[numeroAulas];
            longitudes = new double[numeroAulas];

            descripciones = new String[numeroAulas];
            descripciones[0] = "Miércoles: 8:30~10:30 - E.G.2 Edificio 21 Planta: -1";
            latitudes[0] = 45.479066;
            longitudes[0] = 9.233579;
            descripciones[1] = "Jueves: 11:30~13:30 - E.G.4 Edificio 21 Planta: -1";
            latitudes[1] = 45.479107;
            longitudes[1] = 9.233952;
        }
        else if(busqueda.equals("FP"))
        {
            encontrado = true;
            id = 3;
            idUniversidad = 2;
            asignatura.setText("" + busqueda);
            infoHorario.setText("Lunes: 15:30~17:30 - 2.1 Edificio: Aulas - Plata: 2");

            numeroAulas = 1;

            descripciones = new String[numeroAulas];
            latitudes = new double[numeroAulas];
            longitudes = new double[numeroAulas];

            descripciones = new String[numeroAulas];
            descripciones[0] = "Lunes: 15:30~17:30 - 2.1 Edificio: Aulas - Plata: 2";
            latitudes[0] = 37.197319;
            longitudes[0] = -3.624315;

        }
        else
        {
            asignatura.setText("");
            infoHorario.setText("No se ha encontrado resultado alguno.");
        }

        if(numeroAsignaturas != 0)
        {
            for(int i = 0; i < numeroAsignaturas; i++)
            {
                if(!incluido)
                {
                    if(idIncluidos[i] == id)
                    {
                        incluido = true;
                        pos = i;
                    }
                }
            }
        }
        else
        {
            incluido = false;
        }

        if (!encontrado)
        {
            Button boton = (Button) findViewById(R.id.aniadir);
            boton.setEnabled(false);

            Button botonQuitar = (Button) findViewById(R.id.quitar);
            botonQuitar.setEnabled(false);

            Button botonMapa = (Button) findViewById(R.id.mapa);
            botonMapa.setEnabled(false);
        }
        else if(incluido)
        {
            Button boton = (Button) findViewById(R.id.aniadir);
            boton.setEnabled(false);

            Button botonQuitar = (Button) findViewById(R.id.quitar);
            botonQuitar.setEnabled(true);

            Button botonMapa = (Button) findViewById(R.id.mapa);
            botonMapa.setEnabled(true);
        }
        else
        {
            Button boton = (Button) findViewById(R.id.aniadir);
            boton.setEnabled(true);

            Button botonQuitar = (Button) findViewById(R.id.quitar);
            botonQuitar.setEnabled(false);

            Button botonMapa = (Button) findViewById(R.id.mapa);
            botonMapa.setEnabled(true);
        }*/
    }



    public void Aniadir(View vista)
    {
        idIncluidos[numeroAsignaturas] = id;
        numeroAsignaturas++;

        Button boton = (Button) findViewById(R.id.aniadir);
        boton.setEnabled(false);

        Button botonQuitar = (Button) findViewById(R.id.quitar);
        botonQuitar.setEnabled(true);
    }



    public void Quitar(View vista)
    {
        int aux = pos - 1;

        if(aux != numeroAsignaturas)
        {
            for(int i = pos; i < numeroAsignaturas - 1; i++)
            {
                idIncluidos[i] = idIncluidos[i+1];
            }
        }
        numeroAsignaturas--;

        Button boton = (Button) findViewById(R.id.aniadir);
        boton.setEnabled(true);

        Button botonQuitar = (Button) findViewById(R.id.quitar);
        botonQuitar.setEnabled(false);
    }



    public void onBackPressed()
    {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("numeroAsignaturas", numeroAsignaturas);
        i.putExtra("idIncluidos", idIncluidos);
        startActivity(i);
    }



    public void AbrirMapa(View vista)
    {
        Intent i = new Intent(this, MapaUniversidad.class);
        //i.putExtra("latitudes", datos.getDouble());
        i.putExtra("longitudes", datos.getDouble("longitud"));
        i.putExtra("latitudes", datos.getDouble("latitud"));
        i.putExtra("nombreAsignatura", datos.getString("asignatura"));
        i.putExtra("nombreAula", datos.getString("aula"));

        //i.putExtra("nombreAsignatura", nombreAsignatura);
        //i.putExtra("descripciones", descripciones);
        //i.putExtra("numeroAulas", numeroAulas);

        startActivity(i);
    }
}
