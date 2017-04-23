package com.eg.yevgeniy.egontrial.Activities.Dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.eg.yevgeniy.egontrial.Activities.Home.HomeActivity;
import com.eg.yevgeniy.egontrial.Activities.Login.LoginActivity;
import com.eg.yevgeniy.egontrial.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class CustomerFragment extends Fragment implements OnChartGestureListener, OnChartValueSelectedListener {



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

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((HomeActivity) getActivity()).setActionBarTitle("Visualization");

        View v = inflater.inflate(R.layout.fragment_customer, container, false);
        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recycler_stations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CustomerAdapter(postRef);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LineChart bchart = (LineChart) v.findViewById(R.id.chart1);
        barchart(bchart);

        return v;
    }

    public void barchart(LineChart chart){

        LineChart bchart = chart;

        bchart.setOnChartGestureListener(this);
        bchart.setOnChartValueSelectedListener(this);


        ArrayList<Entry> yVals = setYAxisValues();

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "DataSet 1");
        set1.setFillAlpha(110);
        set1.setFillColor(Color.parseColor("#03324a"));

        // set the line to be drawn like this "- - - - - -"
        // set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);
        set1.setDrawValues(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(dataSets);

        // set data
        bchart.setData(data);

        Legend l = bchart.getLegend();
        l.setForm(Legend.LegendForm.NONE);

        Description description = new Description();
        description.setTextSize(10);
        description.setText("Monthly Consumption");

        bchart.setDescription(description);

        bchart.getAxisLeft().setDrawLabels(true);
        bchart.getAxisRight().setDrawLabels(false);
        bchart.getXAxis().setDrawLabels(false);
        bchart.getLegend().setEnabled(false);
        IAxisValueFormatter axisValueFormatter = new HourAxisValueFormatter(100800);
    }



    private ArrayList<Entry> setYAxisValues(){
        ArrayList<Entry> yVals = new ArrayList<>();
        yVals.add(new Entry(1, 30, "June"));
        yVals.add(new Entry(2, 35, "June"));
        yVals.add(new Entry(3, 31, "June"));
        yVals.add(new Entry(4, 42, "July"));
        yVals.add(new Entry(5, 35, "August"));
        yVals.add(new Entry(6, 30, "June"));
        yVals.add(new Entry(7, 25, "June"));
        yVals.add(new Entry(8, 31, "June"));
        yVals.add(new Entry(9, 41, "July"));
        yVals.add(new Entry(10, 35, "August"));
        yVals.add(new Entry(11, 30, "June"));
        yVals.add(new Entry(12, 35, "June"));
        yVals.add(new Entry(13, 31, "June"));
        yVals.add(new Entry(14, 32, "July"));
        yVals.add(new Entry(15, 35, "August"));
        yVals.add(new Entry(16, 30, "June"));
        yVals.add(new Entry(17, 30, "June"));
        yVals.add(new Entry(18, 25, "June"));
        yVals.add(new Entry(19, 30, "July"));
        yVals.add(new Entry(20, 30, "August"));
        yVals.add(new Entry(21, 30, "June"));
        yVals.add(new Entry(22, 35, "June"));
        yVals.add(new Entry(23, 31, "June"));
        yVals.add(new Entry(24, 36, "July"));
        yVals.add(new Entry(25, 35, "August"));
        yVals.add(new Entry(26, 30, "June"));
        yVals.add(new Entry(27, 25, "June"));
        yVals.add(new Entry(28, 31, "June"));
        yVals.add(new Entry(29, 41, "July"));
        yVals.add(new Entry(30, 35, "August"));
        return yVals;
    }


    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }
}
