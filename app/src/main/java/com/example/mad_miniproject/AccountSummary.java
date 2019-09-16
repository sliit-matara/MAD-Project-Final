package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AccountSummary extends AppCompatActivity {

    private Spinner spnCurrency,spnAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_summary);

        spnCurrency = (Spinner) findViewById(R.id.spnCurrency);
        spnAccount = (Spinner) findViewById(R.id.spnFrom);

        ArrayAdapter<String> currencyAdapter = new ArrayAdapter<String>(AccountSummary.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.currency));

        currencyAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnCurrency.setAdapter(currencyAdapter);
    }
}
