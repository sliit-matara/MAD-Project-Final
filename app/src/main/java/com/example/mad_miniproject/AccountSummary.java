package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.ArrayList;

public class AccountSummary extends AppCompatActivity {

    Spinner spnCurrency,spnAccountList;
    TextView txtBalAmount;
    String nic,accNo,currency;
    DBHelper dbHelper;
    Double accBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_summary);

        dbHelper = new DBHelper(this);

        spnCurrency = (Spinner) findViewById(R.id.spnCurrency);
        spnAccountList = (Spinner) findViewById(R.id.spnAccount);
        txtBalAmount = findViewById(R.id.txtAmountBalance);

        Intent intent = getIntent();
        nic = intent.getStringExtra(Account_Main.NIC_NUMBER);

        ArrayAdapter<String> currencyAdapter = new ArrayAdapter<String>(AccountSummary.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.currency));

        currencyAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnCurrency.setAdapter(currencyAdapter);

        ArrayList<Integer> accNumbers =new ArrayList<>();

        accNumbers = dbHelper.getAccount(nic);

        ArrayAdapter<Integer> accountAdapter = new ArrayAdapter<Integer>(AccountSummary.this, android.R.layout.simple_list_item_1,
                accNumbers);

        accountAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnAccountList.setAdapter(accountAdapter);

        spnAccountList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                accNo = spnAccountList.getSelectedItem().toString();
                showBalance();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spnCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currency = spnCurrency.getSelectedItem().toString();
                showCurrency();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void showBalance(){
        ArrayList<Double> balance = new ArrayList<>();

        balance = dbHelper.showBalance(accNo);

        String bal = Double.toString(balance.get(0));

        accBalance = Double.parseDouble(bal);
        txtBalAmount.setText(bal);
    }

    private void showCurrency(){
        double cur;
        if(currency.equals("USD")){
            cur = accBalance/180.00;
            cur = Math.round(cur*100.0)/100.0;
            txtBalAmount.setText(Double.toString(cur));
        }else if(currency.equals("EUR")){
            cur = accBalance/200.00;
            cur = Math.round(cur*100.0)/100.0;
            txtBalAmount.setText(Double.toString(cur));
        }else if(currency.equals("LKR")){
            txtBalAmount.setText(Double.toString(accBalance));
        }else if(currency.equals("AUD")){
            cur = accBalance/125.00;
            cur = Math.round(cur*100.0)/100.0;
            txtBalAmount.setText(Double.toString(cur));
        }else if(currency.equals("BGP")){
            cur = accBalance/225.00;
            cur = Math.round(cur*100.0)/100.0;
            txtBalAmount.setText(Double.toString(cur));
        }
    }

}
