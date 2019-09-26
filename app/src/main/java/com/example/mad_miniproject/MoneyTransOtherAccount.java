package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MoneyTransOtherAccount extends AppCompatActivity implements View.OnClickListener {

    private Button btnTransfer;
    DBHelper dbHelper;
    public Spinner spnFrom;
    EditText txtAmount,txtTo;
    private String nic;
    int id,transID;
    ArrayList<Double> balanceArr,balanceArrTo;
    double balance,balanceTo;
    TextView txtShowBalance,errorAccNo,errorAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_trans_other_account);

        dbHelper = new DBHelper(this);

        spnFrom = findViewById(R.id.spnAccount);
        txtTo = findViewById(R.id.txtAccNo);
        txtAmount = findViewById(R.id.txtAmount);
        txtShowBalance = findViewById(R.id.txtShowBalance);
        errorAccNo = findViewById(R.id.erTxtAccNo);
        errorAmount = findViewById(R.id.erTxtAmount);

        Intent intent = getIntent();
        nic = intent.getStringExtra(MoneyTransMain.USERNIC);

        ArrayList<Integer> accNumbers = dbHelper.getAccount(nic);

        ArrayAdapter<Integer> accAdapter = new ArrayAdapter<Integer>(MoneyTransOtherAccount.this,android.R.layout.simple_list_item_1,
                accNumbers);

        accAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnFrom.setAdapter(accAdapter);

        btnTransfer = (Button) findViewById(R.id.btnTransfer);

        btnTransfer.setOnClickListener(this);
        txtShowBalance.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnTransfer){
            ArrayList<Double> balances = dbHelper.showBalance(spnFrom.getSelectedItem().toString());
            String from = spnFrom.getSelectedItem().toString();
            String to = txtTo.getText().toString();
            String transAmt = txtAmount.getText().toString();
            if(to.equals("")){
                errorAccNo.setTextColor(Color.RED);
                errorAccNo.setText("Enter Account No");
                errorAmount.setText("");
            }else if(transAmt.equals("")){
                errorAmount.setTextColor(Color.RED);
                errorAmount.setText("Enter Transfer Amount");
                errorAccNo.setText("");
            }else if(to.equals(from)){
                errorAccNo.setTextColor(Color.RED);
                errorAccNo.setText("Account Number Cannot be same");
                errorAmount.setText("");
            }else if(balances.get(0)<Integer.parseInt(transAmt)) {
                errorAmount.setTextColor(Color.RED);
                errorAmount.setText("Amount exceeds from balance");
                errorAccNo.setText("");
            }else {
                if(addMoneyTransfer()){
                    updateBalance();
                    if(addTransaction()) {
                        Toast.makeText(getApplicationContext(), "Money Transfer Registered!!!", Toast.LENGTH_LONG).show();
                        Intent main = new Intent(this, MainActivity.class);
                        startActivity(main);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Cannot Transfer", Toast.LENGTH_LONG).show();
                }
            }
        }else if(view.getId()==R.id.txtShowBalance){
            showBalance();
        }
    }

    private boolean addMoneyTransfer(){
        String stFromAccNo = spnFrom.getSelectedItem().toString();
        String stToAccNo = txtTo.getText().toString();
        String stAmount = txtAmount.getText().toString();

        int FromAccNo = Integer.parseInt(stFromAccNo);
        int ToAccNo = Integer.parseInt(stToAccNo);
        double amount = Double.parseDouble(stAmount);

        ArrayList<Integer> moneyTrans = dbHelper.readLastTransferID();
        if(moneyTrans.isEmpty()){
            id=12345;
        }else{
            int preAccNumber = moneyTrans.get(0);
            id = preAccNumber+1;
        }

        String approvedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        return (dbHelper.addInfoToMoneyTransfer(id,FromAccNo,ToAccNo,amount,approvedDate));
    }

    private boolean addTransaction(){
        String stFromAccNo = spnFrom.getSelectedItem().toString();
        String stToAccNo = txtTo.getText().toString();
        String stAmount = txtAmount.getText().toString();

        int FromAccNo = Integer.parseInt(stFromAccNo);
        int ToAccNo = Integer.parseInt(stToAccNo);
        double amount = Double.parseDouble(stAmount);

        String transDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        ArrayList<Integer> transactions = dbHelper.readLastTransactionID();
        if(transactions.isEmpty()){
            transID=1234567890;
        }else{
            int preAccNumber =transactions.get(0);
            transID = preAccNumber+1;
        }

        return (dbHelper.addInfoToTransaction(transID,FromAccNo,"Money Transfer",transDate,amount,0,balance)&&
        dbHelper.addInfoToTransaction(transID+1,ToAccNo,"Money Transfer",transDate,0,amount,balanceTo));
    }

    private void showBalance(){
        ArrayList<Double> balance = dbHelper.showBalance(spnFrom.getSelectedItem().toString());

        String bal = Double.toString(balance.get(0));

        txtShowBalance.setText(bal);
    }

    private void updateBalance(){
        String accountNo = spnFrom.getSelectedItem().toString();
        String accountNoTo = txtTo.getText().toString();

        balanceArr = dbHelper.showBalance(accountNo);
        balanceArrTo =dbHelper.showBalance(accountNoTo);

        balance = balanceArr.get(0);
        balanceTo = balanceArrTo.get(0);

        double transferAmount = Double.parseDouble(txtAmount.getText().toString());
        balance=balance-transferAmount;
        balanceTo = balanceTo+transferAmount;

        dbHelper.updateBalance(accountNo,balance);
        dbHelper.updateBalance(accountNoTo,balanceTo);
    }
}
