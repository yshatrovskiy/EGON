package com.eg.yevgeniy.egontrial.Activities.Energy_Generation;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eg.yevgeniy.egontrial.Activities.Dashboard.DayAxisValueFormatter;
import com.eg.yevgeniy.egontrial.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DayShare#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DayShare extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private BarChart mChart;


    public DayShare() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DayShare.
     */
    // TODO: Rename and change types and number of parameters
    public static DayShare newInstance(String param1, String param2) {
        DayShare fragment = new DayShare();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_day_share, container, false);

        mChart = (BarChart)v.findViewById(R.id.chartMain);
        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        mChart.setDrawValueAboveBar(false);
        mChart.setHighlightFullBarEnabled(false);

        // change the position of the y-labels
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setEnabled(false);
        leftAxis.setDrawGridLines(false);
        mChart.getAxisRight().setEnabled(false);

        XAxis xLabels = mChart.getXAxis();
        xLabels.setDrawGridLines(false);
        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
        xLabels.setGranularity(1f);
        xLabels.setLabelCount(7);

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);
        xLabels.setValueFormatter(xAxisFormatter);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);


        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 21; i < 30; i++) {
            int mult = 10;
            int val1 = (int) (Math.random() * mult) + mult / 3;
            int val2 = (int) (Math.random() * mult) + mult / 3;
            int val3 = (int) (Math.random() * mult) + mult / 3;

            yVals1.add(new BarEntry(i, new float[]{val1, val2, val3},
                    getResources().getDrawable(R.drawable.ic_drawer)));
        }




        BarDataSet set1;


        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "");
            set1.setDrawIcons(false);
            set1.setColors(getColors());
            set1.setStackLabels(new String[]{"Purchased", "Generated & Consumed"});

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextColor(Color.WHITE);

            mChart.setData(data);
        }
        mChart.setBackgroundColor(Color.TRANSPARENT);

        mChart.setFitBars(true);
        mChart.invalidate();

        return v;
    }


    private int[] getColors() {
        int[] colors = {Color.parseColor("#5fbc5a"), Color.parseColor("#03324a")};
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colors[i];
        }
        return colors;
    }

}
