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
import android.widget.Toast;

public class PayBill extends AppCompatActivity implements View.OnClickListener {

    private Button btnNext;
    private Spinner spnBiller,spnAccount;
    private TextView erTxtBiller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_bill);

        this.btnNext = (Button) findViewById(R.id.btnNext);
        spnBiller = (Spinner) findViewById(R.id.spnBiller);
        spnAccount = (Spinner) findViewById(R.id.spnAccount);
        erTxtBiller = (TextView) findViewById(R.id.erTxtSelBiller);

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
                startActivity(billVer);
            }
        }
    }
}
