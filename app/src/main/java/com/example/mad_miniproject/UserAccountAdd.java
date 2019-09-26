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

public class UserAccountAdd extends AppCompatActivity implements View.OnClickListener {

    private Button btnAdd;
    private EditText txtUN,txtPwd,txtNIC;
    private TextView erTxtUN,erTxtPwd,erTxtNIC;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account_add);

        dbHelper = new DBHelper(this);

        txtUN = (EditText) findViewById(R.id.txtUserName);
        txtPwd = (EditText) findViewById(R.id.txtPassword);
        txtNIC = (EditText) findViewById(R.id.txtHolderNIC);

        erTxtUN = (TextView) findViewById(R.id.erTxtUN);
        erTxtPwd = (TextView) findViewById(R.id.erTxtPwd);
        erTxtNIC = (TextView) findViewById(R.id.erTxtHolderNIC);

        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAdd) {
            String unValue = txtUN.getText().toString();
            String pwdValue = txtPwd.getText().toString();
            String nicValue = txtNIC.getText().toString();
            ArrayList<String> nameList = dbHelper.checkMember(nicValue);

            if ((unValue.equals("")) || (unValue.length() <= 6) || (unValue.length() >= 15)) {
                erTxtUN.setTextColor(Color.RED);
                erTxtUN.setText("Please enter valid user name");
                erTxtPwd.setText("");
                erTxtNIC.setText("");
            } else if ((pwdValue.equals("")) || pwdValue.length() <= 8 || pwdValue.length() >= 16) {
                erTxtPwd.setTextColor(Color.RED);
                erTxtPwd.setText("Please enter valid password");
                erTxtUN.setText("");
                erTxtNIC.setText("");
            } else if (nicValue.length() < 10 || nicValue.length() >= 12||nameList.isEmpty()) {
                erTxtNIC.setTextColor(Color.RED);
                erTxtNIC.setText("Please enter valid nic");
                erTxtUN.setText("");
                erTxtPwd.setText("");
            }else {
                addUserAccount();
                Intent adminMain = new Intent(this, MainAdmin.class);
                startActivity(adminMain);
            }
        }
    }

    private void addUserAccount(){
        String userName = txtUN.getText().toString();
        String password = txtPwd.getText().toString();
        String nic = txtNIC.getText().toString();

        if(dbHelper.addInfoToLogin(userName,password,nic))
            Toast.makeText(getApplicationContext(),"Inserted a new User!",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Connot insert user",Toast.LENGTH_LONG).show();
    }
}
