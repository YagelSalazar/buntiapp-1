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
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bucket.bunti.Helpers.SharedPreferencesProject;
import com.bucket.bunti.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocalizationActivity extends AppCompatActivity {

    private TextView notNow;
    private Button btnPaymentScreen;
    private Intent paymentScreen, menuScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localization_screen);

        notNow = findViewById(R.id.textViewNotNow);
        btnPaymentScreen = findViewById(R.id.buttonYes);
        menuScreen  = new Intent(this,MenuActivity.class);
        paymentScreen  = new Intent(this,MethodPaymentActivity.class);


        notNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI("menu");
            }
        });

        btnPaymentScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationStart();
            }
        });
    }

    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setLocActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateUI("payment");
                return;
            } else {
                updateUI("menu");
            }
        }
    }

    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        LocalizationActivity locActivity;

        public LocalizationActivity getLocActivity() {
            return locActivity;
        }

        public void setLocActivity(LocalizationActivity locActivity) {
            this.locActivity = locActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
//            this.locActivity.setLocation(loc);
        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }

    private void showMessage(String message){
        // Method for toast messages
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void updateUI(String activity){
        switch (activity){
            case "menu":
                startActivity(menuScreen);
                break;

            case "payment":
                startActivity(paymentScreen);
                break;
        }
        finish();
    }

    @Override
    protected void onStart(){
        super.onStart();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            updateUI("payment");
        }
    }
}
