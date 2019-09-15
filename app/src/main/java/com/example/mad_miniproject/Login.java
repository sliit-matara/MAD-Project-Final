package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
            Intent main =new Intent(this,MainActivity.class);
            startActivity(main);
        }
    }

    private void readUNPWD(){
        String userName = txtUN.getText().toString();
        String password = txtPwd.getText().toString();

        ArrayList list = new ArrayList();

        //list = dbHelper.
    }
}
