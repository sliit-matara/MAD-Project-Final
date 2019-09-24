package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.ArrayList;

public class AccountSingle extends AppCompatActivity implements View.OnClickListener {

    EditText txtHolderNIC;
    Button btnAdd;
    private String accountNumber,nic;
    TextView errorNIC;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_single);

        dbHelper = new DBHelper(this);

        txtHolderNIC = findViewById(R.id.txtHolderNIC);
        btnAdd = findViewById(R.id.btnAdd);
        errorNIC = findViewById(R.id.erTxtNIC);

        Intent intent = getIntent();
        accountNumber = intent.getStringExtra(AccountAdd.EXTRA_MESSAGE);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAdd){
            nic = txtHolderNIC.getText().toString();
            ArrayList<String> nameList;
            nameList = dbHelper.checkMember(nic);
            String msg ="Member NIC not registered";
            if(nameList.isEmpty()){
                errorNIC.setTextColor(Color.RED);
                errorNIC.setText(msg);
            }else{
                addAccount_Holder();
                Intent adminMain = new Intent(this, MainAdmin.class);
                startActivity(adminMain);
            }
        }
    }

    private void addAccount_Holder(){
        int accountNo = Integer.parseInt(accountNumber);

        if (dbHelper.addInfoToACCOUNT_HOLDER(nic, accountNo)) {
            Toast.makeText(getApplicationContext(), "Inserted a new Account_Holder!", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(getApplicationContext(), "Cannot insert the Account_Holder", Toast.LENGTH_LONG).show();
    }
}
