package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAccount,btnLoan,btnMoneyTrans,btnBillPay,btnAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnAccount = (Button) findViewById(R.id.btnAccount);
        this.btnBillPay = (Button) findViewById(R.id.btnBillPayment);
        this.btnMoneyTrans = (Button) findViewById(R.id.btnMoneyTransfer);
        this.btnLoan = (Button) findViewById(R.id.btnLoan);

        btnAdmin = findViewById(R.id.button5);

        btnAccount.setOnClickListener(this);
        btnBillPay.setOnClickListener(this);
        btnMoneyTrans.setOnClickListener(this);
        btnLoan.setOnClickListener(this);

        btnAdmin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAccount){
            Intent accountMain = new Intent(this,Account_Main.class);
            startActivity(accountMain);
        }else if(view.getId() == R.id.btnBillPayment){
            Intent payBill = new Intent(this,PayBill.class);
            startActivity(payBill);
        }else if(view.getId()==R.id.btnMoneyTransfer){
            Intent moneyTrans = new Intent(this,MoneyTransMain.class);
            startActivity(moneyTrans);
        }else if(view.getId()==R.id.button5){
            Intent admin = new Intent(this,MainAdmin.class);
            startActivity(admin);
        }else if(view.getId()==R.id.btnLoan){
            Intent loan = new Intent(this,LoanApply.class);
            startActivity(loan);
        }
    }
}
