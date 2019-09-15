package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.Calendar;

public class MemberAdd extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName,txtNIC,txtMobile,txtAddress,txtEmail;
    private Button btnAdd;
    private TextView txtDOB;
    private DatePickerDialog.OnDateSetListener setListener;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_add);

        dbHelper = new DBHelper(this);

        txtDOB = (TextView) findViewById(R.id.txtDOB);
        txtName = (EditText) findViewById(R.id.txtHolderName);
        txtNIC = (EditText) findViewById(R.id.txtHolderNIC);
        txtMobile = (EditText) findViewById(R.id.txtHolderMobile);
        txtAddress = (EditText) findViewById(R.id.txtHolderAddress);
        txtEmail = (EditText) findViewById(R.id.txtHolderEmail);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        //Calender--------------------------------------------------
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        txtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MemberAdd.this,android.R.style.Theme,
                        setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year+"/"+month+"/"+day;
                txtDOB.setText(date);
            }
        };

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAdd){
            addMemeber();
            //Intent adminMain = new Intent(this,MainAdmin.class);
            //startActivity(adminMain);
        }
    }

    private void addMemeber() {
        String nic = txtNIC.getText().toString();
        String name = txtName.getText().toString();
        String mobileNo = txtMobile.getText().toString();
        String dob = txtDOB.getText().toString();
        String address = txtAddress.getText().toString();
        String email = txtEmail.getText().toString();

        if (dbHelper.addInfoToAccountHolder(nic,name,mobileNo,dob,address,email)) {
            Toast.makeText(getApplicationContext(), "Inserted a new Account Holder!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Connot Insert the Account Holder!", Toast.LENGTH_LONG).show();
        }
    }
}
