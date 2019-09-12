package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PayBillConfirm extends AppCompatActivity implements View.OnClickListener {

    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_bill_confirm);

        btnConfirm = (Button) findViewById(R.id.btnConfrim);

        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnConfrim){
            Intent main = new Intent(this,MainActivity.class);
            startActivity(main);
        }
    }
}
