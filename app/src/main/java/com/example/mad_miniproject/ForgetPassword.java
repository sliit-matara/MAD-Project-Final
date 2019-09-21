package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mad_miniproject.DB_Files.DBHelper;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {

    Button nextBtn;
    EditText username,email,mobile;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        dbHelper = new DBHelper(this);

        nextBtn = findViewById(R.id.btnNext);
        username = findViewById(R.id.txtUsername);
        email = findViewById(R.id.txtEmail);
        mobile = findViewById(R.id.txtMobile);

        nextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnNext){
            Intent newPwd = new Intent(this,NewPassword.class);
            startActivity(newPwd);
        }
    }
}
