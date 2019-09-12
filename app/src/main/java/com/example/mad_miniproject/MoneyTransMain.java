package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MoneyTransMain extends AppCompatActivity implements View.OnClickListener {

    private Button ownAcc,otherAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_trans_main);

        ownAcc = (Button) findViewById(R.id.btnOwnAcc);
        otherAcc = (Button) findViewById(R.id.btnOtherAcc);

        ownAcc.setOnClickListener(this);
        otherAcc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnOwnAcc){
            Intent ownAccount = new Intent(this,MoneyTransOwnAccount.class);
            startActivity(ownAccount);
        }else if(view.getId()==R.id.btnOtherAcc){
            Intent otherAccount = new Intent(this,MoneyTransOtherAccount.class);
            startActivity(otherAccount);
        }
    }
}
