package com.bucket.bunti.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bucket.bunti.R;

public class MethodPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_payment);
        Button btnCash = (Button) findViewById(R.id.buttonCash);
        Button btnCreditCard = (Button) findViewById(R.id.buttonCreditCard);
        Button btnPaypal = (Button) findViewById(R.id.buttonPayPal);

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI();
            }
        });

        btnCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI();
            }
        });

        btnPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI();
            }
        });
    }

    private void updateUI(){
        Intent intentSatisfactoryPayment = new Intent(getApplicationContext(), SatisfactoryPaymentActivity.class);
        startActivityForResult(intentSatisfactoryPayment,0);
        finish();
    }
}
