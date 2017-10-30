package com.example.onetrickpony.banktracker.model.data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by onetrickpony on 29.09.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "banktrackingDb.db";

    public static final String TABLE_BANKS = "banks";
    public static final String BANK_NAME = "bank_name";
    public static final String PHONE_NUMBER = "phone_number";

    public static final String KEY_ID = "_id";

    public static final String TABLE_CARDS = "cards";
    public static final String TYPE = "type";
    public static final String NUMBER = "number";
    public static final String BANK_ID = "bankID";

    public static final String TABLE_COMPANIES = "companies";
    public static final String COMPANY_NAME = "company_name";
    public static final String PAYMENT_TYPE = "payment_type";

    public static final String TABLE_TRANSACTIONS = "transactions";
    public static final String DATE_TIME = "date_time";
    public static final String SUM = "tr_sum"; //
    public static final String DIRECTION = "direction";
    public static final String CARD_ID = "card_id";
    public static final String COMPANY_ID = "company_id";

    public DBHelper(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_BANKS + "(" +
                KEY_ID + " integer primary key," +
                BANK_NAME + " text," +
                PHONE_NUMBER + " text" + ");");


        db.execSQL("create table " + TABLE_CARDS + "(" +
                KEY_ID + " integer primary key," +
                TYPE + " text," +
                NUMBER + " text," +
                BANK_ID + " integer," +
                "foreign key(" + BANK_ID + ") references banks(" + KEY_ID + "));");

        db.execSQL("create table " + TABLE_COMPANIES + "(" +
                KEY_ID + " integer primary key," +
                COMPANY_NAME + " text," +
                PAYMENT_TYPE + " text" + ");");

        db.execSQL("create table " + TABLE_TRANSACTIONS + "(" +
                KEY_ID + " integer primary key," +
                DATE_TIME + " text," +
                SUM + " real," +
                DIRECTION + " integer," +
                CARD_ID + " integer," +
                COMPANY_ID + " integer," +
                "foreign key(" + CARD_ID + ") references " + TABLE_CARDS + "(" + KEY_ID + ")," +
                "foreign key(" + COMPANY_ID + ") references " + TABLE_COMPANIES + "(" + KEY_ID + "));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        dropTables(db);

        onCreate(db);
    }

    private void dropTables(SQLiteDatabase db)
    {
        db.execSQL("drop table if exists " + TABLE_TRANSACTIONS);
        db.execSQL("drop table if exists " + TABLE_COMPANIES);
        db.execSQL("drop table if exists " + TABLE_CARDS);
        db.execSQL("drop table if exists " + TABLE_BANKS);
    }
}

