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

public class AccountJoined extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNIC1,txtNIC2;
    private Button btnAdding;
    TextView error1,error2;
    private String accountNumber,nic1,nic2;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_joined);

        dbHelper = new DBHelper(this);

        txtNIC1 = (EditText) findViewById(R.id.txtNIC1);
        txtNIC2 = (EditText) findViewById(R.id.txtNIC2);
        btnAdding = (Button) this.findViewById(R.id.btnAdd);
        error1 = findViewById(R.id.erTxt1NIC);
        error2 = findViewById(R.id.erTxt2NIC);

        Intent intent = getIntent();
        accountNumber = intent.getStringExtra(AccountAdd.EXTRA_MESSAGE);

        btnAdding.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAdd){
            nic1 = txtNIC1.getText().toString();
            nic2 = txtNIC2.getText().toString();
            ArrayList<String> nameSet1 = dbHelper.checkMember(nic1);
            ArrayList<String> nameSet2 = dbHelper.checkMember(nic2);
            String msg = "Member not registered!!!";
            if(nic1.equals(nic2)) {
                error2.setTextColor(Color.RED);
                error2.setText("NIC cannot be same!!!");
                error1.setText("");
            }else if(nameSet1.isEmpty()||nic1.equals("")) {
                error2.setText("");
                error1.setTextColor(Color.RED);
                error1.setText(msg);
            }else if(nameSet2.isEmpty()||nic2.equals("")){
                error1.setText("");
                error2.setTextColor(Color.RED);
                error2.setText(msg);
            }else{
                if(addAccount_Holder()) {
                    Toast.makeText(getApplicationContext(),"Account Holders are assigned",Toast.LENGTH_LONG).show();
                    Intent adminMain = new Intent(AccountJoined.this, MainAdmin.class);
                    startActivity(adminMain);
                }else
                    Toast.makeText(getApplicationContext(),"Cannot assign Account Holders",Toast.LENGTH_LONG).show();;
            }
        }
    }

    private boolean addAccount_Holder(){
        int accNo = Integer.parseInt(accountNumber);

        return ((dbHelper.addInfoToACCOUNT_HOLDER(nic1,accNo))&&(dbHelper.addInfoToACCOUNT_HOLDER(nic2,accNo)));
    }
}
