package com.bucket.bunti.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bucket.bunti.R;

public class CheckPayment extends AppCompatActivity {

    private TextView reciptClient, reciptDate, reciptAddress,reciptPayment;
    private Button btnFinishRequest;
    private Intent uiMenu;
    private String client,date,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_payment);

        reciptClient  = findViewById(R.id.reciptClient);
        reciptDate    = findViewById(R.id.reciptDate);
        reciptAddress = findViewById(R.id.reciptAddress);
        reciptPayment = findViewById(R.id.reciptPayment);
        btnFinishRequest = findViewById(R.id.btnFinishRequest);
        uiMenu = new Intent(this,MenuActivity.class);

        setRecipt();

        btnFinishRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI("menu");
            }
        });

    }

    private void setRecipt(){
        if(getIntent().getExtras().getString("client") != null &&
                getIntent().getExtras().getString("date") != null &&
                getIntent().getExtras().getString("address") != null &&
                getIntent().getExtras().getString("payment") != null) {
            reciptClient.setText("Cliente: "+getIntent().getExtras().getString("client"));
            reciptDate.setText("Fecha y hora: "+getIntent().getExtras().getString("date"));
            reciptAddress.setText("Dirección: "+getIntent().getExtras().getString("address"));
            reciptPayment.setText("Forma de pago: "+getIntent().getExtras().getString("payment"));
        } else {
            reciptClient.setText("No se pudo cargar, intenta maás tarde.");
            reciptAddress.setVisibility(View.INVISIBLE);
            reciptDate.setVisibility(View.INVISIBLE);
            reciptPayment.setVisibility(View.INVISIBLE);
        }
    }

    private void updateUI(String activity){
        switch (activity){
            case "menu":
                startActivity(uiMenu);
                break;
        }
        finish();
    }
}
