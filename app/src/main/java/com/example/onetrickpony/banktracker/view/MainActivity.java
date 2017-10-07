package com.example.onetrickpony.banktracker.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.FragmentTransaction;

import com.example.onetrickpony.banktracker.R;
import com.example.onetrickpony.banktracker.model.data.Card;
import com.example.onetrickpony.banktracker.model.data.PaymentType;
import com.example.onetrickpony.banktracker.model.data.Transaction;
import com.example.onetrickpony.banktracker.view.fragments.FragmentBankAccounts;
import com.example.onetrickpony.banktracker.view.fragments.FragmentTransactions;
import com.example.onetrickpony.banktracker.view.fragments.FragmentView;
import com.example.onetrickpony.banktracker.controller.Controller;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentView fview;
    private FragmentBankAccounts fbankaccounts;
    private FragmentTransactions ftransactions;

    private List<Transaction> transactionsList;
    private List<Card> cardsList;
    private List<PaymentType> ptList;

    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fview = new FragmentView();
        fbankaccounts = new FragmentBankAccounts();
        //------------------------------------------------------------------------------------------

        controller = new Controller(this);
        controller.loadDataFromSmsDB();
        //


        transactionsList = controller.getAllTransactionsData();
        cardsList = controller.getCardsStatistic();
        ptList = controller.getPaymentTypesStatistic();
        ftransactions = ftransactions.newInstance(transactionsList);
        fbankaccounts = fbankaccounts.newInstance(cardsList);
        fview = fview.newInstance(ptList);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Действия на нажатие пунктов меню
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction ftrans = getFragmentManager().beginTransaction();

        if (id == R.id.nav_view) {
            ftrans.replace(R.id.container, fview);
        } else if (id == R.id.nav_bank_accounts) {
            ftrans.replace(R.id.container, fbankaccounts);
        } else if (id == R.id.nav_transactions) {
            ftrans.replace(R.id.container, ftransactions);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public List<Transaction> getTransactionList()
    {
        return transactionsList;
    }

    /*
    public void UpdateModel()
    {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursorSMS = getContentResolver().query(uri, null, null ,null,null);

        Cursor cursorMyDB = db.query(DBHelper.DATABASE_NAME, null, null, null, null, null, null);
        ContentValues contentValuesTr = new ContentValues();

        if(cursorSMS.moveToFirst()) {
            for(int i=0; i < cursorSMS.getCount(); i++) {
                // Do something here for example read the body
                String body = cursorSMS.getString(cursorSMS.getColumnIndexOrThrow("body")).toString();
                String address = cursorSMS.getString(cursorSMS.getColumnIndexOrThrow("address")).toString();
                int date = cursorSMS.getInt(cursorSMS.getColumnIndexOrThrow("date"));

                if (address.equals("900"))
                {
                    Pattern pattern = Pattern.
                            compile("(ECMC4982|MAES3040) (\\d+).(\\d+).(\\d+) (\\d+):(\\d+) (\\S+) (\\d+|\\d.\\d)р(.+)");

                    if (pattern.matcher(body).matches())
                    {
                        Matcher matcher = pattern.matcher(body);
                        matcher.find();
                        Transaction transaction = new Transaction();

                        Date _date = new Date();
                        _date.setYear(100 + Integer.parseInt(matcher.group(4)));
                        _date.setMonth(Integer.parseInt(matcher.group(3)) - 1);
                        _date.setDate(Integer.parseInt(matcher.group(2)));

                        _date.setHours(Integer.parseInt(matcher.group(5)));
                        _date.setMinutes(Integer.parseInt(matcher.group(6)));

                        transaction.setDateTime(_date);
                        transaction.setSum(Float.parseFloat(matcher.group(8)));

                        String direction = matcher.group(7);
                        if (direction.equals("зачисление"))
                        {
                            transaction.setDirection(true);
                        } else if (direction.equals("списание") || direction.equals("покупка"))
                        {
                            transaction.setDirection(false);
                        }
                        else
                        {
                            //вызвать исключение
                        }

                        transaction.setCompanyId(1);
                        transaction.setCardId(1);

                        contentValuesTr.put(dbHelper.DATE_TIME, transaction.getDate() + " " + transaction.getTime());
                        contentValuesTr.put(dbHelper.SUM, transaction.getSum());
                        contentValuesTr.put(dbHelper.DIRECTION, transaction.isDirection());
                        contentValuesTr.put(dbHelper.COMPANY_ID, transaction.getCompanyId());
                        contentValuesTr.put(dbHelper.CARD_ID, transaction.getCompanyId());

                        db.insert(DBHelper.TABLE_TRANSACTIONS, null, contentValuesTr);

                    }
                    else
                    {
                        // сообщение с другим шаблоном
                    }

                }

                cursorSMS.moveToNext();
            }
        }
        cursorSMS.close();

    }



    public void UpdateModel_old()
    {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor c= getContentResolver().query(uri, null, null ,null,null);

        if(c.moveToFirst()) {
            for(int i=0; i < c.getCount(); i++) {
                // Do something here for example read the body
                String body = c.getString(c.getColumnIndexOrThrow("body")).toString();
                String address = c.getString(c.getColumnIndexOrThrow("address")).toString();
                int date = c.getInt(c.getColumnIndexOrThrow("date"));

                if (address.equals("900"))
                {
                    Pattern pattern = Pattern.
                           compile("(ECMC4982|MAES3040) (\\d+).(\\d+).(\\d+) (\\d+):(\\d+) (\\S+) (\\d+|\\d.\\d)р(.+)");

                    if (pattern.matcher(body).matches())
                    {
                        Matcher matcher = pattern.matcher(body);
                        matcher.find();
                        Transaction transaction = new Transaction();

                        Date _date = new Date();
                        _date.setYear(100 + Integer.parseInt(matcher.group(4)));
                        _date.setMonth(Integer.parseInt(matcher.group(3)) - 1);
                        _date.setDate(Integer.parseInt(matcher.group(2)));

                        _date.setHours(Integer.parseInt(matcher.group(5)));
                        _date.setMinutes(Integer.parseInt(matcher.group(6)));

                        transaction.setDateTime(_date);
                        transaction.setSum(Float.parseFloat(matcher.group(8)));

                        String direction = matcher.group(7);
                        if (direction.equals("зачисление"))
                        {
                            transaction.setDirection(true);
                        } else if (direction.equals("списание") || direction.equals("покупка"))
                        {
                            transaction.setDirection(false);
                        }
                        else
                        {
                            //вызвать исключение
                        }

                        transaction.setCompanyId(1);
                        transaction.setCardId(1);

                        SQLite.get().insert(TransactionsTable.TABLE, transaction);
                    }
                    else
                    {
                        // сообщение с другим шаблоном
                    }

                }

                c.moveToNext();
            }
        }
        c.close();

    }



    public void LoadTestData()
    {
        for (int i = 1; i < 10; i++)
        {
            Transaction transaction = new Transaction();

            //transaction.setDateTime("12.01.2996");
            transaction.setSum(200 + 3 * i);
            transaction.setDirection(false);
            transaction.setCompanyId(1+i);
            transaction.setCardId(1+i);

            SQLite.get().insert(TransactionsTable.TABLE, transaction);
        }
    }


    public void LoadTestData2(List<Transaction> listoftr)
    {
        for (int i = 1; i < 10; i++)
        {
            Transaction transaction = new Transaction();

            //transaction.setDateTime("12.01.1996");
            transaction.setSum(100 + i);
            transaction.setDirection(false);
            transaction.setCompanyId(1+i);
            transaction.setCardId(1+i);

            listoftr.add(transaction);
        }

    }
    */
}
