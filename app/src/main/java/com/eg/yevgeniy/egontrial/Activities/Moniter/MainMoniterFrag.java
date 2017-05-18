package com.eg.yevgeniy.egontrial.Activities.Moniter;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cardiomood.android.controls.gauge.SpeedometerGauge;
import com.eg.yevgeniy.egontrial.Activities.Dashboard.DayAxisValueFormatter;
import com.eg.yevgeniy.egontrial.Activities.Home.HomeActivity;
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
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import pl.pawelkleczkowski.customgauge.CustomGauge;

import static com.eg.yevgeniy.egontrial.Activities.E_Car.CarShare.toCalendar;


public class MainMoniterFrag extends android.support.v4.app.Fragment {

    PointerSpeedometer pointerSpeedometer;

    private BatteryIndicatorGauge batteryindicator;
    Button autoButton;
    Button offButton;
    TextView statusTxt;


    private Calendar endTime;
    private CustomGauge guage3;


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


        ((HomeActivity) getActivity()).setActionBarTitle("Heater");

        View v = inflater.inflate(R.layout.fragment_main_moniter, container, false);
        autoButton = (Button)v.findViewById(R.id.auto);
        autoButton.setOnClickListener(auto);
        offButton = (Button)v.findViewById(R.id.off);
        offButton.setOnClickListener(off);
        statusTxt = (TextView)v.findViewById(R.id.statusText);



        //Guage
        guage3 = (CustomGauge)v.findViewById(R.id.gauge3);
        guage3.setValue(45);

        chart = (BarChart) v.findViewById(R.id.chartMain);
        BARENTRY = new ArrayList<>();
        BarEntryLabels = new ArrayList<String>();
        AddValuesToBARENTRY();
        Bardataset = new BarDataSet(BARENTRY, "kWh Used");


        Bardataset.setColor(R.color.colorPrimary);
        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(Bardataset);

        BARDATA = new BarData(Bardataset);
        chart.getDescription().setEnabled(false);
        chart.setData(BARDATA);
        chart.animateY(1000);

        Legend l = chart.getLegend();
        l.setEnabled(true);


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

        BARENTRY.add(new BarEntry(1f, 121));
        BARENTRY.add(new BarEntry(2f, 110));
        BARENTRY.add(new BarEntry(3f, 121));
        BARENTRY.add(new BarEntry(4f, 118));
        BARENTRY.add(new BarEntry(5f, 122));
        BARENTRY.add(new BarEntry(6f, 120));

    }

    View.OnClickListener auto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pickDate();
        }
    };

    public void pickDate(){

        final Calendar date = Calendar.getInstance();
        final SlideDateTimeListener listener = new SlideDateTimeListener() {

            @Override
            public void onDateTimeSet(Date date1)
            {
                date.setTime(date1);
                String hours = date.get(Calendar.HOUR_OF_DAY) + "";
                String minute = date.get(Calendar.MINUTE) + "";
                statusTxt.setText("Start at: " + hours + ":" + minute);
            }
        };

        new SlideDateTimePicker.Builder(getFragmentManager())
                .setListener(listener)
                .setInitialDate(date.getTime())
                .setMinDate(date.getTime())
                .setIs24HourTime(true)
                .setTheme(SlideDateTimePicker.HOLO_LIGHT)
                .build()
                .show();

    }
    View.OnClickListener off = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            statusTxt.setText("");
        }
    };

}
