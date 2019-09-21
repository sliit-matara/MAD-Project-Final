package com.example.mad_miniproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener {

    public static final String HOLDER_NIC = "com.example.nic.HOLDER_NIC";

    private Button btnSignIn;
    private EditText txtUN,txtPwd;
    DBHelper dbHelper;
    TextView forgetUN,forgetPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        txtUN = (EditText) findViewById(R.id.txtUserName);
        txtPwd = (EditText) findViewById(R.id.txtPassword);
        forgetUN = findViewById(R.id.forUN);
        forgetPwd = findViewById(R.id.forPwd);

        btnSignIn.setOnClickListener(this);
        forgetUN.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnSignIn){
            readUNPWD();
        }else if(view.getId()==R.id.forUN){

        }else if(view.getId()==R.id.forPwd){
            Intent forPwd = new Intent(this,ForgetPassword.class);
            startActivity(forPwd);
        }
    }

    private void readUNPWD(){
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
                main.putExtra(HOLDER_NIC,list.get(2));
                startActivity(main);
            }
        }else if((!list.get(0).toString().equals(userName))||(!list.get(1).toString().equals(password))){
            Toast.makeText(getApplicationContext(),"Incorrect username or password!!!",Toast.LENGTH_LONG).show();
        }
    }
}
