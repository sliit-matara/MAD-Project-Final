package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.ArrayList;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {

    public static final String UNPASS = "pass.un";

    Button nextBtn;
    EditText username,mobile;
    DBHelper dbHelper;
    String userUN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        dbHelper = new DBHelper(this);

        nextBtn = findViewById(R.id.btnNext);
        username = findViewById(R.id.txtUsername);
        mobile = findViewById(R.id.txtMobile);

        nextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnNext){
            checkDet();
        }
    }

    private void checkDet(){
        String un = username.getText().toString();
        String mobil = mobile.getText().toString();

        ArrayList<String> uns;

        uns = dbHelper.getUNMobile(un,mobil);

        if(uns.isEmpty()){
            Toast.makeText(getApplicationContext(),"Sorry!, username of mobile invalid",Toast.LENGTH_LONG).show();
            finish();
            startActivity(getIntent());
        }else{
            userUN = uns.get(0);
            Intent newPwd = new Intent(this, NewPassword.class);
            newPwd.putExtra(UNPASS,userUN);
            startActivity(newPwd);
        }
    }
}
