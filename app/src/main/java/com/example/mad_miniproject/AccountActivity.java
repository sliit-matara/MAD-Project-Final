package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {

    public Spinner spnAcc;
    String nic;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        dbHelper = new DBHelper(this);

        spnAcc = findViewById(R.id.spnAccount);

        Intent intent = getIntent();
        nic = intent.getStringExtra(Account_Main.NIC_NUMBER);

        ArrayList<Integer> accNumbers =new ArrayList<>();

        accNumbers = dbHelper.getAccount(nic);

        ArrayAdapter<Integer> accountAdapter = new ArrayAdapter<Integer>(AccountActivity.this,android.R.layout.simple_list_item_1,
                accNumbers);

        accountAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnAcc.setAdapter(accountAdapter);
    }
}
