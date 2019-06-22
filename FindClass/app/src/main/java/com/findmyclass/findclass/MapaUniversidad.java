package com.findmyclass.findclass;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaUniversidad extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    //double[] latitudes, longitudes;
    double latitudes, longitudes;

    String nombreAsignatura;
    String nombreAula;
    String[] descripciones;

    int numeroAulas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle datos = getIntent().getExtras();

        //numeroAulas = datos.getInt("numeroAulas");
        //descripciones = datos.getStringArray("descripciones");
        nombreAsignatura = datos.getString("nombreAsignatura");
        nombreAula = datos.getString("nombreAula");
        //latitudes = datos.getDoubleArray("latitudes");
        //longitudes = datos.getDoubleArray("longitudes");
        latitudes = datos.getDouble("latitudes");
        longitudes = datos.getDouble("longitudes");

        setContentView(R.layout.activity_mapa_universidad);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        LatLng busqueda = new LatLng(latitudes, longitudes);
        //mMap.addMarker(new MarkerOptions().position(busqueda).title(nombreAsignatura).snippet(descripciones[0]));
        mMap.addMarker(new MarkerOptions().position(busqueda).title(nombreAsignatura).snippet(nombreAula));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(busqueda));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(busqueda, 18));

        /*if(numeroAulas == 1)
        {
            LatLng busqueda = new LatLng(latitudes[0], longitudes[0]);
            mMap.addMarker(new MarkerOptions().position(busqueda).title(nombreAsignatura).snippet(descripciones[0]));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(busqueda));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(busqueda, 18), 5000, null);
        }
        else
        {
            LatLng[] busquedas = new LatLng[numeroAulas];

            for(int i = 0; i < numeroAulas; i++)
            {
                busquedas[i] = new LatLng(latitudes[i], longitudes[i]);
                mMap.addMarker(new MarkerOptions().position(busquedas[i]).title(nombreAsignatura).snippet(descripciones[i]));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(busquedas[0]));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(busquedas[0], 18), 5000, null);
        }*/

        mMap.getMaxZoomLevel();

    }
}
