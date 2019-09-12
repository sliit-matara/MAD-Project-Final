package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MoneyTransSuccess extends AppCompatActivity implements View.OnClickListener {

    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_trans_success);

        btnDone = findViewById(R.id.btnDone);

        btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnDone){
            Intent main = new Intent(this,MainActivity.class);
            startActivity(main);
        }
    }
}
