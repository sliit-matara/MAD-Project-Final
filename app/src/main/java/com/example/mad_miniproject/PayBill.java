package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.ArrayList;

public class PayBill extends AppCompatActivity implements View.OnClickListener {

    public static final String ACCOUNTNUMBER = "com.example.ACCNO";
    public static final String BILLERTYPE = "com.exaple.BILLTYPE";

    private Button btnNext;
    private Spinner spnBiller,spnAccount;
    private TextView erTxtBiller;
    DBHelper dbHelper;
    private String nic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_bill);

        dbHelper = new DBHelper(this);

        this.btnNext = (Button) findViewById(R.id.btnNext);
        spnBiller = (Spinner) findViewById(R.id.spnBiller);
        spnAccount = (Spinner) findViewById(R.id.spnAccount);
        erTxtBiller = (TextView) findViewById(R.id.erTxtSelBiller);

        Intent intent = getIntent();
        nic = intent.getStringExtra(MainActivity.NICOFHOLDER);

        ArrayList<Integer> accNumbers = dbHelper.getAccount(nic);

        ArrayAdapter<Integer> accAdapter = new ArrayAdapter<Integer>(PayBill.this,android.R.layout.simple_list_item_1,
                accNumbers);

        accAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnAccount.setAdapter(accAdapter);

        btnNext.setOnClickListener(this);

        ArrayAdapter<String> billerAdapter = new ArrayAdapter<String>(PayBill.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.billerList));

        billerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnBiller.setAdapter(billerAdapter);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnNext){
            String spnValue = spnBiller.getSelectedItem().toString();
            if(spnValue.equals("Choose...")){
                erTxtBiller.setTextColor(Color.RED);
                erTxtBiller.setText("Please select a Biller");
            }else {
                erTxtBiller.setText("");
                Intent billVer = new Intent(this, PayBillVerify.class);
                billVer.putExtra(ACCOUNTNUMBER,spnAccount.getSelectedItem().toString());
                billVer.putExtra(BILLERTYPE,spnBiller.getSelectedItem().toString());
                startActivity(billVer);
            }
        }
    }
}
