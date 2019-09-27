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

public class NewPassword extends AppCompatActivity implements View.OnClickListener {

    EditText pwd,conPwd;
    DBHelper dbHelper;
    Button done;
    String un;
    TextView erText;
    String error = "Password does not match";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        dbHelper = new DBHelper(this);

        pwd = findViewById(R.id.pwdNew);
        conPwd = findViewById(R.id.pwdCon);
        done = findViewById(R.id.btnDone);
        erText = findViewById(R.id.erPwdMatch);

        Intent intent = getIntent();
        un = intent.getStringExtra(ForgetPassword.UNPASS);

        done.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnDone){
            if(validate()){
               changePassword();
            }
        }
    }

    private boolean validate(){
        String password = pwd.getText().toString();
        String confirmPwd = conPwd.getText().toString();

        if(password.length() <= 8 || password.length() >= 16) {
            erText.setTextColor(Color.RED);
            erText.setText("Enter valid password!!!");
            return false;
        }else if(password.equals(confirmPwd)){
            erText.setText("");
            return true;
        }else{
            erText.setTextColor(Color.RED);
            erText.setText(error);
            return false;
        }
    }

    private void changePassword(){
        String password = pwd.getText().toString();

        dbHelper.changePwd(password,un);
        Toast.makeText(getApplicationContext(),"Password Change Successfully!!!",Toast.LENGTH_LONG).show();

        Intent login = new Intent(this,Login.class);
        startActivity(login);
    }
}
