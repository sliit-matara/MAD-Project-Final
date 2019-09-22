package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PayBillConfirm extends AppCompatActivity implements View.OnClickListener {

    protected Button btnCon;
    private TextView accNo,biller,billerAccNo,amount;
    DBHelper dbHelper;
    int payID,transID;
    ArrayList<Double> balanceArr;
    double balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_bill_confirm);

        dbHelper = new DBHelper(this);

        btnCon = findViewById(R.id.btnConfrim);
        accNo = findViewById(R.id.valAccNo);
        biller = findViewById(R.id.valBillerName);
        billerAccNo = findViewById(R.id.valBillAccNo);
        amount = findViewById(R.id.valAmount);

        Intent intent = getIntent();

        accNo.setText(intent.getStringExtra(PayBillVerify.ACCOUNTNUMBER));
        biller.setText(intent.getStringExtra(PayBillVerify.BILLER));
        billerAccNo.setText(intent.getStringExtra(PayBillVerify.BILLERACCNO));
        amount.setText(intent.getStringExtra(PayBillVerify.AMOUNT));

        btnCon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnConfrim){
            addPayBill();
            balanceArr = dbHelper.showBalance(accNo.getText().toString());
            balance = Double.parseDouble(balanceArr.get(0).toString());
            balance=balance-Double.parseDouble(amount.getText().toString());
            dbHelper.updateBalance(accNo.getText().toString(),balance);
            addTransaction();
        }
    }

    private void addPayBill(){
        int accNumber = Integer.parseInt(accNo.getText().toString());
        String billerName = biller.getText().toString();
        String billAccountNo = billerAccNo.getText().toString();
        double billAmount = Double.parseDouble(amount.getText().toString());

        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        ArrayList<Integer> accNo = dbHelper.readLastPayBillID();
        String lastAcc = accNo.get(0).toString();
        if(lastAcc.equals("")){
            payID=1234567;
        }else{
            int preAccNumber = Integer.parseInt(lastAcc);
            payID = preAccNumber+1;
        }

        if(dbHelper.addInfoToBillPayment(payID,accNumber,billerName,billAccountNo,billAmount,currentDate)) {
            Toast.makeText(getApplicationContext(), "Successfully Inserted!!!", Toast.LENGTH_LONG).show();
            Intent main = new Intent(PayBillConfirm.this, MainActivity.class);
            startActivity(main);
        } else
            Toast.makeText(getApplicationContext(),"Not Inserted!!!",Toast.LENGTH_LONG).show();

    }

    private void addTransaction(){
        String stAccNo = accNo.getText().toString();
        String stAmount = amount.getText().toString();

        int accNo = Integer.parseInt(stAccNo);
        double amount = Double.parseDouble(stAmount);

        String paidDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        ArrayList<Integer> transaction = dbHelper.readLastTransactionID();
        String lastID = transaction.get(0).toString();
        if(lastID.equals("")){
            transID=1234567890;
        }else{
            int preAccNumber = Integer.parseInt(lastID);
            transID = preAccNumber+1;
        }

        dbHelper.addInfoToTransaction(transID,accNo,"Bill Payment",paidDate,amount,0,balance);
    }
}
