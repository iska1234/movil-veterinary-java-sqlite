package com.example.proyectoclinicaveterinaria;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.proyectoclinicaveterinaria.databinding.ActivityUbicacionMapsBinding;

public class UbicacionMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
private ActivityUbicacionMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityUbicacionMapsBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng losolivos = new LatLng(-11.9426427, -77.0135672);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.hamster1)).position(losolivos).title("Sede en Los Olivos").snippet("Horario de atencion: 8:00am-8:00pm"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(losolivos));
        //
        LatLng comas = new LatLng(-11.9159079, -77.0406608);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.hamster2)).position(comas).title("Sede en Comas").snippet("PHorario de atencion: 9:30am-10:00pm"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(comas));
        //
        LatLng carabayllo = new LatLng(-11.8692079, -77.0178675);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.perro1)).position(carabayllo).title("Sede en Carabayllo").snippet("Horario de atencion: 9:00am-8:30pm"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(carabayllo));
        //
        LatLng sjl = new LatLng(-11.9722971, -77.0037067);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.perro2)).position(sjl).title("Sede en San Juan de Lurigancho").snippet("Horario de atencion: 7:00am-6:00pm"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sjl));


        mMap.setMaxZoomPreference(13);
        mMap.setMinZoomPreference(13);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}