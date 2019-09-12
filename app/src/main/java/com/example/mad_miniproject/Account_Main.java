package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Account_Main extends AppCompatActivity implements View.OnClickListener {

    private Button btnAccSummary,btnAccDetails,btnAccActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__main);

        this.btnAccSummary = (Button) findViewById(R.id.btnAccSummary);
        this.btnAccActivity = (Button) findViewById(R.id.btnAccActivity);
        this.btnAccDetails = (Button) findViewById(R.id.btnAccDetails);

        btnAccSummary.setOnClickListener(this);
        btnAccActivity.setOnClickListener(this);
        btnAccDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAccSummary){
            Intent accSum = new Intent(this,AccountSummary.class);
            startActivity(accSum);
        }else if(view.getId()==R.id.btnAccActivity){
            Intent accActivity = new Intent(this,AccountActivity.class);
            startActivity(accActivity);
        }else if(view.getId()==R.id.btnAccDetails){
            Intent accDet = new Intent(this,AccountDetails.class);
            startActivity(accDet);
        }
    }
}
