package com.eg.yevgeniy.egontrial.Activities.Dashboard;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.eg.yevgeniy.egontrial.Activities.Dashboard.DayAxisValueFormatter;

import com.eg.yevgeniy.egontrial.Activities.Home.HomeActivity;
import com.eg.yevgeniy.egontrial.Activities.Login.LoginActivity;
import com.eg.yevgeniy.egontrial.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



import java.util.ArrayList;
import java.util.List;

import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller;


public class CustomerFragment extends Fragment{



    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset;
    BarData BARDATA ;

    private DatabaseReference postRef = FirebaseDatabase.getInstance().getReference();
    private CustomerAdapter adapter;

    public CustomerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CustomerFragment newInstance() {
        CustomerFragment fragment = new CustomerFragment();
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

        ((HomeActivity) getActivity()).setActionBarTitle("Visualization");

        View v = inflater.inflate(R.layout.fragment_customer, container, false);
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recycler_stations);

        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CustomerAdapter();
        recyclerView.addItemDecoration(new VertSpace(20));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


        chart = (BarChart) v.findViewById(R.id.chart1);

        BARENTRY = new ArrayList<>();
        BarEntryLabels = new ArrayList<String>();
        AddValuesToBARENTRY();
        Bardataset = new BarDataSet(BARENTRY, "Usage");


        Bardataset.setColor(Color.parseColor("#03324a"));
        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(Bardataset);



        BARDATA = new BarData(Bardataset);
        chart.getDescription().setEnabled(false);
        chart.setData(BARDATA);
        chart.animateY(3000);

        Legend l = chart.getLegend();
        //l.setEnabled(false);


        YAxis rightAxis = chart.getAxisRight();
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setEnabled(false);
        rightAxis.setEnabled(false);
        rightAxis.setDrawGridLines(false);

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);

        return v;
    }


    public void AddValuesToBARENTRY(){

        BARENTRY.add(new BarEntry(0f, 1));
        BARENTRY.add(new BarEntry(1f, 2));
        BARENTRY.add(new BarEntry(2f, 3));
        BARENTRY.add(new BarEntry(3f, 4));
        BARENTRY.add(new BarEntry(4f, 5));
        BARENTRY.add(new BarEntry(5f, 6));

    }
}

class VertSpace extends RecyclerView.ItemDecoration{
    private final int spacer;
    //private final int spacer2;

    public VertSpace(int spacer){
        this.spacer = spacer;
        //this.spacer2 = spacer2;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = spacer;

    }
}