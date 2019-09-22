package com.example.mad_miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mad_miniproject.DB_Files.DBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String NICOFHOLDER = "com.example.HOLDERNIC";
    public static final String UNOFHODER = "com.example.HOLDERUN";

    Button btnAccount,btnLoan,btnMoneyTrans,btnBillPay;
    public String nic,un;
    TextView txtHolderNIC;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        this.btnAccount = (Button) findViewById(R.id.btnAccount);
        this.btnBillPay = (Button) findViewById(R.id.btnBillPayment);
        this.btnMoneyTrans = (Button) findViewById(R.id.btnMoneyTransfer);
        this.btnLoan = (Button) findViewById(R.id.btnLoan);

        txtHolderNIC = findViewById(R.id.txtHolderNIC);

        btnAccount.setOnClickListener(this);
        btnBillPay.setOnClickListener(this);
        btnMoneyTrans.setOnClickListener(this);
        btnLoan.setOnClickListener(this);
        txtHolderNIC.setOnClickListener(this);

        Intent intent = getIntent();
        nic = intent.getStringExtra(Login.HOLDER_NIC);
        un = intent.getStringExtra(Login.HOLDER_UN);

        ArrayList<String> name = new ArrayList<>();
        name = dbHelper.getUserName(nic);

        txtHolderNIC.setText(name.get(0));
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnAccount){
            Intent accountMain = new Intent(this,Account_Main.class);
            accountMain.putExtra(NICOFHOLDER,nic);
            startActivity(accountMain);
        }else if(view.getId() == R.id.btnBillPayment){
            Intent payBill = new Intent(this,PayBill.class);
            payBill.putExtra(NICOFHOLDER,nic);
            startActivity(payBill);
        }else if(view.getId()==R.id.btnMoneyTransfer){
            Intent moneyTrans = new Intent(this,MoneyTransMain.class);
            moneyTrans.putExtra(NICOFHOLDER,nic);
            startActivity(moneyTrans);
        }else if(view.getId()==R.id.btnLoan){
            Intent loan = new Intent(this,LoanApply.class);
            loan.putExtra(NICOFHOLDER,nic);
            startActivity(loan);
        }else if(view.getId()==R.id.txtHolderNIC){
            Intent profile = new Intent(this,UserProfile.class);
            profile.putExtra(UNOFHODER,un);
            startActivity(profile);
        }
    }
}
