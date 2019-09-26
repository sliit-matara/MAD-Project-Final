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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberAdd extends AppCompatActivity implements View.OnClickListener {

    EditText txtName,txtNIC,txtMobile,txtAddress,txtEmail;
    Button btnAdd;
    TextView txtDOB,erTxtName,erTxtNIC,erTxtMobile,erTxtDOB,erTxtEmail;
    DatePickerDialog.OnDateSetListener setListener;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_add);

        dbHelper = new DBHelper(this);

        txtDOB = (TextView) findViewById(R.id.txtDOB);
        erTxtName = (TextView) findViewById(R.id.erTxtName);
        erTxtNIC = (TextView) findViewById(R.id.erTxtNic);
        erTxtMobile = (TextView) findViewById(R.id.erTxtMobile);
        erTxtDOB = (TextView) findViewById(R.id.erTxtDOB);
        erTxtEmail = (TextView) findViewById(R.id.erTxtEmail);

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
                String date = year+"-"+month+"-"+day;
                txtDOB.setText(date);
            }
        };

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAdd){
            String alphabaticExp = "[ .a-zA-Z]+";
            String emailExp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";

            Matcher nameMatch = Pattern.compile(alphabaticExp).matcher(txtName.getText().toString());
            Matcher emailMatch = Pattern.compile(emailExp).matcher(txtEmail.getText().toString());
            int nicLen = txtNIC.getText().toString().length();

            if((txtName.equals(""))||(!nameMatch.matches())){
                erTxtName.setTextColor(Color.RED);
                erTxtName.setText("Enter valid Name");
                erTxtNIC.setText("");
                erTxtMobile.setText("");
                erTxtDOB.setText("");
                erTxtEmail.setText("");
            }else if((txtNIC.equals(""))||(!(nicLen<=12))||(!(nicLen>=10))) {
                erTxtNIC.setTextColor(Color.RED);
                erTxtNIC.setText("Enter valid NIC");
                erTxtName.setText("");
                erTxtMobile.setText("");
                erTxtDOB.setText("");
                erTxtEmail.setText("");
            }else if((txtMobile.equals(""))||(txtMobile.getText().toString().length()!=10)) {
                erTxtMobile.setTextColor(Color.RED);
                erTxtMobile.setText("Enter valid Mobile No(10 digits)");
                erTxtName.setText("");
                erTxtNIC.setText("");
                erTxtDOB.setText("");
                erTxtEmail.setText("");
            }else if(txtDOB.getText().toString().equals("Select a Date")) {
                erTxtDOB.setTextColor(Color.RED);
                erTxtDOB.setText("Select a date");
                erTxtName.setText("");
                erTxtNIC.setText("");
                erTxtMobile.setText("");
                erTxtEmail.setText("");
            }else if((txtEmail.equals(""))||(!emailMatch.matches())){
                erTxtEmail.setTextColor(Color.RED);
                erTxtEmail.setText("Enter valid Email");
                erTxtName.setText("");
                erTxtMobile.setText("");
                erTxtDOB.setText("");
                erTxtNIC.setText("");
            }else{
                if(addMember()) {
                    Toast.makeText(getApplicationContext(),"Adding member Successfully!!!",Toast.LENGTH_LONG).show();
                    Intent adminMain = new Intent(this, MainAdmin.class);
                    startActivity(adminMain);
                }else {
                    Toast.makeText(getApplicationContext(), "Cannot add member!!!", Toast.LENGTH_LONG).show();
                    erTxtNIC.setTextColor(Color.RED);
                    erTxtNIC.setText("NIC already used");
                    erTxtMobile.setText("");
                    erTxtEmail.setText("");
                    erTxtDOB.setText("");
                    erTxtName.setText("");
                }
            }
        }
    }

    private boolean addMember() {
        String nic = txtNIC.getText().toString();
        String name = txtName.getText().toString();
        String mobileNo = txtMobile.getText().toString();
        String dob = txtDOB.getText().toString();
        String address = txtAddress.getText().toString();
        String email = txtEmail.getText().toString();

        return (dbHelper.addInfoToAccountHolder(nic,name,mobileNo,dob,address,email));
    }
}
