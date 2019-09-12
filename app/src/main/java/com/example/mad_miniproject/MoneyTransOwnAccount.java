package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MoneyTransOwnAccount extends AppCompatActivity implements View.OnClickListener {

    private Button btnTransfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_trans_own_account);

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
