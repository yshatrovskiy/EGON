package com.eg.yevgeniy.egontrial.Activities.ManageEnergy;


import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.eg.yevgeniy.egontrial.Activities.Home.HomeActivity;
import com.eg.yevgeniy.egontrial.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyFragment extends android.support.v4.app.Fragment {





    int counter = 0;

    public String getCounter() {
        return counter + " kWh ";
    }
    public String getAvailable() {
        return available + " kWh ";
    }

    int available = 0;
    String myUnit = "22B";

    AptAdapter adapter;

    TextView tViewTotal;
    TextView tViewCounter;
    TextView tViewAvailable;
    TextView myCredit;
    TextView popUpTxt;
    TextView tViewMyEnergy;
    TextView tViewPending;

    Button bPlus;
    Button bMinus;
    Button bBuy;
    Button bSell;

    Button agree;
    Button disagree;

    Button bBuyTotal;

    PopupWindow pw;

    HomeActivity activity;



    public BuyFragment() {
        // Required empty public constructor
    }


    public static BuyFragment newInstance() {
        BuyFragment fragment = new BuyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (HomeActivity)getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        View v = inflater.inflate(R.layout.fragment_buy, container, false);

        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.apt_recycler_stations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = activity.getAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        tViewTotal = (TextView)v.findViewById(R.id.textViewTotal);
        tViewCounter = (TextView)v.findViewById(R.id.textViewCounter);
        tViewAvailable = (TextView)v.findViewById(R.id.textViewEnergy);
        tViewMyEnergy = (TextView)v.findViewById(R.id.textViewMyEnergy);
        tViewPending = (TextView)v.findViewById(R.id.pendingb);
        if(activity.getPending() > 0){
            setPending();
        }else{
            tViewPending.setText("");
        }


        myCredit = (TextView)v.findViewById(R.id.textViewCredit);

        setPending(0);
        setAvailable();
        setPrice();
        setCredit(activity.getCredit());
        setEnergy(activity.getEnergy());

        bPlus = (Button)v.findViewById(R.id.buttonPlus);
        bPlus.setOnClickListener(test);

        bMinus = (Button)v.findViewById(R.id.buttonMinus);
        bMinus.setOnClickListener(test);


        bBuyTotal = (Button)v.findViewById(R.id.buttonBuyTotal);
        bBuyTotal.setText("Buy / Sell");

        bBuyTotal.setBackgroundResource(R.drawable.rectangle_green);
        bBuyTotal.setText("Buy");
        bBuyTotal.setOnClickListener(buy);


        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_window, null);
        agree = (Button)popupView.findViewById(R.id.agree);
        disagree = (Button)popupView.findViewById(R.id.disagree);
        popUpTxt = (TextView)popupView.findViewById(R.id.popUpText);
        pw = new PopupWindow(popupView, 300, 300, true);


        return v;
    }

    View.OnClickListener buy = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pw.showAtLocation(getView(), Gravity.CENTER, 0, 0);
            popUpTxt.setText("Are You Sure You Want to Purchase " + getCounter() + "for " + getPrice());
            disagree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pw.dismiss();
                }
            });
            agree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //add contstants
                    setCredit(activity.getCredit() - getPriceInt());
                    setEnergy(activity.getEnergy() + counter);
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


    public void plusOne(){
        if(available > counter){
            counter++;
            tViewCounter.setText(counter + " kWh");
        }
    }

    public int getEnergy() {
        return activity.getEnergy();
    }

    public void setEnergy(int energy) {
        activity.setEnergy(energy);
        tViewMyEnergy.setText(energy + " kWh");

    }

    public void setPending(double pending){
        activity.setPending(pending);
        String pr = String.format("%.2f", activity.getPending());
        if (activity.getPending() > 0){
            tViewPending.setText(pr + " €");
        }
    }

    public void setPending(){
        String pr = String.format("%.2f", activity.getPending());
        tViewPending.setText(pr + " €");
    }

    public void setCounter(int counter) {
        this.counter = counter;
        tViewCounter.setText(counter + " kWh");
    }

    public void setAvailable() {
        available = adapter.getTotalValue();
        tViewAvailable.setText(available + " kWh Available");
    }

    public void setCredit(double credit1){
        activity.setCredit(credit1);
        String pr = String.format("%.2f", activity.getCredit());
        myCredit.setText(pr + " €");
    }

    public void minusOne(){
        if(0 < counter){
            counter--;
            tViewCounter.setText(counter + " kWh");
        }
    }

    public void setPrice(){
        double price = counter * .18;
        String pr = String.format("%.2f", price);
        tViewTotal.setText(pr + " €");
    }

    public double getPriceInt(){
        return counter * .18;
    }

    public String getPrice(){
        double price = counter * .18;
        String pr = String.format("%.2f", price);

        return pr + " €";
    }


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
