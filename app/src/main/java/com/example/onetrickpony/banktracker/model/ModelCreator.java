package com.example.onetrickpony.banktracker.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.onetrickpony.banktracker.model.data.sqlite.DBHelper;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by onetrickpony on 30.09.2017.
 */

public class ModelCreator {
    public void FillTransactionsTable(SQLiteDatabase db, Context context) {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursorSMS = context.getContentResolver().query(uri, null, null ,null,null);

        ArrayList<String> bankPhoneNumbers = new ArrayList<>(getBankPhoneNumbersList(db));

        if(cursorSMS.moveToFirst()) {
            for(int i = 0; i < cursorSMS.getCount(); i++) {

                String body = cursorSMS.getString(cursorSMS.getColumnIndexOrThrow("body"));
                String address = cursorSMS.getString(cursorSMS.getColumnIndexOrThrow("address"));
                int date = cursorSMS.getInt(cursorSMS.getColumnIndexOrThrow("date"));

                for (int j = 0; j < bankPhoneNumbers.size(); j++)
                {
                    if (address.equals(bankPhoneNumbers.get(j)))
                    {
                        Pattern pattern = Pattern.
                                compile("(ECMC|MAES|VISA)(\\d+) (\\d+.\\d+.\\d+ \\d+:\\d+) (\\S+) (\\d+|\\d+.\\d+)р (.*)Баланс(.+)");// не добавляются не целые суммы

                        if (pattern.matcher(body).matches())
                        {
                            Matcher matcher = pattern.matcher(body);
                            matcher.find();

                            String direction = matcher.group(4);
                            if (direction.equals("зачисление"))
                            {
                                addOneTransactionToTable(db, true, matcher.group(3), Float.parseFloat(matcher.group(5)),
                                        getCompanyIDFromCompaniesTable(db, "Зачисление"),
                                        getCardIDFromCardsTable(db, matcher.group(1), matcher.group(2)));

                            } else if (direction.equals("покупка"))
                            {
                                addOneTransactionToTable(db, false, matcher.group(3), Float.parseFloat(matcher.group(5)),
                                        getCompanyIDFromCompaniesTable(db, matcher.group(6)),
                                        getCardIDFromCardsTable(db, matcher.group(1), matcher.group(2)));

                            } else if (direction.equals("списание"))
                            {
                                addOneTransactionToTable(db, false, matcher.group(3), Float.parseFloat(matcher.group(5)),
                                        getCompanyIDFromCompaniesTable(db, "Списание"),
                                        getCardIDFromCardsTable(db, matcher.group(1), matcher.group(2)));
                            }
                            else
                            {
                                //вызвать исключение
                            }
                        }
                        else
                        {
                            // сообщение с другим шаблоном
                        }

                    }
                }
                cursorSMS.moveToNext();
            }
        }
        cursorSMS.close();
    }


    private ArrayList<String> getBankPhoneNumbersList(SQLiteDatabase db)
    {
        ArrayList<String> bankPhoneNumbers = new ArrayList<>();

        Cursor cursorBanks = db.query(DBHelper.TABLE_BANKS, null, null, null, null, null, null);

        if (cursorBanks != null)
        {
            if (cursorBanks.moveToFirst()) {
                for (int i = 0; i < cursorBanks.getCount(); i++) {
                    bankPhoneNumbers.add(cursorBanks.getString(cursorBanks.getColumnIndexOrThrow(DBHelper.PHONE_NUMBER)));
                    cursorBanks.moveToNext();
                }
            }
            cursorBanks.close();
        }

        return bankPhoneNumbers;
    }

    private int getCardIDFromCardsTable(SQLiteDatabase db, String cardType, String cardNumber)
    {
        Cursor cursorCards = db.query(DBHelper.TABLE_CARDS, null, null, null, null, null, null);

        int _id = 0;

        if (cursorCards != null)
        {
            if (cursorCards.moveToFirst())
            {
                for(int i = 0; i < cursorCards.getCount(); i++) {
                    if (cardType.equals(cursorCards.getString(cursorCards.getColumnIndexOrThrow(DBHelper.TYPE)))
                            && cardNumber.equals(cursorCards.getString(cursorCards.getColumnIndexOrThrow(DBHelper.NUMBER))))
                    {
                        _id = cursorCards.getInt(cursorCards.getColumnIndexOrThrow(DBHelper.KEY_ID));
                    }
                    cursorCards.moveToNext();
                }
            }
            cursorCards.close();
        }

        return _id;
    }

