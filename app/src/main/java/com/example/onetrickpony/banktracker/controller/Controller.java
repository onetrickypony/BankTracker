package com.example.onetrickpony.banktracker.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.onetrickpony.banktracker.model.ModelCreator;
import com.example.onetrickpony.banktracker.model.QueriesSender;
import com.example.onetrickpony.banktracker.model.data.Card;
import com.example.onetrickpony.banktracker.model.data.PaymentType;
import com.example.onetrickpony.banktracker.model.data.Transaction;
import com.example.onetrickpony.banktracker.model.data.sqlite.DBHelper;

import java.util.List;

/**
 * Created by onetrickpony on 30.09.2017.
 */

public class Controller {
    DBHelper dbHelper;
    SQLiteDatabase db;
    ModelCreator modelCreator;
    QueriesSender queriesSender;
    Context mContext;

    public Controller(Context context) {
        mContext = context;
        context.deleteDatabase(DBHelper.DATABASE_NAME);
        dbHelper = new DBHelper(mContext, 1);
        db = dbHelper.getWritableDatabase();
        modelCreator = new ModelCreator();
        queriesSender = new QueriesSender();

        modelCreator.FillBanksTable(db);
        modelCreator.FillCardsTable(db);
        modelCreator.FillCompaniesTable(db);
    }


    public void loadDataFromSmsDB()
    {
        modelCreator.FillTransactionsTable(db, mContext);
    }

    public Transaction getSingleTransactionData(int position)
    {
        return queriesSender.getSingleTransactionData(db, position);
    }

    public List<Transaction> getAllTransactionsData()
    {
        return queriesSender.getAllTransactionsData(db);
    }

    public int getTransactionsCount()
    {
        return queriesSender.getTransactionsCount(db);
    }

    public List<Card> getCardsStatistic() {
        return queriesSender.getCardsStatistic(db);
    }

    public List<PaymentType> getPaymentTypesStatistic() {
        return queriesSender.getPaymentTypesStatistic(db);
    }
}
