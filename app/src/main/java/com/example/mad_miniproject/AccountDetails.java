package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.ArrayList;

public class AccountDetails extends AppCompatActivity {

    Spinner account;
    DBHelper dbHelper;
    String nic,accNo;
    TextView accountNo,type,openDate,relationship,bal,prod,bran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        dbHelper = new DBHelper(this);

        account = findViewById(R.id.spnAccount);
        accountNo = findViewById(R.id.txtAccNo);
        type = findViewById(R.id.txtAccType);
        openDate = findViewById(R.id.txtOpenDate);
        relationship = findViewById(R.id.txtRelation);
        bal = findViewById(R.id.txtBalance);
        prod = findViewById(R.id.txtProdName);
        bran = findViewById(R.id.txtBranch);

        Intent intent = getIntent();
        nic = intent.getStringExtra(Account_Main.NIC_NUMBER);

        ArrayList<Integer> accNumbers;

        accNumbers = dbHelper.getAccount(nic);

        ArrayAdapter<Integer> accountAdapter = new ArrayAdapter<Integer>(AccountDetails.this,android.R.layout.simple_list_item_1,
                accNumbers);

        accountAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        account.setAdapter(accountAdapter);

        account.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                accNo = account.getSelectedItem().toString();
                showDetails();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void showDetails(){
        ArrayList<String> accInfo = dbHelper.readAllInfoAccount(accNo);

        accountNo.setText(accNo);
        type.setText(accInfo.get(0));
        openDate.setText(accInfo.get(1));
        relationship.setText(accInfo.get(2));
        bal.setText(accInfo.get(3));
        prod.setText(accInfo.get(4));
        bran.setText(accInfo.get(5));
    }
}
