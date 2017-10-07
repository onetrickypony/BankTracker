package com.example.onetrickpony.banktracker.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.onetrickpony.banktracker.R;

/**
 * Created by onetrickpony on 06.10.2017.
 */

public class PaymentTypeHolder extends RecyclerView.ViewHolder {
    TextView paymentType;
    TextView sum;

    public PaymentTypeHolder(View v) {
        super(v);
        paymentType = (TextView)itemView.findViewById(R.id.paymentTypeTextView);
        sum = (TextView)itemView.findViewById(R.id.companySumTextView);
    }
}
