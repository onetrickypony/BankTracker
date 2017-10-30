package com.example.onetrickpony.banktracker.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onetrickpony.banktracker.R;
import com.example.onetrickpony.banktracker.model.data.Card;
import com.example.onetrickpony.banktracker.model.data.PaymentType;

import java.util.List;

/**
 * Created by onetrickpony on 07.10.2017.
 */

public class PaymentTypeAdapter extends RecyclerView.Adapter<PaymentTypeHolder>{
    private List<PaymentType> ptList;

    public PaymentTypeAdapter(List<PaymentType> ptList) {
        this.ptList = ptList;
    }

    @Override
    public PaymentTypeHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.companies_view, parent, false);
        PaymentTypeHolder pvh = new PaymentTypeHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PaymentTypeHolder holder, int position) {
        holder.sum.setText(String.valueOf(ptList.get(position).getSum()));
        holder.companyNameType.setText(ptList.get(position).getcName());
        holder.ptType.setText(ptList.get(position).getPaymentType());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //return controller.getTransactionsCount();

        return ptList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
