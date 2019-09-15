package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_miniproject.DB_Files.DBHelper;

public class AccountSingle extends AppCompatActivity implements View.OnClickListener {

    private EditText txtHolderNIC;
    private Button btnAdd;
    private String accountNumber;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_single);

        dbHelper = new DBHelper(this);

        txtHolderNIC = (EditText) findViewById(R.id.txtHolderNIC);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        Intent intent = getIntent();
        accountNumber = intent.getStringExtra(AccountAdd.EXTRA_MESSAGE);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAdd){
            addAccount_Holder();
            Intent adminMain = new Intent(this,MainAdmin.class);
            startActivity(adminMain);
        }
    }

    private void addAccount_Holder(){
        int accountNo = Integer.parseInt(accountNumber);
        String nic = txtHolderNIC.getText().toString();

        if(dbHelper.addInfoToACCOUNT_HOLDER(nic,accountNo))
            Toast.makeText(getApplicationContext(), "Inserted a new Account_Holder!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Cannot insert the Account_Holder",Toast.LENGTH_LONG).show();
    }
}
