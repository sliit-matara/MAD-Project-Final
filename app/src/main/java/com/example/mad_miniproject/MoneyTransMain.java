package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MoneyTransMain extends AppCompatActivity implements View.OnClickListener {

    public static final String USERNIC = "com.example.nic";

    Button ownAcc,otherAcc;
    String nic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_trans_main);

        ownAcc = (Button) findViewById(R.id.btnOwnAcc);
        otherAcc = (Button) findViewById(R.id.btnOtherAcc);

        Intent intent = getIntent();
        nic = intent.getStringExtra(MainActivity.NICOFHOLDER);

        ownAcc.setOnClickListener(this);
        otherAcc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnOwnAcc){
            Intent ownAccount = new Intent(this,MoneyTransOwnAccount.class);
            ownAccount.putExtra(USERNIC,nic);
            startActivity(ownAccount);
        }else if(view.getId()==R.id.btnOtherAcc){
            Intent otherAccount = new Intent(this,MoneyTransOtherAccount.class);
            otherAccount.putExtra(USERNIC,nic);
            startActivity(otherAccount);
        }
    }
}
