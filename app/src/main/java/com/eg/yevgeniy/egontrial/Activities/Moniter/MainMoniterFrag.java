package com.eg.yevgeniy.egontrial.Activities.Moniter;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cardiomood.android.controls.gauge.SpeedometerGauge;
import com.eg.yevgeniy.egontrial.Activities.Dashboard.DayAxisValueFormatter;
import com.eg.yevgeniy.egontrial.R;
import com.github.anastr.speedviewlib.AwesomeSpeedometer;
import com.github.anastr.speedviewlib.PointerSpeedometer;
import com.github.anastr.speedviewlib.ProgressiveGauge;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import com.cardiomood.android.controls.gauge.BatteryIndicatorGauge;
import com.cardiomood.android.controls.gauge.SpeedometerGauge;
import com.cardiomood.android.controls.progress.CircularProgressBar;

import java.util.ArrayList;
import java.util.Random;


public class MainMoniterFrag extends android.support.v4.app.Fragment {

    PointerSpeedometer pointerSpeedometer;

    private BatteryIndicatorGauge batteryindicator;

    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset;
    BarData BARDATA ;

    public MainMoniterFrag() {
        // Required empty public constructor
    }
    public static MainMoniterFrag newInstance() {
        MainMoniterFrag fragment = new MainMoniterFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.fragment_main_moniter, container, false);

        batteryindicator = (BatteryIndicatorGauge)v.findViewById(R.id.batteryindicator);
        batteryindicator.setValue(50, 1000, 300);




//        SpeedometerGauge speedometer;
//
//        // Customize SpeedometerGauge
//        speedometer = (SpeedometerGauge) v.findViewById(R.id.speedometer);
//
//        // Add label converter
//        speedometer.setLabelConverter(new SpeedometerGauge.LabelConverter() {
//            @Override
//            public String getLabelFor(double progress, double maxProgress) {
//                return String.valueOf((int) Math.round(progress));
//            }
//        });
//
//        // configure value range and ticks
//        speedometer.setMaxSpeed(300);
//        speedometer.setMajorTickStep(30);
//        speedometer.setMinorTicks(2);
//
//        // Configure value range colors
//        speedometer.setBackgroundColor(Color.TRANSPARENT);
//        speedometer.addColoredRange(30, 140, Color.GREEN);
//        speedometer.addColoredRange(140, 180, Color.YELLOW);
//        speedometer.addColoredRange(180, 400, Color.RED);
//
//
//        speedometer.setSpeed(95, 1000, 300);




        chart = (BarChart) v.findViewById(R.id.chartMain);
        BARENTRY = new ArrayList<>();
        BarEntryLabels = new ArrayList<String>();
        AddValuesToBARENTRY();
        Bardataset = new BarDataSet(BARENTRY, "Usage");


        Bardataset.setColor(Color.parseColor("#FFA500"));
        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(Bardataset);

        BARDATA = new BarData(Bardataset);
        chart.getDescription().setEnabled(false);
        chart.setData(BARDATA);
        chart.animateY(3000);

        Legend l = chart.getLegend();
        l.setEnabled(false);


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
