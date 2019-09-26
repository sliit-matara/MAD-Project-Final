package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AccountAdd extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_MESSAGE = "com.example.AccNum.ACCOUNTNUMBER";

    private Spinner accType,relation;
    private EditText txtCurBal,txtProdName,txtBranch;
    private TextView addDate,erTxtAccType,erTxtDate,erTxtRelation,erTxtCurBal;
    private DatePickerDialog.OnDateSetListener setListener;
    private Button btnAddAccount;
    private int accountNumber;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_add);

        dbHelper = new DBHelper(this);

        addDate = (TextView) findViewById(R.id.txtDate);
        erTxtAccType = (TextView) findViewById(R.id.erTxtAccType);
        erTxtDate = (TextView) findViewById(R.id.erTxtDate);
        erTxtRelation = (TextView) findViewById(R.id.erTxtRelation);
        erTxtCurBal = (TextView) findViewById(R.id.erTxtCurBal);

        accType = (Spinner) findViewById(R.id.spnAccType);
        relation = (Spinner) findViewById(R.id.spnRelationship);

        txtCurBal = (EditText) findViewById(R.id.txtCurBalance);
        txtProdName = (EditText) findViewById(R.id.txtProductName);
        txtBranch = (EditText) findViewById(R.id.txtBranch);

        btnAddAccount = (Button) findViewById(R.id.btnAddAccount);

        //Spinner
        ArrayAdapter<String> accTypeAdapter = new ArrayAdapter<String>(AccountAdd.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.accType));

        accTypeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        accType.setAdapter(accTypeAdapter);

        ArrayAdapter<String> relationAdapter = new ArrayAdapter<String>(AccountAdd.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.relationship));

        relationAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        relation.setAdapter(relationAdapter);

        //Calander
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AccountAdd.this,android.R.style.Theme,
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
                addDate.setText(date);
            }
        };

        btnAddAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAddAccount){
            if(accType.getSelectedItem().toString().equals("Choose...")){
                erTxtAccType.setTextColor(Color.RED);
                erTxtAccType.setText("Please select one");
                erTxtDate.setText("");
                erTxtRelation.setText("");
                erTxtCurBal.setText("");
            }else if(addDate.getText().toString().equals("Select a date")){
                erTxtDate.setTextColor(Color.RED);
                erTxtDate.setText("Please select a date");
                erTxtAccType.setText("");
                erTxtRelation.setText("");
                erTxtCurBal.setText("");
            }else if(relation.getSelectedItem().toString().equals("Choose...")){
                erTxtRelation.setTextColor(Color.RED);
                erTxtRelation.setText("Please select one");
                erTxtAccType.setText("");
                erTxtDate.setText("");
                erTxtCurBal.setText("");
            }else if(txtCurBal.getText().toString().equals("")){
                erTxtCurBal.setTextColor(Color.RED);
                erTxtCurBal.setText("Please enter valid balance");
                erTxtAccType.setText("");
                erTxtDate.setText("");
                erTxtRelation.setText("");
            }else{
                if(addAccount()) {
                    Toast.makeText(getApplicationContext(),"Account add Successfully!!!",Toast.LENGTH_LONG).show();
                    if (relation.getSelectedItem().toString().equals("Single")) {
                        Intent addSingleMem = new Intent(this, AccountSingle.class);
                        addSingleMem.putExtra(EXTRA_MESSAGE, Integer.toString(accountNumber));
                        startActivity(addSingleMem);
                    } else if (relation.getSelectedItem().toString().equals("Joint")) {
                        Intent addMultMem = new Intent(this, AccountJoined.class);
                        addMultMem.putExtra(EXTRA_MESSAGE, Integer.toString(accountNumber));
                        startActivity(addMultMem);
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Cannot add account",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private boolean addAccount(){
        String accountType = accType.getSelectedItem().toString();
        String openDate = addDate.getText().toString();
        String relationship = relation.getSelectedItem().toString();
        String bal = txtCurBal.getText().toString();
        double balance = Float.parseFloat(bal);
        String productName = txtProdName.getText().toString();
        String branch = txtBranch.getText().toString();

        ArrayList<Integer> accNo = dbHelper.readLastAccountNumber();
        if(accNo.isEmpty()){
            accountNumber=123456789;
        }else{
            int preAccNumber = accNo.get(0);
            accountNumber = preAccNumber+1;
        }

        return dbHelper.addInfoToAccount(accountNumber,accountType,openDate,relationship,balance,productName,branch);
    }

}
