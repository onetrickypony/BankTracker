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
import com.example.onetrickpony.banktracker.model.data.PaymentType;
import com.example.onetrickpony.banktracker.view.adapters.PaymentTypeAdapter;
import com.example.onetrickpony.banktracker.view.adapters.TransactionsAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private RecyclerView rv;
    private LinearLayoutManager llm;

    private PaymentTypeAdapter mPaymentTypeAdapter;
    private List<PaymentType> paymentTypeList;

    private OnFragmentInteractionListener mListener;

    public FragmentView() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentView newInstance(List<PaymentType> ptList) {
        FragmentView fragment = new FragmentView();
        /*
        Bundle args = new Bundle();
        args.put putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        */
        fragment.init(ptList);
        return fragment;
    }

    public void init(List<PaymentType> ptList){
        this.paymentTypeList = ptList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view, container, false);

        rv = (RecyclerView) v.findViewById(R.id.rv_wastes);
        rv.setHasFixedSize(true);
        llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);

        mPaymentTypeAdapter = new PaymentTypeAdapter(paymentTypeList);
        rv.setAdapter(mPaymentTypeAdapter);

        return v;
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
