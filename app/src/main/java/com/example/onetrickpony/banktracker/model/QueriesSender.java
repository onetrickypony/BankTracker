package com.example.onetrickpony.banktracker.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.onetrickpony.banktracker.model.data.Card;
import com.example.onetrickpony.banktracker.model.data.PaymentType;
import com.example.onetrickpony.banktracker.model.data.Transaction;
import com.example.onetrickpony.banktracker.model.data.sqlite.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by onetrickpony on 01.10.2017.
 */

public class QueriesSender {

    public ArrayList<Transaction> getAllTransactionsData(SQLiteDatabase db)
    {
        ArrayList<Transaction> trList = new ArrayList<>();

        String table = DBHelper.TABLE_TRANSACTIONS + " as TR" +
                " left join " + DBHelper.TABLE_COMPANIES + " as TCOMPANIES" +
                " on  TR." + DBHelper.COMPANY_ID + " = " + "TCOMPANIES." + DBHelper.KEY_ID +
                " left join " + DBHelper.TABLE_CARDS + " as TCARDS" +
                " on TR." + DBHelper.CARD_ID + " = " + "TCARDS." + DBHelper.KEY_ID +
                " left join " + DBHelper.TABLE_BANKS + " as TBANKS" +
                " on  TCARDS." + DBHelper.BANK_ID  + " = " + "TBANKS." + DBHelper.KEY_ID;;

        Cursor cursorMyDB = db.query(table, null , null, null, null, null, null);

        if (cursorMyDB != null) {
            if (cursorMyDB.moveToFirst()) {
                for (int i = 0; i < cursorMyDB.getCount(); i++) {
                    Transaction tr = new Transaction();

                    tr.setDateTime(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.DATE_TIME)));
                    tr.setSum(cursorMyDB.getFloat(cursorMyDB.getColumnIndexOrThrow(DBHelper.SUM)));
                    tr.setDirection(cursorMyDB.getInt(cursorMyDB.getColumnIndexOrThrow(DBHelper.DIRECTION)));
                    tr.setCardNumber(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.NUMBER)));
                    tr.setCardType(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.TYPE)));
                    tr.setCompanyName(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.COMPANY_NAME)));
                    tr.setBankName(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.BANK_NAME)));
                    //tr.setBankName("TEST_BANK_NAME");
                    tr.setPaymentType(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.PAYMENT_TYPE)));

                    trList.add(tr);

                    cursorMyDB.moveToNext();
                }

            }
            cursorMyDB.close();
        }

        return trList;
    }


    public Transaction getSingleTransactionData(SQLiteDatabase db, int position)
    {
        Transaction tr = null;

        String table = DBHelper.TABLE_TRANSACTIONS + " as TR left join " + DBHelper.TABLE_CARDS + " as TCARDS" +
                " on TR." + DBHelper.CARD_ID + " = " + "TCARDS." + DBHelper.KEY_ID +
                " left join " + DBHelper.TABLE_COMPANIES + " as TCOMPANIES" +
                " on  TR." + DBHelper.COMPANY_ID + " = " + "TCOMPANIES." + DBHelper.KEY_ID;

        String selection =  DBHelper.KEY_ID + " = " + String.valueOf(position + 1);

        Cursor cursorMyDB = db.query(table, null , selection, null, null, null, null);

        if (cursorMyDB != null) {
            if (cursorMyDB.moveToFirst()) {

                tr = new Transaction();

                tr.setDateTime(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.DATE_TIME)));
                tr.setSum(cursorMyDB.getFloat(cursorMyDB.getColumnIndexOrThrow(DBHelper.SUM)));
                tr.setDirection(cursorMyDB.getInt(cursorMyDB.getColumnIndexOrThrow(DBHelper.DIRECTION)));
                tr.setCardNumber(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.NUMBER)));
                tr.setCardType(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.TYPE)));
                tr.setCompanyName(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.COMPANY_NAME)));
                tr.setBankName("TEST_BANK_NAME");
                tr.setPaymentType(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.PAYMENT_TYPE)));
            }
            cursorMyDB.close();
        }
        return tr;
    }

    public int getTransactionsCount(SQLiteDatabase db)
    {
        String[] columns = new String[] {"count(" + DBHelper.KEY_ID + ") as count_transactions"};
        Cursor cursor = db.query(DBHelper.TABLE_TRANSACTIONS, columns, null, null, null, null, null);

        int count = 0;

        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                count = cursor.getInt(cursor.getColumnIndexOrThrow("count_transactions"));
            }
            cursor.close();
        }

        return count;
    }
