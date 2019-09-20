package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Account_Main extends AppCompatActivity implements View.OnClickListener {

    public static final String NIC_NUMBER = "com.Account.NIC";

    private Button btnAccSummary,btnAccDetails,btnAccActivity;
    public String nic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__main);

        this.btnAccSummary = (Button) findViewById(R.id.btnAccSummary);
        this.btnAccActivity = (Button) findViewById(R.id.btnAccActivity);
        this.btnAccDetails = (Button) findViewById(R.id.btnAccDetails);

        Intent intent = getIntent();
        nic = intent.getStringExtra(MainActivity.NICOFHOLDER);

        btnAccSummary.setOnClickListener(this);
        btnAccActivity.setOnClickListener(this);
        btnAccDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAccSummary){
            Intent accSum = new Intent(this,AccountSummary.class);
            accSum.putExtra(NIC_NUMBER,nic);
            startActivity(accSum);
        }else if(view.getId()==R.id.btnAccActivity){
            Intent accActivity = new Intent(this,AccountActivity.class);
            accActivity.putExtra(NIC_NUMBER,nic);
            startActivity(accActivity);
        }else if(view.getId()==R.id.btnAccDetails){
            Intent accDet = new Intent(this,AccountDetails.class);
            accDet.putExtra(NIC_NUMBER,nic);
            startActivity(accDet);
        }
    }
}
