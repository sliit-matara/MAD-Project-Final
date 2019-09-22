package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MoneyTransOwnAccount extends AppCompatActivity implements View.OnClickListener {

    private Button btnTransfer;
    String nic;
    DBHelper dbHelper;
    public Spinner spnFrom,spnTo;
    EditText txtAmount;
    int id,transID;
    ArrayList<Double> balanceArr,balanceArrTo;
    double balance,balanceTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_trans_own_account);

        dbHelper = new DBHelper(this);

        spnFrom = findViewById(R.id.spnAccount);
        spnTo = findViewById(R.id.spnTo);
        txtAmount = findViewById(R.id.txtAmount);

        Intent intent = getIntent();
        nic = intent.getStringExtra(MoneyTransMain.USERNIC);

        ArrayList<Integer> accNumbers =new ArrayList<>();

        accNumbers = dbHelper.getAccount(nic);

        ArrayAdapter<Integer> accAdapter = new ArrayAdapter<Integer>(MoneyTransOwnAccount.this,android.R.layout.simple_list_item_1,
                accNumbers);

        accAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnFrom.setAdapter(accAdapter);
        spnTo.setAdapter(accAdapter);

        btnTransfer = (Button) findViewById(R.id.btnTransfer);

        btnTransfer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnTransfer){
            addMoneyTransfer();

            balanceArr = dbHelper.showBalance(spnFrom.getSelectedItem().toString());
            balance = Double.parseDouble(balanceArr.get(0).toString());
            balance=balance-Double.parseDouble(txtAmount.getText().toString());
            dbHelper.updateBalance(spnFrom.getSelectedItem().toString(),balance);

            balanceArrTo = dbHelper.showBalance(spnTo.getSelectedItem().toString());
            balanceTo = Double.parseDouble(balanceArrTo.get(0).toString());
            balanceTo = balanceTo+Double.parseDouble(txtAmount.getText().toString());
            dbHelper.updateBalance(spnTo.getSelectedItem().toString(),balanceTo);

            addTransaction();
        }
    }

    private void addMoneyTransfer(){
        String stFromAccNo = spnFrom.getSelectedItem().toString();
        String stToAccNo = spnTo.getSelectedItem().toString();
        String stAmount = txtAmount.getText().toString();

        int FromAccNo = Integer.parseInt(stFromAccNo);
        int ToAccNo = Integer.parseInt(stToAccNo);
        double amount = Double.parseDouble(stAmount);

        ArrayList<Integer> moneyTrans = dbHelper.readLastTransferID();
        String lastID = moneyTrans.get(0).toString();
        if(lastID.equals("")){
            id=12345;
        }else{
            int preAccNumber = Integer.parseInt(lastID);
            id = preAccNumber+1;
        }

        String approvedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        if(dbHelper.addInfoToMoneyTransfer(id,FromAccNo,ToAccNo,amount,approvedDate)){
            Toast.makeText(getApplicationContext(),"Money Transfer Registered!!!",Toast.LENGTH_LONG).show();
            Intent main = new Intent(this,MainActivity.class);
            startActivity(main);
        }else
            Toast.makeText(getApplicationContext(),"Cannot Transfer",Toast.LENGTH_LONG).show();
    }

    private void addTransaction(){
        String stFromAccNo = spnFrom.getSelectedItem().toString();
        String stAmount = txtAmount.getText().toString();

        int FromAccNo = Integer.parseInt(stFromAccNo);
        double amount = Double.parseDouble(stAmount);

        String transDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        ArrayList<Integer> transaction = dbHelper.readLastTransactionID();
        String lastID = transaction.get(0).toString();
        if(lastID.equals("")){
            transID=1234567890;
        }else{
            int preAccNumber = Integer.parseInt(lastID);
            transID = preAccNumber+1;
        }

        dbHelper.addInfoToTransaction(transID,FromAccNo,"Money Transfer",transDate,amount,0,25);
    }
}
