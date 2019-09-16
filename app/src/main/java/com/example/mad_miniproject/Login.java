package com.example.mad_miniproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignIn;
    private EditText txtUN,txtPwd;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        txtUN = (EditText) findViewById(R.id.txtUserName);
        txtPwd = (EditText) findViewById(R.id.txtPassword);

        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnSignIn){
            if(!readUNPWD()){
                Intent login = new Intent(this,Login.class);
                startActivity(login);
            }
        }
    }

    private boolean readUNPWD(){
        String userName = txtUN.getText().toString();
        String password = txtPwd.getText().toString();

        ArrayList<String> list = new ArrayList<>();

        list = dbHelper.getUnPwd(userName,password);

        if((list.get(0).toString().equals(userName))&&(list.get(1).toString().equals(password))){
            Toast.makeText(getApplicationContext(),"You are successfully logged!!!",Toast.LENGTH_LONG).show();
            if(list.get(0).toString().equals("Admin")){
                Intent adminMain = new Intent(this,MainAdmin.class);
                startActivity(adminMain);
            }else {
                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);
            }
            return true;
        }else
            return false;
    }
}
