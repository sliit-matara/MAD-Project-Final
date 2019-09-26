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
    private String accountNumber,bill,billAccountNumber,billAmount;

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
        accountNumber = intent.getStringExtra(PayBillVerify.ACCOUNTNUMBER);
        bill = intent.getStringExtra(PayBillVerify.BILLER);
        billAccountNumber = intent.getStringExtra(PayBillVerify.BILLERACCNO);
        billAmount = intent.getStringExtra(PayBillVerify.AMOUNT);

        accNo.setText(accountNumber);
        biller.setText(bill);
        billerAccNo.setText(billAccountNumber);
        amount.setText(billAmount);

        btnCon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnConfrim){
            if(addPayBill()) {
                updateBalance();
                if (addTransaction()) {
                    Toast.makeText(getApplicationContext(), "Bill Payment Successfully", Toast.LENGTH_LONG).show();
                    Intent main = new Intent(PayBillConfirm.this, MainActivity.class);
                    startActivity(main);
                }
            }
        }
    }

    private boolean addPayBill(){
        int accNumber = Integer.parseInt(accountNumber);
        double bilAmount = Double.parseDouble(billAmount);

        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        ArrayList<Integer> accNo = dbHelper.readLastPayBillID();
        if(accNo.isEmpty()){
            payID=1234567;
        }else{
            int preAccNumber = accNo.get(0);
            payID = preAccNumber+1;
        }

        return (dbHelper.addInfoToBillPayment(payID,accNumber,bill,billAccountNumber,bilAmount,currentDate));
    }

    private boolean addTransaction(){
        String stAccNo = accNo.getText().toString();
        String stAmount = amount.getText().toString();

        int accNo = Integer.parseInt(stAccNo);
        double amount = Double.parseDouble(stAmount);

        String paidDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        ArrayList<Integer> transaction = dbHelper.readLastTransactionID();
        if(transaction.isEmpty()){
            transID=1234567890;
        }else{
            int preAccNumber = transaction.get(0);
            transID = preAccNumber+1;
        }

        return dbHelper.addInfoToTransaction(transID,accNo,"Bill Payment",paidDate,amount,0,balance);
    }

    private void updateBalance(){
        String accountNo = accNo.getText().toString();
        balanceArr = dbHelper.showBalance(accountNo);
        balance = balanceArr.get(0);
        double billAmount = Double.parseDouble(amount.getText().toString());
        balance=balance-billAmount;
        dbHelper.updateBalance(accountNo,balance);
    }
}
