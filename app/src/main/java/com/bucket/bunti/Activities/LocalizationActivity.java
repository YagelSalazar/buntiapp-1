package com.bucket.bunti.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bucket.bunti.R;

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
                updateUI("payment");
            }
        });
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
}
