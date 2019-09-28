package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {

    public static final String UN = "com.sample.UN";

    Button logout,changePwd;
    String un;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        changePwd = findViewById(R.id.btnChangePwd);
        logout = findViewById(R.id.btnLogout);

        Intent intent = getIntent();
        un = intent.getStringExtra(MainActivity.UNOFHODER);

        changePwd.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnChangePwd){
            Intent changePwd = new Intent(this,ChangePassword.class);
            changePwd.putExtra(UN,un);
            startActivity(changePwd);
        }else if(view.getId()==R.id.btnLogout){
            Intent login = new Intent(this,Login.class);
            startActivity(login);
        }
    }
}
