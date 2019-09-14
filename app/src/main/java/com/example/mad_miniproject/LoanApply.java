package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoanApply extends AppCompatActivity implements View.OnClickListener {

    private Button btnApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_apply);

        btnApply = (Button) findViewById(R.id.btnLoanApply);

        btnApply.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnLoanApply){
            Intent main = new Intent(this,MainActivity.class);
            startActivity(main);
        }
    }
}