/////////////////////////////////////////////////////////////////////////////////////////////
    ///sss
    public List<Card> getCardsStatistic(SQLiteDatabase db) {
        ArrayList<Card> cList = new ArrayList<>();

        String table = DBHelper.TABLE_TRANSACTIONS + " as TR" +
                " left join " + DBHelper.TABLE_COMPANIES + " as TCOMPANIES" +
                " on  TR." + DBHelper.COMPANY_ID + " = " + "TCOMPANIES." + DBHelper.KEY_ID +
                " left join " + DBHelper.TABLE_CARDS + " as TCARDS" +
                " on TR." + DBHelper.CARD_ID + " = " + "TCARDS." + DBHelper.KEY_ID +
                " left join " + DBHelper.TABLE_BANKS + " as TBANKS" +
                " on  TCARDS." + DBHelper.BANK_ID  + " = " + "TBANKS." + DBHelper.KEY_ID;;

        Cursor cursorMyDB = db.query(table, null , null, null, null, null, null);

        if (cursorMyDB != null) {
            if (cursorMyDB.moveToFirst()) {
                for (int i = 0; i < cursorMyDB.getCount(); i++) {
                    Card tr = new Card();

                    /*
                    tr.setDateTime(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.DATE_TIME)));
                    tr.setSum(cursorMyDB.getFloat(cursorMyDB.getColumnIndexOrThrow(DBHelper.SUM)));
                    tr.setDirection(cursorMyDB.getInt(cursorMyDB.getColumnIndexOrThrow(DBHelper.DIRECTION)));
                    tr.setCardNumber(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.NUMBER)));
                    tr.setCardType(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.TYPE)));
                    tr.setCompanyName(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.COMPANY_NAME)));
                    tr.setBankName(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.BANK_NAME)));
                    //tr.setBankName("TEST_BANK_NAME");
                    tr.setPaymentType(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.PAYMENT_TYPE)));
                    */
                    cList.add(tr);

                    cursorMyDB.moveToNext();
                }

            }
            cursorMyDB.close();
        }

        return cList;
    }

    ////ssss
    public List<PaymentType> getPaymentTypesStatistic(SQLiteDatabase db) {
        ArrayList<PaymentType> ptList = new ArrayList<>();

        String table = DBHelper.TABLE_TRANSACTIONS + " as TR" +
                " left join " + DBHelper.TABLE_COMPANIES + " as TCOMPANIES" +
                " on  TR." + DBHelper.COMPANY_ID + " = " + "TCOMPANIES." + DBHelper.KEY_ID +
                " left join " + DBHelper.TABLE_CARDS + " as TCARDS" +
                " on TR." + DBHelper.CARD_ID + " = " + "TCARDS." + DBHelper.KEY_ID +
                " left join " + DBHelper.TABLE_BANKS + " as TBANKS" +
                " on  TCARDS." + DBHelper.BANK_ID  + " = " + "TBANKS." + DBHelper.KEY_ID;;

        Cursor cursorMyDB = db.query(table, null , null, null, null, null, null);

        if (cursorMyDB != null) {
            if (cursorMyDB.moveToFirst()) {
                for (int i = 0; i < cursorMyDB.getCount(); i++) {
                    PaymentType tr = new PaymentType();

                    /*
                    tr.setDateTime(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.DATE_TIME)));
                    tr.setSum(cursorMyDB.getFloat(cursorMyDB.getColumnIndexOrThrow(DBHelper.SUM)));
                    tr.setDirection(cursorMyDB.getInt(cursorMyDB.getColumnIndexOrThrow(DBHelper.DIRECTION)));
                    tr.setCardNumber(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.NUMBER)));
                    tr.setCardType(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.TYPE)));
                    tr.setCompanyName(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.COMPANY_NAME)));
                    tr.setBankName(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.BANK_NAME)));
                    //tr.setBankName("TEST_BANK_NAME");
                    tr.setPaymentType(cursorMyDB.getString(cursorMyDB.getColumnIndexOrThrow(DBHelper.PAYMENT_TYPE)));
                    */
                    ptList.add(tr);

                    cursorMyDB.moveToNext();
                }

            }
            cursorMyDB.close();
        }

        return ptList;
    }
}