    private int getCompanyIDFromCompaniesTable(SQLiteDatabase db, String companyName)
    {
        Cursor cursorCompanies = db.query(DBHelper.TABLE_COMPANIES, null, null, null, null, null, null);

        int _id = 0;

        if (cursorCompanies != null)
        {
            if (cursorCompanies.moveToFirst())
            {
                for(int i = 0; i < cursorCompanies.getCount(); i++) {

                    String companyNameFromDB = cursorCompanies.getString(cursorCompanies.getColumnIndexOrThrow(DBHelper.COMPANY_NAME));
                    if (companyName.contains(companyNameFromDB))
                    {
                        _id = cursorCompanies.getInt(cursorCompanies.getColumnIndexOrThrow(DBHelper.KEY_ID));
                    }
                    cursorCompanies.moveToNext();
                }
            }
            cursorCompanies.close();
        }

        if (_id == 0) {
            String selection = DBHelper.COMPANY_NAME + " = 'OTHER COMPANIES'";
            Cursor cursor = db.query(DBHelper.TABLE_COMPANIES, null, selection, null, null, null, "1");


            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    _id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.KEY_ID));
                }
                cursor.close();
            }
        }

        return _id;
    }


    private void addOneTransactionToTable(SQLiteDatabase db, boolean direction, String datetime, float sum, int companyID, int cardID)
    {
        ContentValues contentValuesTr = new ContentValues();

        contentValuesTr.put(DBHelper.DIRECTION, direction);
        contentValuesTr.put(DBHelper.DATE_TIME, datetime);
        contentValuesTr.put(DBHelper.SUM, sum);
        contentValuesTr.put(DBHelper.COMPANY_ID, companyID);
        contentValuesTr.put(DBHelper.CARD_ID, cardID);

        db.insert(DBHelper.TABLE_TRANSACTIONS, null, contentValuesTr);
    }

    public void FillBanksTable(SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.BANK_NAME, "SBERBANK");
        contentValues.put(DBHelper.PHONE_NUMBER, 900);
        db.insert(DBHelper.TABLE_BANKS, null, contentValues);

    }

    public void FillCardsTable(SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBHelper.NUMBER, "4982");
        contentValues.put(DBHelper.TYPE, "ECMC");
        contentValues.put(DBHelper.BANK_ID, 1);
        db.insert(DBHelper.TABLE_CARDS, null, contentValues);

        contentValues.put(DBHelper.NUMBER, "3034");
        contentValues.put(DBHelper.TYPE, "MAES");
        contentValues.put(DBHelper.BANK_ID, 1);
        db.insert(DBHelper.TABLE_CARDS, null, contentValues);
    }

    // Проверка: входит ли название компании из таблицы в строку из сообщение, которое мы парсим
    public void FillCompaniesTable(SQLiteDatabase db){
        ContentValues contentValuesCompanies = new ContentValues();

        String food = "Фаст-фуд";
        String drugstore = "Аптека";
        String coffeeshop = "Кофейня";
        String supermarket = "Супермаркет";
        String cafe = "Кафе";
        String cinema = "Кинотеатр";
        String internet_shop = "Интернет-магазин";
        String clothes = "Одежда";
        String ouput = "-";
        String input = "+";
        String other = "Другое";

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "MCDONALDS");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, food);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "BURGER KING");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, food);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "KFC");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, food);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "GLOWSUBS");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, food);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "SUBWAY");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, food);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "APTEKA");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, drugstore);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "COFFEE HOUSE");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, coffeeshop);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "JEFFREY");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, coffeeshop);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "CHAYNIKOFF");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, coffeeshop);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "KOFESHOP 4.20");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, coffeeshop);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "SUPERMARKET SITI");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, supermarket);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "PEREKRESTOK");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, supermarket);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "ATAK");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, supermarket);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "DIKSI");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, supermarket);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "MAGNOLIYA");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, supermarket);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "BILLA");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, supermarket);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "RAZLIVNOE IZOBILIE");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, supermarket);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "PYATEROCHKA");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, supermarket);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "SEDMOY KONTINENT");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, supermarket);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "MOKHOVAYA");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, cafe);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "YAKITORIYA");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, cafe);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "PICCOLO");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, cafe);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "KINOCENTR.RU");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, cinema);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "KF-ATRIUM");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, cinema);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "ALIEXPRESS");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, internet_shop);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "OLDI");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, internet_shop);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "BERSHKA");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, clothes);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "Списание");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, ouput);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "Зачисление");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, input);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);

        contentValuesCompanies.put(DBHelper.COMPANY_NAME, "OTHER COMPANIES");
        contentValuesCompanies.put(DBHelper.PAYMENT_TYPE, other);
        db.insert(DBHelper.TABLE_COMPANIES, null, contentValuesCompanies);
    }

}
