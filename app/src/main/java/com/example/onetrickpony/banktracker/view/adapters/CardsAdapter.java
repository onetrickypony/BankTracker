package com.example.onetrickpony.banktracker.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onetrickpony.banktracker.R;
import com.example.onetrickpony.banktracker.model.data.Card;

import java.util.List;

/**
 * Created by onetrickpony on 06.10.2017.
 */

public class CardsAdapter extends RecyclerView.Adapter<CardsHolder> {
    private List<Card> cardList;

    //Controller controller;

    public CardsAdapter(List<Card> cardList) {
        this.cardList = cardList;
        //this.controller =  controller;
    }

    @Override
    public CardsHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_view, parent, false);
        CardsHolder pvh = new CardsHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CardsHolder holder, int position) {
        //Transaction transaction = controller.getSingleTransactionData(position);

        holder.cardName.setText(cardList.get(position).getType() + cardList.get(position).getNumber());
        holder.minusSum.setText(String.valueOf(cardList.get(position).getMinusSum()));
        holder.plusSum.setText(String.valueOf(cardList.get(position).getPlusSum()));
        holder.resultSum.setText(String.valueOf(cardList.get(position).getResSum()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //return controller.getTransactionsCount();

        return cardList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
