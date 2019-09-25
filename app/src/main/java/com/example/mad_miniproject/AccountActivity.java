package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {

    public Spinner spnAcc;
    String nic,accNo;
    DBHelper dbHelper;
    ArrayList<String> list;
    TextView dt1,dt2,dt3,dt4,dt5,dt6,dt7,dt8,dt9,dt10;
    TextView re1,re2,re3,re4,re5,re6,re7,re8,re9,re10;
    TextView dc1,dc2,dc3,dc4,dc5,dc6,dc7,dc8,dc9,dc10;
    TextView ba1,ba2,ba3,ba4,ba5,ba6,ba7,ba8,ba9,ba10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        dbHelper = new DBHelper(this);

        spnAcc = findViewById(R.id.spnAccount);

        dt1 = findViewById(R.id.date1);
        dt2 = findViewById(R.id.date2);
        dt3 = findViewById(R.id.date3);
        dt4 = findViewById(R.id.date4);
        dt5 = findViewById(R.id.date5);
        dt6 = findViewById(R.id.date6);
        dt7 = findViewById(R.id.date7);
        dt8 = findViewById(R.id.date8);
        dt9 = findViewById(R.id.date9);
        dt10 = findViewById(R.id.date10);

        re1 = findViewById(R.id.ref1);
        re2 = findViewById(R.id.ref2);
        re3 = findViewById(R.id.ref3);
        re4 = findViewById(R.id.ref4);
        re5 = findViewById(R.id.ref5);
        re6 = findViewById(R.id.ref6);
        re7 = findViewById(R.id.ref7);
        re8 = findViewById(R.id.ref8);
        re9 = findViewById(R.id.ref9);
        re10 = findViewById(R.id.ref10);

        dc1 = findViewById(R.id.cd1);
        dc2 = findViewById(R.id.cd2);
        dc3 = findViewById(R.id.cd3);
        dc4 = findViewById(R.id.cd4);
        dc5 = findViewById(R.id.cd5);
        dc6 = findViewById(R.id.cd6);
        dc7 = findViewById(R.id.cd7);
        dc8 = findViewById(R.id.cd8);
        dc9 = findViewById(R.id.cd9);
        dc10 = findViewById(R.id.cd10);

        ba1 = findViewById(R.id.bal1);
        ba2 = findViewById(R.id.bal2);
        ba3 = findViewById(R.id.bal3);
        ba4 = findViewById(R.id.bal4);
        ba5 = findViewById(R.id.bal5);
        ba6 = findViewById(R.id.bal6);
        ba7 = findViewById(R.id.bal7);
        ba8 = findViewById(R.id.bal8);
        ba9 = findViewById(R.id.bal9);
        ba10 = findViewById(R.id.bal10);

        Intent intent = getIntent();
        nic = intent.getStringExtra(Account_Main.NIC_NUMBER);

        ArrayList<Integer> accNumbers;

        accNumbers = dbHelper.getAccount(nic);

        ArrayAdapter<Integer> accountAdapter = new ArrayAdapter<Integer>(AccountActivity.this,android.R.layout.simple_list_item_1,
                accNumbers);

        accountAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnAcc.setAdapter(accountAdapter);

        spnAcc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                accNo = spnAcc.getSelectedItem().toString();
                showDetails();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void showDetails(){
        ArrayList<String> transInfo = dbHelper.readAllInfoTransaction(accNo);
        int lenArr = transInfo.size();

        if(!transInfo.isEmpty()) {
            re1.setText(transInfo.get(0));
            dt1.setText(transInfo.get(1));
            double amount1 = Double.parseDouble(transInfo.get(2));
            if (amount1!=0) {
                String txt1 = transInfo.get(2) + "(C)";
                dc1.setText(txt1);
            } else {
                String txt2 = transInfo.get(3) + "(D)";
                dc1.setText(txt2);
            }
            ba1.setText(transInfo.get(4));
        }

        if(lenArr>5){
            re2.setText(transInfo.get(5));
            dt2.setText(transInfo.get(6));
            double amount2 = Double.parseDouble(transInfo.get(7));
            if (amount2!=0) {
                String txt1 = transInfo.get(7) + "(C)";
                dc2.setText(txt1);
            } else {
                String txt2 = transInfo.get(8) + "(D)";
                dc2.setText(txt2);
            }
            ba2.setText(transInfo.get(9));
        }

        if(lenArr>10){
            re3.setText(transInfo.get(10));
            dt3.setText(transInfo.get(11));
            double amount3 = Double.parseDouble(transInfo.get(12));
            if (amount3!=0) {
                String txt1 = transInfo.get(12) + "(C)";
                dc3.setText(txt1);
            } else {
                String txt2 = transInfo.get(13) + "(D)";
                dc3.setText(txt2);
            }
            ba3.setText(transInfo.get(14));
        }

        if(lenArr>15){
            re4.setText(transInfo.get(15));
            dt4.setText(transInfo.get(16));
            double amount4 = Double.parseDouble(transInfo.get(17));
            if (amount4!=0) {
                String txt1 = transInfo.get(17) + "(C)";
                dc4.setText(txt1);
            } else {
                String txt2 = transInfo.get(18) + "(D)";
                dc4.setText(txt2);
            }
            ba4.setText(transInfo.get(19));
        }

        if(lenArr>20){
            re5.setText(transInfo.get(20));
            dt5.setText(transInfo.get(21));
            double amount5 = Double.parseDouble(transInfo.get(22));
            if (amount5!=0) {
                String txt1 = transInfo.get(22) + "(C)";
                dc5.setText(txt1);
            } else {
                String txt2 = transInfo.get(23) + "(D)";
                dc5.setText(txt2);
            }
            ba5.setText(transInfo.get(24));
        }

        if(lenArr>25){
            re6.setText(transInfo.get(25));
            dt6.setText(transInfo.get(26));
            double amount6 = Double.parseDouble(transInfo.get(27));
            if (amount6!=0) {
                String txt1 = transInfo.get(27) + "(C)";
                dc6.setText(txt1);
            } else {
                String txt2 = transInfo.get(28) + "(D)";
                dc6.setText(txt2);
            }
            ba6.setText(transInfo.get(29));
        }

        if(lenArr>30){
            re7.setText(transInfo.get(30));
            dt7.setText(transInfo.get(31));
            double amount7 = Double.parseDouble(transInfo.get(32));
            if (amount7!=0) {
                String txt1 = transInfo.get(32) + "(C)";
                dc7.setText(txt1);
            } else {
                String txt2 = transInfo.get(33) + "(D)";
                dc7.setText(txt2);
            }
            ba7.setText(transInfo.get(33));
        }

        if(lenArr>35){
            re8.setText(transInfo.get(35));
            dt8.setText(transInfo.get(36));
            double amount8 = Double.parseDouble(transInfo.get(37));
            if (amount8!=0) {
                String txt1 = transInfo.get(37) + "(C)";
                dc8.setText(txt1);
            } else {
                String txt2 = transInfo.get(38) + "(D)";
                dc8.setText(txt2);
            }
            ba8.setText(transInfo.get(39));
        }

        if(lenArr>40){
            re9.setText(transInfo.get(40));
            dt9.setText(transInfo.get(41));
            double amount9 = Double.parseDouble(transInfo.get(42));
            if (amount9!=0) {
                String txt1 = transInfo.get(42) + "(C)";
                dc9.setText(txt1);
            } else {
                String txt2 = transInfo.get(43) + "(D)";
                dc9.setText(txt2);
            }
            ba9.setText(transInfo.get(44));
        }

        if(lenArr>45){
            re10.setText(transInfo.get(45));
            dt10.setText(transInfo.get(46));
            double amount10 = Double.parseDouble(transInfo.get(47));
            if (amount10!=0) {
                String txt1 = transInfo.get(47) + "(C)";
                dc10.setText(txt1);
            } else {
                String txt2 = transInfo.get(48) + "(D)";
                dc10.setText(txt2);
            }
            ba10.setText(transInfo.get(49));
        }
    }
}
