package com.example.onetrickpony.banktracker.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.onetrickpony.banktracker.R;

/**
 * Created by onetrickpony on 09.09.2017.
 */

public class TransactionsHolder extends RecyclerView.ViewHolder {
    TextView date;
    TextView time;
    TextView sum;
    TextView cardNumber;
    TextView companyName;
    TextView bankName;

    public TransactionsHolder(View v) {
        super(v);
        date = (TextView)itemView.findViewById(R.id.dateTextView);
        time = (TextView)itemView.findViewById(R.id.timeTextView);
        sum = (TextView)itemView.findViewById(R.id.sumTextView);
        cardNumber = (TextView)itemView.findViewById(R.id.cardNumberTextView);
        companyName = (TextView)itemView.findViewById(R.id.companyNameTextView);
        bankName = (TextView)itemView.findViewById(R.id.bankNameTextView);
    }
}
