package com.example.mad_miniproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String exp_edu = "^([1-8][0-9]{5}|9[0-8][0-9]{4}|99[0-8][0-9]{3}|999[0-8][0-9]{2}|9999[0-8][0-9]|99999[0-9]|1[0-9]{6}|2000000)$";
        String exp_home = "^([5-8][0-9]{5}|9[0-8][0-9]{4}|99[0-8][0-9]{3}|999[0-8][0-9]{2}|9999[0-8][0-9]|99999[0-9]|[1-8][0-9]{6}|9[0-8][0-9]{5}|99[0-8][0-9]{4}|999[0-8][0-9]{3}|9999[0-8][0-9]{2}|99999[0-8][0-9]|999999[0-9]|[1-6][0-9]{7}|7[0-4][0-9]{6}|75000000)$";
        String exp_persnl = "^([2-8][0-9]{5}|9[0-8][0-9]{4}|99[0-8][0-9]{3}|999[0-8][0-9]{2}|9999[0-8][0-9]|99999[0-9]|[12][0-9]{6}|3000000)$";
        String exp_busns = "^([5-8][0-9]{4}|9[0-8][0-9]{3}|99[0-8][0-9]{2}|999[0-8][0-9]|9999[0-9]|[1-8][0-9]{5}|9[0-8][0-9]{4}|99[0-8][0-9]{3}|999[0-8][0-9]{2}|9999[0-8][0-9]|99999[0-9]|[1-3][0-9]{6}|4000000)$";

        String value1 = txtAmount.getText().toString();

        Matcher match_edu = Pattern.compile(exp_edu).matcher(value1);
        Matcher match_home = Pattern.compile(exp_home).matcher(value1);
        Matcher match_persnl = Pattern.compile(exp_persnl).matcher(value1);
        Matcher match_busns = Pattern.compile(exp_busns).matcher(value1);

        String amount = txtAmount.getText().toString();
        if(view.getId()==R.id.btnLoanApply){
            String type = spnType.getSelectedItem().toString();
            String amt = txtAmount.getText().toString();
            if(type.equals("Choose...")){
                errorType.setTextColor(Color.RED);
                errorType.setText("Choose a loan type");
                errorDuration.setText("");
                errorloanAmount.setText("");
            }else if(type.equals("Education Loan") && (!match_edu.matches()) ){
                errorloanAmount.setTextColor(Color.RED);
                errorloanAmount.setText("Enter loan amount for Education! LKR (100,000 - 2,000,000)");
                errorType.setText("");
                errorDuration.setText("");
            }else if(type.equals("Home Loan") && (!match_home.matches()) ) {
                errorloanAmount.setTextColor(Color.RED);
                errorloanAmount.setText("Enter loan amount for Home! LKR (500,000 - 75,000,000)");
                errorType.setText("");
                errorDuration.setText("");
            }else if(type.equals("Personal Loan") && (!match_persnl.matches()) ) {
                errorloanAmount.setTextColor(Color.RED);
                errorloanAmount.setText("Enter loan amount for Personal! LKR (200,000 - 3,000,000)");
                errorType.setText("");
                errorDuration.setText("");
            } else if(type.equals("Business Loan") && (!match_busns.matches()) ) {
                errorloanAmount.setTextColor(Color.RED);
                errorloanAmount.setText("Enter loan amount for Business! LKR (50,000 - 4,000,000)");
                errorType.setText("");
                errorDuration.setText("");
            }else if(spnDuration.getSelectedItem().toString().equals("Choose...")){
                errorDuration.setTextColor(Color.RED);
                errorDuration.setText("Choose a duration");
                errorloanAmount.setText("");
                errorType.setText("");
            }else{

                AlertDialog.Builder builder = new AlertDialog.Builder(LoanApply.this);
                builder.setCancelable(true);
                builder.setTitle("Apply");
                builder.setMessage("Are you sure you want to Apply?");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent email = new Intent(Intent.ACTION_SEND);
                        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"rushdhyahamed98@gmail.com"});
                        //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
                        //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
                        email.putExtra(Intent.EXTRA_SUBJECT, "Apply for a Loan");
                        email.putExtra(Intent.EXTRA_TEXT, "Loan type: " +spnType.getSelectedItem().toString() + "\n" + "Loan Amount: " +txtAmount.getText()+ "\n" + "Duration for re-settle: " +spnDuration.getSelectedItem().toString());


                        //need this to prompts email client only
                        email.setType("message/rfc822");

                        startActivity(Intent.createChooser(email, "Choose an Email client :"));

                    }
                });
                builder.show();

            }

        }
    }

    private boolean addLoan(){
        String loanType = spnType.getSelectedItem().toString();
        double amount = Double.parseDouble(txtAmount.getText().toString());
        String approvedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String firstTwo = spnDuration.getSelectedItem().toString().substring(0,2);
        int duration = Integer.parseInt(firstTwo);
        double interestRate = Double.parseDouble(txtIntRate.getText().toString());

        ArrayList<Integer> loan = dbHelper.readLastLoanID();
        if(loan.isEmpty()){
            id=123;
        }else{
            int preAccNumber = loan.get(0);
            id = preAccNumber+1;
        }

        return (dbHelper.addInfoToLoan(id,nic,loanType,amount,approvedDate,duration,interestRate));
    }

}
