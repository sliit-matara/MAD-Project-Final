package com.example.mad_miniproject.DB_Files;

import android.provider.BaseColumns;

public final class BankMaster {
    private BankMaster(){}

    protected static class Account implements BaseColumns {
        public static final String TABLE_NAME = "account";
        public static final String COLUMN_NAME_ACCOUNTNUMBER = "accountNo";
        public static final String COLUMN_NAME_ACCOUNTTYPE = "accType";
        public static final String COLUMN_NAME_OPENEDDATE = "openedDate";
        public static final String COLUMN_NAME_RELATIONSHIP = "relationship";
        public static final String COLUMN_NAME_BALANCE = "balance";
        public static final String COLUMN_NAME_PRODUCTNAME = "productName";
        public static final String COLUMN_NAME_BRANCH = "branch";
    }

    protected static class AccountHolder implements BaseColumns{
        public static final String TABLE_NAME = "accountHolder";
        public static final String COLUMN_NAME_NIC = "nic";
        public static final String COLUMN_NAME_HOLDERNAME = "holderName";
        public static final String COLUMN_NAME_MOBILENO = "mobileNo";
        public static final String COLUMN_NAME_DOB = "dob";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_EMAIL = "email";
    }

    protected  static class Login implements BaseColumns{
        public static final String TABLE_NAME = "login";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_NIC = "nic";
    }

    protected static class MoneyTransfer implements BaseColumns{
        public static final String TABLE_NAME = "moneyTransfer";
        public static final String COLUMN_NAME_TRANSFERID = "transferID";
        public static final String COLUMN_NAME_ACCOUNTNUMBER = "accountNo";
        public static final String COLUMN_NAME_TOACCOUNT = "toAccount";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_DATETIME = "dateTime";
    }

    protected static class BillPayment implements BaseColumns{
        public static final String TABLE_NAME = "billPayment";
        public static final String COLUMN_NAME_BILLID = "paymentID";
        public static final String COLUMN_NAME_ACCOUNTNUMBER = "accountNo";
        public static final String COLUMN_NAME_BILLER = "biller";
        public static final String COLUMN_NAME_BILLER_ACCOUNTNUMBER = "billerAccNumber";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_DATETIME = "dateTime";
    }

    protected static class Loan implements BaseColumns{
        public static final String TABLE_NAME = "loan";
        public static final String COLUMN_NAME_LOANID = "loanID";
        public static final String COLUMN_NAME_NIC = "nic";
        public static final String COLUMN_NAME_TYPE = "loanType";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_APPROVED_DATE = "appDate";
        public static final String COLUMN_NAME_DURATION = "duration";
        public static final String COLUMN_NAME_INTEREST_RATE = "intRate";
        public static final String COLUMN_NAME_PERMONTHRATE = "monthRate";
    }

    protected static class Account_Holder implements BaseColumns{
        public static final String TABLE_NAME = "account_holder";
        public static final String COLUMN_NAME_NIC = "nic";
        public static final String COLUMN_NAME_ACCOUNTNUMBER = "accountNo";
    }

    protected static class Transaction implements BaseColumns{
        public static final String TABLE_NAME = "transactionList";
        public static final String COLUMN_NAME_TRANSACTIONID = "transactionID";
        public static final String COLUMN_NAME_ACCOUNTNUMBER = "accountNo";
        public static final String COLUMN_NAME_TRANSACTIONDATE = "transactionDate";
        public static final String COLUMN_NAME_DEBIT = "debit";
        public static final String COLUMN_NAME_CREDIT = "credit";
        public static final String COLUMN_NAME_BALANCE = "balance";
    }
}
