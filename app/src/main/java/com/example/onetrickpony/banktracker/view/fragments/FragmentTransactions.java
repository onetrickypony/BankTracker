package com.example.onetrickpony.banktracker.view.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onetrickpony.banktracker.R;
import com.example.onetrickpony.banktracker.view.adapters.TransactionsAdapter;
import com.example.onetrickpony.banktracker.model.data.Transaction;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentTransactions.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentTransactions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTransactions extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private OnFragmentInteractionListener mListener;

    private RecyclerView rv;
    private LinearLayoutManager llm;

    private TransactionsAdapter mAdapterTransactions;
    private List<Transaction> transactionsList;

    public FragmentTransactions() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentTransactions newInstance(List<Transaction> trList) {//String param1, String param2) {
        FragmentTransactions fragment = new FragmentTransactions();
        /*
        Bundle args = new Bundle();
        args.put putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        */
        fragment.init(trList);
        return fragment;
    }

    public void init(List<Transaction> trList){
        this.transactionsList = trList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_fragment_transactions, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);

        //transactionsList = controller.getAllTransactionsData();
        mAdapterTransactions = new TransactionsAdapter(transactionsList);
        rv.setAdapter(mAdapterTransactions);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
*/
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
