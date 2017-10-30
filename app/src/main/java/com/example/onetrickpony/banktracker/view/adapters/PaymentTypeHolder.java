package com.example.onetrickpony.banktracker.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.onetrickpony.banktracker.R;

/**
 * Created by onetrickpony on 06.10.2017.
 */

public class PaymentTypeHolder extends RecyclerView.ViewHolder {
    TextView companyNameType;
    TextView sum;
    TextView ptType;

    public PaymentTypeHolder(View v) {
        super(v);
        companyNameType = (TextView)itemView.findViewById(R.id.companyName_2TextView);
        sum = (TextView)itemView.findViewById(R.id.companySumTextView);
        ptType = (TextView)itemView.findViewById(R.id.ptTypeTextView);
    }
}
