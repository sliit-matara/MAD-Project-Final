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

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountAdd extends AppCompatActivity implements View.OnClickListener {

    private Spinner accType,relation;
    private EditText txtCurBal,txtProdName,txtBranch;
    private TextView addDate,erTxtAccType,erTxtDate,erTxtRelation,erTxtCurBal;
    private DatePickerDialog.OnDateSetListener setListener;
    private Button btnAddAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_add);

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
            //String numericExp = "^[0-9]*(?:\\.[0-9]*)?$";

            //Matcher balMatch = Pattern.compile(numericExp).matcher(txtCurBal.getText().toString());

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
                Intent adminMain = new Intent(this,MainAdmin.class);
                startActivity(adminMain);
            }
        }
    }


}
