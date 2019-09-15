package com.example.mad_miniproject.DB_Files;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BankSystem1.db";

    public DBHelper(Context context){super(context,DATABASE_NAME,null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ACCOUNT_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.Account.TABLE_NAME+" ("+
                        BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+" INTEGER PRIMARY KEY,"+
                        BankMaster.Account.COLUMN_NAME_ACCOUNTTYPE+" TEXT,"+
                        BankMaster.Account.COLUMN_NAME_OPENEDDATE+" TEXT,"+
                        BankMaster.Account.COLUMN_NAME_RELATIONSHIP+" TEXT,"+
                        BankMaster.Account.COLUMN_NAME_BALANCE+" REAL,"+
                        BankMaster.Account.COLUMN_NAME_PRODUCTNAME+" TEXT,"+
                        BankMaster.Account.COLUMN_NAME_BRANCH+" TEXT)";

        String ACCOUNTHOLDER_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.AccountHolder.TABLE_NAME+" ("+
                        BankMaster.AccountHolder.COLUMN_NAME_NIC+" TEXT PRIMARY KEY,"+
                        BankMaster.AccountHolder.COLUMN_NAME_HOLDERNAME+" TEXT,"+
                        BankMaster.AccountHolder.COLUMN_NAME_MOBILENO+" TEXT,"+
                        BankMaster.AccountHolder.COLUMN_NAME_DOB+" TEXT,"+
                        BankMaster.AccountHolder.COLUMN_NAME_ADDRESS+" TEXT,"+
                        BankMaster.AccountHolder.COLUMN_NAME_EMAIL+" TEXT)";

        String LOGIN_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.Login.TABLE_NAME+" ("+
                        BankMaster.Login.COLUMN_NAME_USERNAME+" TEXT PRIMARY KEY,"+
                        BankMaster.Login.COLUMN_NAME_PASSWORD+" TEXT,"+
                        BankMaster.Login.COLUMN_NAME_NIC+" TEXT REFERENCES "+ BankMaster.AccountHolder.TABLE_NAME +"("+ BankMaster.AccountHolder.COLUMN_NAME_NIC +"))";

        String MONEYTRANSFER_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.MoneyTransfer.TABLE_NAME+" ("+
                        BankMaster.MoneyTransfer.COLUMN_NAME_TRANSFERID+" TEXT PRIMARY KEY,"+
                        BankMaster.MoneyTransfer.COLUMN_NAME_ACCOUNTNUMBER+" INTEGER REFERENCES "+ BankMaster.Account.TABLE_NAME+"("+ BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+"),"+
                        BankMaster.MoneyTransfer.COLUMN_NAME_TOACCOUNT+" INTEGER REFERENCES "+ BankMaster.Account.TABLE_NAME+"("+ BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+"),"+
                        BankMaster.MoneyTransfer.COLUMN_NAME_AMOUNT+" REAL,"+
                        BankMaster.MoneyTransfer.COLUMN_NAME_DATETIME+" TEXT)";

        String BILLPAYMENT_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.BillPayment.TABLE_NAME+" ("+
                        BankMaster.BillPayment.COLUMN_NAME_BILLID+" TEXT PRIMARY KEY,"+
                        BankMaster.BillPayment.COLUMN_NAME_ACCOUNTNUMBER+" INTEGER REFERENCES "+ BankMaster.Account.TABLE_NAME+"("+ BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+"),"+
                        BankMaster.BillPayment.COLUMN_NAME_BILLER+" TEXT,"+
                        BankMaster.BillPayment.COLUMN_NAME_BILLER_ACCOUNTNUMBER+" TEXT,"+
                        BankMaster.BillPayment.COLUMN_NAME_AMOUNT+" REAL,"+
                        BankMaster.BillPayment.COLUMN_NAME_DATETIME+" TEXT)";

        String ACCOUNT_HOLDER_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.Account_Holder.TABLE_NAME+" ("+
                        BankMaster.Account_Holder.COLUMN_NAME_NIC+" TEXT REFERENCES "+ BankMaster.AccountHolder.TABLE_NAME+"("+ BankMaster.AccountHolder.COLUMN_NAME_NIC+"),"+
                        BankMaster.Account_Holder.COLUMN_NAME_ACCOUNTNUMBER+" INTEGER REFERENCES "+ BankMaster.Account.TABLE_NAME+"("+ BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+"),"+
                        "PRIMARY KEY ("+BankMaster.Account_Holder.COLUMN_NAME_NIC+", "+BankMaster.Account_Holder.COLUMN_NAME_ACCOUNTNUMBER+"))";

        String LOAN_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.Loan.TABLE_NAME+" ("+
                        BankMaster.Loan.COLUMN_NAME_LOANID+" TEXT PRIMARY KEY,"+
                        BankMaster.Loan.COLUMN_NAME_NIC+" TEXT REFERENCES "+ BankMaster.AccountHolder.TABLE_NAME+"("+ BankMaster.AccountHolder.COLUMN_NAME_NIC+"),"+
                        BankMaster.Loan.COLUMN_NAME_TYPE+" TEXT,"+
                        BankMaster.Loan.COLUMN_NAME_AMOUNT+" REAL,"+
                        BankMaster.Loan.COLUMN_NAME_APPROVED_DATE+" TEXT,"+
                        BankMaster.Loan.COLUMN_NAME_DURATION+" INTEGER,"+
                        BankMaster.Loan.COLUMN_NAME_INTEREST_RATE+" REAL)";

        /*String TRANSACTION_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.Transaction.TABLE_NAME+" ("+
                        BankMaster.Transaction.COLUMN_NAME_TRANSACTIONID+" TEXT PRIMARY KEY,"+
                        BankMaster.Transaction.COLUMN_NAME_ACCOUNTNUMBER+" INTEGER REFERENCES "+ BankMaster.Account.TABLE_NAME+"("+ BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+"),"+
                        BankMaster.Transaction.COLUMN_NAME_TRANSACTIONDATE+" TEXT,"+
                        BankMaster.Transaction.COLUMN_NAME_DEBIT+" REAL,"+
                        BankMaster.Transaction.COLUMN_NAME_CREDIT+" REAL,"+
                        BankMaster.Transaction.COLUMN_NAME_BALANCE+" REAL)";*/

        String ADMIN_INSERT_VALUE_QUERY =
                "INSERT INTO "+ BankMaster.Login.TABLE_NAME+"("+ BankMaster.Login.COLUMN_NAME_USERNAME+", "+ BankMaster.Login.COLUMN_NAME_PASSWORD +")"+
                "VALUES ('Admin','Bara%@&321')";

        db.execSQL(ACCOUNT_CREATE_QUERY);
        db.execSQL(ACCOUNTHOLDER_CREATE_QUERY);
        db.execSQL(LOGIN_CREATE_QUERY);
        db.execSQL(MONEYTRANSFER_CREATE_QUERY);
        db.execSQL(BILLPAYMENT_CREATE_QUERY);
        db.execSQL(ACCOUNT_HOLDER_CREATE_QUERY);
        db.execSQL(LOAN_CREATE_QUERY);
        //db.execSQL(TRANSACTION_CREATE_QUERY);
        db.execSQL(ADMIN_INSERT_VALUE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addInfoToAccount(int accountNo, String accType,String openedDate,String realationship,double balance,String productName,String branch){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER,accountNo);
        values.put(BankMaster.Account.COLUMN_NAME_ACCOUNTTYPE,accType);
        values.put(BankMaster.Account.COLUMN_NAME_OPENEDDATE,openedDate);
        values.put(BankMaster.Account.COLUMN_NAME_RELATIONSHIP,realationship);
        values.put(BankMaster.Account.COLUMN_NAME_BALANCE,balance);
        values.put(BankMaster.Account.COLUMN_NAME_PRODUCTNAME,productName);
        values.put(BankMaster.Account.COLUMN_NAME_BRANCH,branch);

        long newRowId = db.insert(BankMaster.Account.TABLE_NAME,null,values);

        if(newRowId>=1)
            return true;
        else
            return false;
    }

    public boolean addInfoToAccountHolder(String nic, String name,String mobileNo,String dob,String address,String email){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BankMaster.AccountHolder.COLUMN_NAME_NIC,nic);
        values.put(BankMaster.AccountHolder.COLUMN_NAME_HOLDERNAME,name);
        values.put(BankMaster.AccountHolder.COLUMN_NAME_MOBILENO,mobileNo);
        values.put(BankMaster.AccountHolder.COLUMN_NAME_DOB,dob);
        values.put(BankMaster.AccountHolder.COLUMN_NAME_ADDRESS,address);
        values.put(BankMaster.AccountHolder.COLUMN_NAME_EMAIL,email);

        long newRowId = db.insert(BankMaster.AccountHolder.TABLE_NAME,null,values);

        if(newRowId>=1)
            return true;
        else
            return false;
    }

    public boolean addInfoToLogin(String username, String password,String nic){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BankMaster.Login.COLUMN_NAME_USERNAME,username);
        values.put(BankMaster.Login.COLUMN_NAME_PASSWORD,password);
        values.put(BankMaster.Login.COLUMN_NAME_NIC,nic);

        long newRowId = db.insert(BankMaster.Login.TABLE_NAME,null,values);

        if(newRowId>=1)
            return true;
        else
            return false;
    }

    public boolean addInfoToMoneyTransfer(String tranID, int accNo,int toAccount,double amount,String transMode,String dateTime){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BankMaster.MoneyTransfer.COLUMN_NAME_TRANSFERID,tranID);
        values.put(BankMaster.MoneyTransfer.COLUMN_NAME_ACCOUNTNUMBER,accNo);
        values.put(BankMaster.MoneyTransfer.COLUMN_NAME_TOACCOUNT,toAccount);
        values.put(BankMaster.MoneyTransfer.COLUMN_NAME_AMOUNT,amount);
        values.put(BankMaster.MoneyTransfer.COLUMN_NAME_DATETIME,dateTime);

        long newRowId = db.insert(BankMaster.MoneyTransfer.TABLE_NAME,null,values);

        if(newRowId>=1)
            return true;
        else
            return false;
    }

    public boolean addInfoToBillPayment(String billID, int accNo, String biller, String billerAccNo, double amount, String dateTime){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BankMaster.BillPayment.COLUMN_NAME_BILLID,billID);
        values.put(BankMaster.BillPayment.COLUMN_NAME_ACCOUNTNUMBER,accNo);
        values.put(BankMaster.BillPayment.COLUMN_NAME_BILLER,biller);
        values.put(BankMaster.BillPayment.COLUMN_NAME_BILLER_ACCOUNTNUMBER,billerAccNo);
        values.put(BankMaster.BillPayment.COLUMN_NAME_AMOUNT,amount);
        values.put(BankMaster.BillPayment.COLUMN_NAME_DATETIME,dateTime);

        long newRowId = db.insert(BankMaster.BillPayment.TABLE_NAME,null,values);

        if(newRowId>=1)
            return true;
        else
            return false;
    }

    public boolean addInfoToACCOUNT_HOLDER(String nic, int accountNo){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BankMaster.Account_Holder.COLUMN_NAME_NIC,nic);
        values.put(BankMaster.Account_Holder.COLUMN_NAME_ACCOUNTNUMBER,accountNo);

        long newRowId = db.insert(BankMaster.Account_Holder.TABLE_NAME,null,values);

        if(newRowId>=1)
            return true;
        else
            return false;
    }

    public boolean addInfoToLoan(String loanID, String nic,String type,double amount,String approvedDate,int duration,double intRate){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BankMaster.Loan.COLUMN_NAME_LOANID,loanID);
        values.put(BankMaster.Loan.COLUMN_NAME_NIC,nic);
        values.put(BankMaster.Loan.COLUMN_NAME_TYPE,type);
        values.put(BankMaster.Loan.COLUMN_NAME_AMOUNT,amount);
        values.put(BankMaster.Loan.COLUMN_NAME_APPROVED_DATE,approvedDate);
        values.put(BankMaster.Loan.COLUMN_NAME_DURATION,duration);
        values.put(BankMaster.Loan.COLUMN_NAME_INTEREST_RATE,intRate);


        long newRowId = db.insert(BankMaster.Loan.TABLE_NAME,null,values);

        if(newRowId>=1)
            return true;
        else
            return false;
    }

    public boolean addInfoToTransaction(String transAcID, int accountNo, String transactiondate, double debit, double credit, double balance){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BankMaster.Transaction.COLUMN_NAME_TRANSACTIONID,transAcID);
        values.put(BankMaster.Transaction.COLUMN_NAME_ACCOUNTNUMBER,accountNo);
        values.put(BankMaster.Transaction.COLUMN_NAME_TRANSACTIONDATE,transactiondate);
        values.put(BankMaster.Transaction.COLUMN_NAME_DEBIT,debit);
        values.put(BankMaster.Transaction.COLUMN_NAME_CREDIT,credit);
        values.put(BankMaster.Transaction.COLUMN_NAME_BALANCE,balance);

        long newRowId = db.insert(BankMaster.Transaction.TABLE_NAME,null,values);

        if(newRowId>=1)
            return true;
        else
            return false;
    }
}
