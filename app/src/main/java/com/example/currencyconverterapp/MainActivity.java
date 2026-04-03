package com.example.currencyconverterapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    EditText input;
    Spinner from, to;
    Button convert, settingsBtn;
    TextView result;

    String[] currencies = {"INR", "USD", "EUR", "JPY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.inputAmount);
        from = findViewById(R.id.fromCurrency);
        to = findViewById(R.id.toCurrency);
        convert = findViewById(R.id.convertBtn);
        result = findViewById(R.id.result);
        settingsBtn = findViewById(R.id.settingsBtn);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                currencies
        );

        from.setAdapter(adapter);
        to.setAdapter(adapter);

        convert.setOnClickListener(v -> convertCurrency());

        // 🔥 SETTINGS BUTTON FIXED
        settingsBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
    }

    private void convertCurrency() {

        if (input.getText().toString().isEmpty()) {
            result.setText("Enter amount");
            return;
        }

        double amount = Double.parseDouble(input.getText().toString());

        String fromC = from.getSelectedItem().toString();
        String toC = to.getSelectedItem().toString();

        double rate = 1;

        if (fromC.equals("INR") && toC.equals("USD")) rate = 0.012;
        else if (fromC.equals("USD") && toC.equals("INR")) rate = 83;
        else if (fromC.equals("INR") && toC.equals("EUR")) rate = 0.011;
        else if (fromC.equals("EUR") && toC.equals("INR")) rate = 90;
        else if (fromC.equals("INR") && toC.equals("JPY")) rate = 1.8;
        else if (fromC.equals("JPY") && toC.equals("INR")) rate = 0.55;

        double resultValue = amount * rate;

        result.setText("Result: " + resultValue);
    }
}