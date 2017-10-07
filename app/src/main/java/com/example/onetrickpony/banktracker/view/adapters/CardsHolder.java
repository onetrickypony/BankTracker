package com.example.onetrickpony.banktracker.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.onetrickpony.banktracker.R;

/**
 * Created by onetrickpony on 06.10.2017.
 */

public class CardsHolder extends RecyclerView.ViewHolder {

    TextView cardName;
    TextView plusSum;
    TextView minusSum;
    TextView resultSum;

    public CardsHolder(View v) {
        super(v);
        cardName = (TextView)itemView.findViewById(R.id.cardNameTextView_2);
        plusSum = (TextView)itemView.findViewById(R.id.plusSumTextView);
        minusSum = (TextView)itemView.findViewById(R.id.minusSumTextView);
        resultSum = (TextView)itemView.findViewById(R.id.equallySumTextView);
    }

}
