package com.example.mad_miniproject.DB_Files;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Bank1.db";

    public DBHelper(Context context){super(context,DATABASE_NAME,null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ACCOUNT_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.Account.TABLE_NAME+" ("+
                        BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+" INTEGER PRIMARY KEY,"+
                        BankMaster.Account.COLUMN_NAME_ACCOUNTTYPE+" TEXT NOT NULL,"+
                        BankMaster.Account.COLUMN_NAME_OPENEDDATE+" TEXT NOT NULL,"+
                        BankMaster.Account.COLUMN_NAME_RELATIONSHIP+" TEXT NOT NULL,"+
                        BankMaster.Account.COLUMN_NAME_BALANCE+" REAL NOT NULL,"+
                        BankMaster.Account.COLUMN_NAME_PRODUCTNAME+" TEXT,"+
                        BankMaster.Account.COLUMN_NAME_BRANCH+" TEXT)";

        String ACCOUNTHOLDER_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.AccountHolder.TABLE_NAME+" ("+
                        BankMaster.AccountHolder.COLUMN_NAME_NIC+" TEXT PRIMARY KEY,"+
                        BankMaster.AccountHolder.COLUMN_NAME_HOLDERNAME+" TEXT NOT NULL,"+
                        BankMaster.AccountHolder.COLUMN_NAME_MOBILENO+" TEXT NOT NULL,"+
                        BankMaster.AccountHolder.COLUMN_NAME_DOB+" TEXT NOT NULL,"+
                        BankMaster.AccountHolder.COLUMN_NAME_ADDRESS+" TEXT,"+
                        BankMaster.AccountHolder.COLUMN_NAME_EMAIL+" TEXT NOT NULL)";

        String LOGIN_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.Login.TABLE_NAME+" ("+
                        BankMaster.Login.COLUMN_NAME_USERNAME+" TEXT PRIMARY KEY,"+
                        BankMaster.Login.COLUMN_NAME_PASSWORD+" TEXT NOT NULL,"+
                        BankMaster.Login.COLUMN_NAME_NIC+" TEXT REFERENCES "+ BankMaster.AccountHolder.TABLE_NAME +"("+ BankMaster.AccountHolder.COLUMN_NAME_NIC +"))";

        String MONEYTRANSFER_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.MoneyTransfer.TABLE_NAME+" ("+
                        BankMaster.MoneyTransfer.COLUMN_NAME_TRANSFERID+" INTEGER PRIMARY KEY,"+
                        BankMaster.MoneyTransfer.COLUMN_NAME_ACCOUNTNUMBER+" INTEGER REFERENCES "+ BankMaster.Account.TABLE_NAME+"("+ BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+") NOT NULL,"+
                        BankMaster.MoneyTransfer.COLUMN_NAME_TOACCOUNT+" INTEGER REFERENCES "+ BankMaster.Account.TABLE_NAME+"("+ BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+") NOT NULL,"+
                        BankMaster.MoneyTransfer.COLUMN_NAME_AMOUNT+" REAL NOT NULL,"+
                        BankMaster.MoneyTransfer.COLUMN_NAME_DATETIME+" TEXT NOT NULL)";

        String BILLPAYMENT_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.BillPayment.TABLE_NAME+" ("+
                        BankMaster.BillPayment.COLUMN_NAME_BILLID+" INTEGER PRIMARY KEY,"+
                        BankMaster.BillPayment.COLUMN_NAME_ACCOUNTNUMBER+" INTEGER REFERENCES "+ BankMaster.Account.TABLE_NAME+"("+ BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+") NOT NULL,"+
                        BankMaster.BillPayment.COLUMN_NAME_BILLER+" TEXT NOT NULL,"+
                        BankMaster.BillPayment.COLUMN_NAME_BILLER_ACCOUNTNUMBER+" TEXT NOT NULL,"+
                        BankMaster.BillPayment.COLUMN_NAME_AMOUNT+" REAL NOT NULL,"+
                        BankMaster.BillPayment.COLUMN_NAME_DATETIME+" TEXT NOT NULL)";

        String ACCOUNT_HOLDER_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.Account_Holder.TABLE_NAME+" ("+
                        BankMaster.Account_Holder.COLUMN_NAME_NIC+" TEXT REFERENCES "+ BankMaster.AccountHolder.TABLE_NAME+"("+ BankMaster.AccountHolder.COLUMN_NAME_NIC+"),"+
                        BankMaster.Account_Holder.COLUMN_NAME_ACCOUNTNUMBER+" INTEGER REFERENCES "+ BankMaster.Account.TABLE_NAME+"("+ BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+"),"+
                        "PRIMARY KEY ("+BankMaster.Account_Holder.COLUMN_NAME_NIC+", "+BankMaster.Account_Holder.COLUMN_NAME_ACCOUNTNUMBER+"))";

        String LOAN_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.Loan.TABLE_NAME+" ("+
                        BankMaster.Loan.COLUMN_NAME_LOANID+" INTEGER PRIMARY KEY,"+
                        BankMaster.Loan.COLUMN_NAME_NIC+" TEXT REFERENCES "+ BankMaster.AccountHolder.TABLE_NAME+"("+ BankMaster.AccountHolder.COLUMN_NAME_NIC+") NOT NULL,"+
                        BankMaster.Loan.COLUMN_NAME_TYPE+" TEXT NOT NULL,"+
                        BankMaster.Loan.COLUMN_NAME_AMOUNT+" REAL NOT NULL,"+
                        BankMaster.Loan.COLUMN_NAME_APPROVED_DATE+" TEXT NOT NULL,"+
                        BankMaster.Loan.COLUMN_NAME_DURATION+" INTEGER NOT NULL,"+
                        BankMaster.Loan.COLUMN_NAME_INTEREST_RATE+" REAL)";

        String TRANSACTION_CREATE_QUERY =
                "CREATE TABLE "+ BankMaster.Transaction.TABLE_NAME+" ("+
                        BankMaster.Transaction.COLUMN_NAME_TRANSACTIONID+" INTEGER PRIMARY KEY,"+
                        BankMaster.Transaction.COLUMN_NAME_ACCOUNTNUMBER+" INTEGER REFERENCES "+ BankMaster.Account.TABLE_NAME+"("+ BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+") NOT NULL,"+
                        BankMaster.Transaction.COLUMN_NAME_MODE+" TEXT,"+
                        BankMaster.Transaction.COLUMN_NAME_TRANSACTIONDATE+" TEXT NOT NULL,"+
                        BankMaster.Transaction.COLUMN_NAME_DEBIT+" REAL,"+
                        BankMaster.Transaction.COLUMN_NAME_CREDIT+" REAL,"+
                        BankMaster.Transaction.COLUMN_NAME_BALANCE+" REAL NOT NULL)";

        String ADMIN_INSERT_VALUE_QUERY =
                "INSERT INTO "+ BankMaster.Login.TABLE_NAME+"("+ BankMaster.Login.COLUMN_NAME_USERNAME+", "+ BankMaster.Login.COLUMN_NAME_PASSWORD +")"+
                "VALUES ('Admin','Bara%@&321')";

        String INSERT_QUERY_MEMBER =
                "INSERT INTO "+ BankMaster.AccountHolder.TABLE_NAME+" VALUES ('990291390V', 'Bojitha Mindula', '0775631456','1999-01-29','Galle','boji@gamil.com')";

        String INSERT_QUERY_ACCOUNT1 =
                "INSERT INTO "+ BankMaster.Account.TABLE_NAME+" VALUES (123456789,'Saving','2001-02-03','Single',250636.00,'Vishwa','Weligama')";

        String INSERT_QUERY_ACCOUNT2=
                "INSERT INTO "+ BankMaster.Account.TABLE_NAME+" VALUES (123456790,'Saving','2004-02-03','Single',250640.00,'Vishwa','Weligama')";

        String INSERT_QUERY_ACCOUNT_HOLDER1 =
                "INSERT INTO "+ BankMaster.Account_Holder.TABLE_NAME+" VALUES ('990291390V',123456789)";

        String INSERT_QUERY_ACCOUNT_HOLDER2 =
                "INSERT INTO "+ BankMaster.Account_Holder.TABLE_NAME+" VALUES ('990291390V',123456790)";

        String INSERT_QUERY_LOGIN_USER =
                "INSERT INTO "+ BankMaster.Login.TABLE_NAME+" VALUES ('Bojitha','Bojitha','990291390V')";

        String INSERT_QUERY_MONEY_TRANSFER =
                "INSERT INTO "+ BankMaster.MoneyTransfer.TABLE_NAME+" VALUES (12345,123456789,123456790,2000.00,'2016-02-04 18:20:18')";

        String INSERT_QUERY_BILL_PAYMENT =
                "INSERT INTO "+ BankMaster.BillPayment.TABLE_NAME+" VALUES(1234567,123456789,'Mobitel','0714500369',2000.00,'2017-10-20 12:12;12')";

        String INSERT_QUERY_LOAN =
                "INSERT INTO "+ BankMaster.Loan.TABLE_NAME+" VALUES(123,'990291390V','Student',200000.00,'2016-10-20',24,10)";

        String INSERT_QUERY_TRANSACTION =
                "INSERT INTO "+ BankMaster.Transaction.TABLE_NAME+" VALUES(1234567890,123456789,'Bill Payment','2016-02-04',2000.00,0.0,250636.00)";

        db.execSQL(ACCOUNT_CREATE_QUERY);
        db.execSQL(ACCOUNTHOLDER_CREATE_QUERY);
        db.execSQL(LOGIN_CREATE_QUERY);
        db.execSQL(MONEYTRANSFER_CREATE_QUERY);
        db.execSQL(BILLPAYMENT_CREATE_QUERY);
        db.execSQL(ACCOUNT_HOLDER_CREATE_QUERY);
        db.execSQL(LOAN_CREATE_QUERY);
        db.execSQL(TRANSACTION_CREATE_QUERY);
        db.execSQL(ADMIN_INSERT_VALUE_QUERY);
        db.execSQL(INSERT_QUERY_MEMBER);
        db.execSQL(INSERT_QUERY_ACCOUNT1);
        db.execSQL(INSERT_QUERY_ACCOUNT2);
        db.execSQL(INSERT_QUERY_ACCOUNT_HOLDER1);
        db.execSQL(INSERT_QUERY_ACCOUNT_HOLDER2);
        db.execSQL(INSERT_QUERY_LOGIN_USER);
        db.execSQL(INSERT_QUERY_MONEY_TRANSFER);
        db.execSQL(INSERT_QUERY_BILL_PAYMENT);
        db.execSQL(INSERT_QUERY_LOAN);
        db.execSQL(INSERT_QUERY_TRANSACTION);
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

    public boolean addInfoToMoneyTransfer(int tranID, int accNo,int toAccount,double amount,String dateTime){
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

    public boolean addInfoToBillPayment(int billID, int accNo, String biller, String billerAccNo, double amount, String dateTime){
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

    public boolean addInfoToLoan(int loanID, String nic,String type,double amount,String approvedDate,int duration,double intRate){
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

    public boolean addInfoToTransaction(int transAcID, int accountNo,String mode, String transactionDate, double debit, double credit, double balance){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BankMaster.Transaction.COLUMN_NAME_TRANSACTIONID,transAcID);
        values.put(BankMaster.Transaction.COLUMN_NAME_ACCOUNTNUMBER,accountNo);
        values.put(BankMaster.Transaction.COLUMN_NAME_MODE,mode);
        values.put(BankMaster.Transaction.COLUMN_NAME_TRANSACTIONDATE,transactionDate);
        values.put(BankMaster.Transaction.COLUMN_NAME_DEBIT,debit);
        values.put(BankMaster.Transaction.COLUMN_NAME_CREDIT,credit);
        values.put(BankMaster.Transaction.COLUMN_NAME_BALANCE,balance);

        long newRowId = db.insert(BankMaster.Transaction.TABLE_NAME,null,values);

        if(newRowId>=1)
            return true;
        else
            return false;
    }

    public ArrayList<Integer> readLastAccountNumber(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER
        };

        String sortOrder = BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER +" DESC";

        Cursor cursor = db.query(
                BankMaster.Account.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Integer> accountNumbers = new ArrayList<>();

        while (cursor.moveToNext()){
            int accountNumber = cursor.getInt(cursor.getColumnIndexOrThrow(BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER));
            accountNumbers.add(accountNumber);
        }
        cursor.close();
        return accountNumbers;
    }

    public ArrayList<Integer> readLastPayBillID(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BankMaster.BillPayment.COLUMN_NAME_BILLID
        };

        String sortOrder = BankMaster.BillPayment.COLUMN_NAME_BILLID +" DESC";

        Cursor cursor = db.query(
                BankMaster.BillPayment.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Integer> payIds = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(BankMaster.BillPayment.COLUMN_NAME_BILLID));
            payIds.add(id);
        }
        cursor.close();
        return payIds;
    }

    public ArrayList<Integer> readLastTransferID(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BankMaster.MoneyTransfer.COLUMN_NAME_TRANSFERID
        };

        String sortOrder = BankMaster.MoneyTransfer.COLUMN_NAME_TRANSFERID +" DESC";

        Cursor cursor = db.query(
                BankMaster.MoneyTransfer.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Integer> transferIds = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(BankMaster.MoneyTransfer.COLUMN_NAME_TRANSFERID));
            transferIds.add(id);
        }
        cursor.close();
        return transferIds;
    }

    public ArrayList<Integer> readLastTransactionID(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BankMaster.Transaction.COLUMN_NAME_TRANSACTIONID
        };

        String sortOrder = BankMaster.Transaction.COLUMN_NAME_TRANSACTIONID +" DESC";

        Cursor cursor = db.query(
                BankMaster.Transaction.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Integer> transactionIds = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(BankMaster.Transaction.COLUMN_NAME_TRANSACTIONID));
            transactionIds.add(id);
        }
        cursor.close();
        return transactionIds;
    }

    public ArrayList<Integer> readLastLoanID(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BankMaster.Loan.COLUMN_NAME_LOANID
        };

        String sortOrder = BankMaster.Loan.COLUMN_NAME_LOANID +" DESC";

        Cursor cursor = db.query(
                BankMaster.Loan.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<Integer> loanIds = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(BankMaster.Loan.COLUMN_NAME_LOANID));
            loanIds.add(id);
        }
        cursor.close();
        return loanIds;
    }

    public ArrayList<String> getUnPwd(String uname,String pwd){
        SQLiteDatabase db = getReadableDatabase();

        String read = "SELECT * FROM "+ BankMaster.Login.TABLE_NAME+" WHERE "+ BankMaster.Login.COLUMN_NAME_USERNAME+"=? AND "+ BankMaster.Login.COLUMN_NAME_PASSWORD+"=?";

        Cursor cursor = db.rawQuery(read,new String[]{uname,pwd});

        ArrayList<String> userNames = new ArrayList<>();

        while (cursor.moveToNext()){
            String userName = cursor.getString(cursor.getColumnIndexOrThrow(BankMaster.Login.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(BankMaster.Login.COLUMN_NAME_PASSWORD));
            String nic = cursor.getString(cursor.getColumnIndexOrThrow(BankMaster.Login.COLUMN_NAME_NIC));

            userNames.add(userName);
            userNames.add(password);
            userNames.add(nic);
        }
        cursor.close();
        return userNames;
    }

    public ArrayList<String> getUserName(String nic){
        SQLiteDatabase db = getReadableDatabase();

        String read = "SELECT "+ BankMaster.AccountHolder.COLUMN_NAME_HOLDERNAME+" FROM "+ BankMaster.AccountHolder.TABLE_NAME+" WHERE "+ BankMaster.AccountHolder.COLUMN_NAME_NIC+"=?";

        Cursor cursor = db.rawQuery(read, new String[]{nic});

        ArrayList<String> names = new ArrayList<>();

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(BankMaster.AccountHolder.COLUMN_NAME_HOLDERNAME));

            names.add(name);
        }
        cursor.close();
        return names;
    }

    public ArrayList<Integer> getAccount(String nic){
        SQLiteDatabase db = getReadableDatabase();

        String read = "SELECT "+ BankMaster.Account_Holder.COLUMN_NAME_ACCOUNTNUMBER+" FROM "+ BankMaster.Account_Holder.TABLE_NAME+" WHERE "+ BankMaster.Account_Holder.COLUMN_NAME_NIC+"=?";

        Cursor cursor = db.rawQuery(read,new String[]{nic});

        ArrayList<Integer> accNumber = new ArrayList<>();

        while (cursor.moveToNext()){
            int accNo = cursor.getInt(cursor.getColumnIndexOrThrow(BankMaster.Account_Holder.COLUMN_NAME_ACCOUNTNUMBER));

            accNumber.add(accNo);
        }
        cursor.close();
        return accNumber;
    }

    public ArrayList<Double> showBalance(String accNo){
        SQLiteDatabase db = getReadableDatabase();

        String read = "SELECT "+ BankMaster.Account.COLUMN_NAME_BALANCE+" FROM "+ BankMaster.Account.TABLE_NAME+" WHERE "+ BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+"=?";
        Cursor cursor = db.rawQuery(read,new String[]{accNo});

        ArrayList<Double> balances = new ArrayList<>();

        while(cursor.moveToNext()){
            double balance = cursor.getDouble(cursor.getColumnIndexOrThrow(BankMaster.Account.COLUMN_NAME_BALANCE));

            balances.add(balance);
        }
        cursor.close();
        return balances;
    }

    public void updateBalance(String accNo,double amount){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(BankMaster.Account.COLUMN_NAME_BALANCE,amount);

        String selection = BankMaster.Account.COLUMN_NAME_ACCOUNTNUMBER+" LIKE ?";
        String[] selectionArgs = {accNo};

        int count = db.update(
                BankMaster.Account.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

}
