package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.ArrayList;

public class MoneyTransOwnAccount extends AppCompatActivity implements View.OnClickListener {

    private Button btnTransfer;
    String nic;
    DBHelper dbHelper;
    public Spinner spnFrom,spnTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_trans_own_account);

        dbHelper = new DBHelper(this);

        spnFrom = findViewById(R.id.spnFrom);
        spnTo = findViewById(R.id.spnTo);

        Intent intent = getIntent();
        nic = intent.getStringExtra(MoneyTransMain.USERNIC);

        ArrayList<Integer> accNumbers =new ArrayList<>();

        accNumbers = dbHelper.getAccount(nic);

        ArrayAdapter<Integer> accAdapter = new ArrayAdapter<Integer>(MoneyTransOwnAccount.this,android.R.layout.simple_list_item_1,
                accNumbers);

        accAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnFrom.setAdapter(accAdapter);
        spnTo.setAdapter(accAdapter);

        btnTransfer = (Button) findViewById(R.id.btnTransfer);

        btnTransfer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnTransfer){
            Intent success = new Intent(this,MoneyTransSuccess.class);
            startActivity(success);
        }
    }
}
