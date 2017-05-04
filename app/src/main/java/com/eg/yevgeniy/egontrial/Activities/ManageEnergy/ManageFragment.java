package com.eg.yevgeniy.egontrial.Activities.ManageEnergy;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.eg.yevgeniy.egontrial.Activities.Dashboard.CustomerAdapter;
import com.eg.yevgeniy.egontrial.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageFragment extends android.support.v4.app.Fragment {

    private AptAdapter adapter;



    int counter = 0;
    int available = 0;

    TextView tViewTotal;
    TextView tViewCounter;
    TextView tViewAvailable;

    Button bPlus;
    Button bMinus;
    Button bBuy;
    Button bSell;

    Button agree;
    Button disagree;

    Button bBuyTotal;

    PopupWindow pw;



    public ManageFragment() {
        // Required empty public constructor
    }


    public static ManageFragment newInstance() {
        ManageFragment fragment = new ManageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View v = inflater.inflate(R.layout.fragment_manage, container, false);

        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.apt_recycler_stations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AptAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        tViewTotal = (TextView)v.findViewById(R.id.textViewTotal);
        tViewCounter = (TextView)v.findViewById(R.id.textViewCounter);
        tViewAvailable = (TextView)v.findViewById(R.id.textViewEnergy);

        setAvailable();
        setPrice();

        bPlus = (Button)v.findViewById(R.id.buttonPlus);
        bPlus.setOnClickListener(test);

        bMinus = (Button)v.findViewById(R.id.buttonMinus);
        bMinus.setOnClickListener(test);

        bBuy = (Button)v.findViewById(R.id.buttonBuy);
        bBuy.setOnClickListener(choice);

        bSell = (Button)v.findViewById(R.id.buttonSell);
        bSell.setOnClickListener(choice);


        bBuyTotal = (Button)v.findViewById(R.id.buttonBuyTotal);
        bBuyTotal.setText("Buy / Sell");


        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_window, null);
        agree = (Button)popupView.findViewById(R.id.agree);
        disagree = (Button)popupView.findViewById(R.id.disagree);
        pw = new PopupWindow(popupView, 300, 300, true);


        return v;
    }

    View.OnClickListener buy = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pw.showAtLocation(getView(), Gravity.CENTER, 0, 0);
            disagree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pw.dismiss();
                }
            });
            agree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.makePurchase(counter);
                    adapter.notifyDataSetChanged();
                    available = adapter.getTotalValue();
                    setCounter(0);
                    setAvailable();
                    setPrice();
                    pw.dismiss();
                }
            });
        }
    };

    View.OnClickListener sell = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pw.showAtLocation(getView(), Gravity.CENTER, 0, 0);
            disagree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pw.dismiss();
                }
            });
            agree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.addUnit(counter, counter);
                    adapter.notifyDataSetChanged();
                    available =adapter.getTotalValue();
                    setCounter(0);
                    setAvailable();
                    setPrice();
                    pw.dismiss();
                }
            });
        }
    };


    public void plusOne(){
        if(available > counter){
            counter++;
            tViewCounter.setText(counter + " kWh");
        }
    }


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
        tViewCounter.setText(counter + " kWh");
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable() {
        available = adapter.getTotalValue();
        tViewAvailable.setText(available + " kWh");
    }

    public void minusOne(){
        if(0 < counter){
            counter--;
            tViewCounter.setText(counter + " kWh");
        }
    }

    public void setPrice(){
        tViewTotal.setText((counter * .18) + " Euros");
    }

    View.OnClickListener choice = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.buttonBuy:
                    bBuyTotal.setBackgroundResource(R.drawable.rectangle_green);
                    bBuyTotal.setText("Buy");
                    bBuyTotal.setOnClickListener(buy);
                    break;
                case R.id.buttonSell:
                    bBuyTotal.setBackgroundResource(R.drawable.rectangle_orange);
                    bBuyTotal.setText("Sell");
                    bBuyTotal.setOnClickListener(sell);
                    break;
            }
        }
    };

    View.OnClickListener test = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.buttonMinus:
                    minusOne();
                    setPrice();
                    break;
                case R.id.buttonPlus:
                    plusOne();
                    setPrice();
                    break;
            }
        }
    };
}
