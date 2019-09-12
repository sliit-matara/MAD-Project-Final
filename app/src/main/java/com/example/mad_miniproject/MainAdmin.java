package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainAdmin extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddAcc,btnAddUserAcc,btnAddMem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        btnAddAcc = findViewById(R.id.btnAddAccount);
        btnAddMem = findViewById(R.id.btnAddMember);
        btnAddUserAcc = findViewById(R.id.btnAddUserAcc);

        btnAddAcc.setOnClickListener(this);
        btnAddMem.setOnClickListener(this);
        btnAddUserAcc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAddAccount){
            Intent addAccount = new Intent(this,AccountAdd.class);
            startActivity(addAccount);
        }else if(view.getId()==R.id.btnAddMember){
            Intent addMemeber = new Intent(this,MemberAdd.class);
            startActivity(addMemeber);
        }else if(view.getId()==R.id.btnAddUserAcc){
            Intent addUserAccount = new Intent(this,UserAccountAdd.class);
            startActivity(addUserAccount);
        }
    }
}
