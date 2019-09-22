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

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {

    String un;
    EditText curPwd,newPwd,newConPwd;
    DBHelper dbHelper;
    Button update;
    TextView errorCurPwd,errorNewPwd,errorConPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        un = intent.getStringExtra(UserProfile.UN);

        curPwd = findViewById(R.id.txtCurPwd);
        newPwd = findViewById(R.id.txtNewPwd);
        newConPwd = findViewById(R.id.txtConPwd);
        update = findViewById(R.id.btnUpdate);
        errorCurPwd = findViewById(R.id.erTxtCurPwd);
        errorNewPwd = findViewById(R.id.erTxtNewPwd);
        errorConPwd = findViewById(R.id.erTxtConPwd);

        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnUpdate){
            if(checkCurPwd()){
                if(checkNewPwds()){
                    changePassword();
                }
            }
        }
    }

    private boolean checkCurPwd(){
        String currentPwd = curPwd.getText().toString();

        ArrayList<String> pwd;
        pwd = dbHelper.checkPassword(un);

        if(currentPwd.equals(pwd.get(0))){
            errorCurPwd.setText("");
            return true;
        }else {
            errorCurPwd.setTextColor(Color.RED);
            errorCurPwd.setText("Invalid Current Password!!!");
            return false;
        }
    }

    private  boolean checkNewPwds() {
        String newPassword = newPwd.getText().toString();
        String confirmPassword = newConPwd.getText().toString();

        if (newPassword.equals(confirmPassword)) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "New passwords does not match!!!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void changePassword(){
        String password = newPwd.getText().toString();

        dbHelper.changePwd(password,un);
        Toast.makeText(getApplicationContext(),"Password Change Successfully!!!",Toast.LENGTH_LONG).show();

        Intent login = new Intent(this,Login.class);
        startActivity(login);
    }
}
