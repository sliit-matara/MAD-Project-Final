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
        ArrayList<String> pwd = dbHelper.checkPassword(un);
        String currentPwd = curPwd.getText().toString();

        if(!pwd.get(0).equals(currentPwd)||currentPwd.equals("")){
            errorCurPwd.setTextColor(Color.RED);
            errorCurPwd.setText("Invalid Current Password!!!");
            errorNewPwd.setText("");
            errorConPwd.setText("");
            return false;
        }else {
            errorCurPwd.setText("");
            return true;
        }
    }

    private  boolean checkNewPwds() {
        String newPassword = newPwd.getText().toString();
        String confirmPassword = newConPwd.getText().toString();

        if(newPassword.equals("")){
            errorNewPwd.setTextColor(Color.RED);
            errorNewPwd.setText("Enter new password");
            errorCurPwd.setText("");
            errorConPwd.setText("");
            return false;
        }else if (confirmPassword.equals("")){
            errorConPwd.setTextColor(Color.RED);
            errorConPwd.setText("Enter confirm password");
            errorCurPwd.setText("");
            errorNewPwd.setText("");
            return false;
        }else if (newPassword.equals(confirmPassword)) {
            return true;
        }else {
            errorConPwd.setTextColor(Color.RED);
            errorConPwd.setText("Passwords does not match");
            errorCurPwd.setText("");
            errorNewPwd.setText("");
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
