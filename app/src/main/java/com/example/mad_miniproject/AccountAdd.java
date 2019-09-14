package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class AccountAdd extends AppCompatActivity {

    private Spinner accType,relation;
    private TextView addDate;
    private DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_add);

        this.addDate = (TextView) findViewById(R.id.txtDate);
        accType = (Spinner) findViewById(R.id.spnAccType);
        relation = (Spinner) findViewById(R.id.spnRelationship);

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
                String date = year+"/"+month+"/"+day;
                addDate.setText(date);
            }
        };
    }
}
