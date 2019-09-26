package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class LoanApply extends AppCompatActivity implements View.OnClickListener {

    private Button btnApply;
    DBHelper dbHelper;
    public Spinner spnType,spnDuration;
    public EditText txtAmount,txtIntRate;
    public String nic;
    public int id;
    private TextView errorType,errorloanAmount,errorDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_apply);


        dbHelper = new DBHelper(this);

        btnApply = (Button) findViewById(R.id.btnLoanApply);
        spnType = findViewById(R.id.spnLoanType);
        spnDuration = findViewById(R.id.spnLoanDuration);
        txtAmount = findViewById(R.id.txtLoanAmount);
        txtIntRate = findViewById(R.id.txtIntRate);

        errorType=findViewById(R.id.erTxtLoanType);
        errorloanAmount = findViewById(R.id.erTxtLoanAmount);
        errorDuration = findViewById(R.id.erTxtLoanDuration);

        ArrayAdapter<String> loanTypeAdapter = new ArrayAdapter<String>(LoanApply.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.loanType));

        loanTypeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnType.setAdapter(loanTypeAdapter);

        ArrayAdapter<String> loanDurationAdapter = new ArrayAdapter<String>(LoanApply.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.loanDuration));

        loanDurationAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnDuration.setAdapter(loanDurationAdapter);

        Intent intent = getIntent();
        nic = intent.getStringExtra(MainActivity.NICOFHOLDER);

        spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String loanType = spnType.getSelectedItem().toString();
                switch(loanType){
                    case "Education Loan":
                        txtIntRate.setText("5");
                        break;
                    case "Home Loan":
                        txtIntRate.setText("10");
                        break;
                    case "Personal Loan":
                        txtIntRate.setText("12");
                        break;
                    case "Business Loan":
                        txtIntRate.setText("15");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnApply.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnLoanApply){
            if(spnType.getSelectedItem().toString().equals("Choose...")){
                errorType.setTextColor(Color.RED);
                errorType.setText("Choose a loan type");
            }else if(spnDuration.getSelectedItem().toString().equals("Choose...")){
                errorDuration.setTextColor(Color.RED);
                errorDuration.setText("Choose a duration");
            }else if(txtAmount.getText().toString().equals("")){
                errorloanAmount.setTextColor(Color.RED);
                errorloanAmount.setText("Enter a amount");
            }else{
                addLoan();
            }

        }
    }

    private void addLoan(){
        String loanType = spnType.getSelectedItem().toString();
        double amount = Double.parseDouble(txtAmount.getText().toString());
        String approvedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String firstTwo = spnDuration.getSelectedItem().toString().substring(0,2);
        int duration = Integer.parseInt(firstTwo);
        double interestRate = Double.parseDouble(txtIntRate.getText().toString());

        ArrayList<Integer> loan = dbHelper.readLastLoanID();
        String lastID = loan.get(0).toString();
        if(lastID.equals("")){
            id=123;
        }else{
            int preAccNumber = Integer.parseInt(lastID);
            id = preAccNumber+1;
        }

        if(dbHelper.addInfoToLoan(id,nic,loanType,amount,approvedDate,duration,interestRate)) {
            Toast.makeText(getApplicationContext(), "Approved your Loan", Toast.LENGTH_LONG).show();
            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);

        }else
            Toast.makeText(getApplicationContext(),"Cannot approve",Toast.LENGTH_LONG).show();
    }

}
