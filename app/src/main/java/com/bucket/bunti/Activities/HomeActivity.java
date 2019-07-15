package com.bucket.bunti.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bucket.bunti.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private TextView userEmail, userName,longitud, latitud, direccion;
    private Button btnLogout;

    //FIREBASE
    private FirebaseAuth oAuth;
    private FirebaseUser userCurrent;

    //LOCATE
    private LocationManager ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        localizacion();
        registrarLocalizacion();

        userName  = findViewById(R.id.homeUsername);
        userEmail = findViewById(R.id.homeUseremail);
        btnLogout = findViewById(R.id.btnLogout);

        oAuth = FirebaseAuth.getInstance();
        userCurrent = oAuth.getCurrentUser();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
                finish();
            }
        });
    }

    private void localizacion() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            }, 1000);
        }

        longitud = (TextView) findViewById(R.id.txtLongitud);
        latitud = (TextView) findViewById(R.id.txtLatitud);
        direccion = (TextView) findViewById(R.id.txtDireccion);

        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (ubicacion != null) {
            Log.d("Latitud", String.valueOf(loc.getLatitude()));
            Log.d("Longitud", String.valueOf(loc.getLongitude()));
            longitud.setText("LONGITUD: " + String.valueOf(loc.getLongitude()));
            latitud.setText("LATITUD: " + String.valueOf(loc.getLatitude()));
        }
    }

    private void listaProviders() {
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> listaProvider = ubicacion.getAllProviders();

        String mejorProvider = ubicacion.getBestProvider(mejorCriterio(), false);
        System.out.println(mejorProvider);

        LocationProvider provider = ubicacion.getProvider(listaProvider.get(0));
        System.out.println(provider.getAccuracy());
        System.out.println(provider.getPowerRequirement());
        System.out.println(provider.supportsAltitude());
    }

    private Criteria mejorCriterio() {
        Criteria requerimiento = new Criteria();
        requerimiento.setAccuracy(Criteria.ACCURACY_FINE);
        requerimiento.setAltitudeRequired(true);
        return requerimiento;
    }

    private boolean estadoGPS() {
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!ubicacion.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d("GPS", "NO ACTIVADO");
        } else {
            Log.d("GPS", "ACTIVADO");
        }
        return true;
    }

    private void registrarLocalizacion() {
        ubicacion = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        ubicacion.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, new miLocalizacionListener());
    }

    private class miLocalizacionListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            String lat = "Mi latitud es: " + location.getLatitude();
            String lon = "Mi longitud es: " + location.getLongitude();

            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            try {
                List<Address> direccion1 = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                System.out.println("Direccion: " + direccion1.get(0).getAddressLine(0));
                System.out.println(direccion1.get(0).getCountryName() + "----------------------------------------------------------------------------------");
                System.out.println(direccion1.get(0).getLocality() + "----------------------------------------------------------------------------------");
                System.out.println(direccion1.get(0).getPostalCode() + "----------------------------------------------------------------------------------");
                direccion.setText(direccion1.get(0).getAddressLine(0));
            } catch (IOException e) {
                e.printStackTrace();
            }

            latitud.setText(lat);
            longitud.setText(lon);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
