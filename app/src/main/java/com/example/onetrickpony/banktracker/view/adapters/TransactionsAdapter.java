package com.example.onetrickpony.banktracker.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onetrickpony.banktracker.R;
import com.example.onetrickpony.banktracker.model.data.Transaction;

import java.util.List;

/**
 * Created by onetrickpony on 07.09.2017.
 */

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsHolder> {

    private List<Transaction> transactionsList;

    //Controller controller;

    public TransactionsAdapter(List<Transaction> transactionsList) {
        this.transactionsList = transactionsList;
        //this.controller =  controller;
    }

    @Override
    public TransactionsHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_view, parent, false);
        TransactionsHolder pvh = new TransactionsHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(TransactionsHolder holder, int position) {
        //Transaction transaction = controller.getSingleTransactionData(position);

        /*
        holder.date.setText(transaction.getDate());
        holder.time.setText(transaction.getTime());
        holder.sum.setText(String.valueOf(transaction.getSum()));
        holder.cardNumber.setText(transaction.getCardType() + transaction.getCardNumber());
        holder.companyName.setText(transaction.getCompanyName());
        holder.bankName.setText(transaction.getBankName());
        */
        holder.date.setText(transactionsList.get(position).getDate());
        holder.time.setText(transactionsList.get(position).getTime());
        holder.sum.setText(String.valueOf(transactionsList.get(position).getSum()));
        holder.cardNumber.setText(transactionsList.get(position).getCardType() + transactionsList.get(position).getCardNumber());
        holder.companyName.setText(transactionsList.get(position).getCompanyName());
        holder.bankName.setText(transactionsList.get(position).getBankName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //return controller.getTransactionsCount();

        return transactionsList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
