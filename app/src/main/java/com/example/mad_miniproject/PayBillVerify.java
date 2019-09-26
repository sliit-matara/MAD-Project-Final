package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.ArrayList;

public class PayBillVerify extends AppCompatActivity implements View.OnClickListener {

    public static final String ACCOUNTNUMBER = "com.accountNumber";
    public static final String BILLER = "com.biller";
    public static final String BILLERACCNO = "com.billerAccNo";
    public static final String AMOUNT = "com.amount";

    private Button btnNext;
    private String accNo,biller;
    private EditText txtAccountNumber,txtBiller,txtBillerAccNo,txtAmount;
    private TextView txtShowBalance,errorAcc,errorAmount;
    DBHelper dbHelper;
    ArrayList<Double> balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_bill_verify);

        dbHelper = new DBHelper(this);

        this.btnNext = (Button) findViewById(R.id.btnNext);
        txtAccountNumber = (EditText) findViewById(R.id.txtAccNo);
        txtBiller = (EditText) findViewById(R.id.txtBiller);
        txtBillerAccNo = (EditText) findViewById(R.id.txtBillerAccNo);
        txtAmount = (EditText) findViewById(R.id.txtAmount);
        txtShowBalance = (TextView) findViewById(R.id.txtShowBalance);
        errorAcc = findViewById(R.id.erTxtBillAccNo);
        errorAmount = findViewById(R.id.erTxtAmount);

        Intent intent = getIntent();
        accNo = intent.getStringExtra(PayBill.ACCOUNTNUMBER);
        biller = intent.getStringExtra(PayBill.BILLERTYPE);

        txtAccountNumber.setText(accNo);
        txtBiller.setText(biller);

        btnNext.setOnClickListener(this);
        txtShowBalance.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnNext){
            balance = dbHelper.showBalance(txtAccountNumber.getText().toString());
            if(txtBillerAccNo.getText().toString().equals("")){
                errorAcc.setTextColor(Color.RED);
                errorAcc.setText("Enter a account number");
                errorAmount.setText("");
            }else if(txtAmount.getText().toString().equals("")){
                errorAmount.setTextColor(Color.RED);
                errorAmount.setText("Enter a amount");
                errorAcc.setText("");
            }else if(Integer.parseInt(txtAmount.getText().toString())>balance.get(0)){
                errorAmount.setTextColor(Color.RED);
                errorAmount.setText("Amount exceeds from balance");
                errorAcc.setText("");
            }else {
                Intent billCon = new Intent(this, PayBillConfirm.class);
                billCon.putExtra(ACCOUNTNUMBER, txtAccountNumber.getText().toString());
                billCon.putExtra(BILLER, txtBiller.getText().toString());
                billCon.putExtra(BILLERACCNO, txtBillerAccNo.getText().toString());
                billCon.putExtra(AMOUNT, txtAmount.getText().toString());
                startActivity(billCon);
            }
        }else if(view.getId()==R.id.txtShowBalance){
            showBalance();
        }
    }

    private void showBalance(){
        ArrayList<Double> balance = dbHelper.showBalance(accNo);

        String bal = Double.toString(balance.get(0));

        txtShowBalance.setText(bal);
    }
}
