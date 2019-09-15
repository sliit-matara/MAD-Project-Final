package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_miniproject.DB_Files.DBHelper;

public class AccountJoined extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNIC1,txtNIC2;
    private Button btnAdd;
    private String accountNumber;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_joined);

        dbHelper = new DBHelper(this);

        txtNIC1 = (EditText) findViewById(R.id.txtNIC1);
        txtNIC2 = (EditText) findViewById(R.id.txtNIC2);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        Intent intent = getIntent();
        accountNumber = intent.getStringExtra(AccountAdd.EXTRA_MESSAGE);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAdd){
            addAccount_Holder();
            Intent adminMain =new Intent(this,MainAdmin.class);
            startActivity(adminMain);
        }
    }

    private void addAccount_Holder(){
        int accNo = Integer.parseInt(accountNumber);
        String nic1 = txtNIC1.getText().toString();
        String nic2 = txtNIC2.getText().toString();

        if((dbHelper.addInfoToACCOUNT_HOLDER(nic1,accNo))&&(dbHelper.addInfoToACCOUNT_HOLDER(nic2,accNo)))
            Toast.makeText(getApplicationContext(),"Inserted new Account_Holders!",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Connot insert the Account Holders!",Toast.LENGTH_LONG).show();
    }
}
