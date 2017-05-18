package com.eg.yevgeniy.egontrial.Activities.Energy_Generation;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cardiomood.android.controls.gauge.BatteryIndicatorGauge;
import com.eg.yevgeniy.egontrial.R;
import com.github.lzyzsd.circleprogress.ArcProgress;

import pl.pawelkleczkowski.customgauge.CustomGauge;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EnergyBreakDownFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnergyBreakDownFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArcProgress arcProgress1;
    private ArcProgress arcProgress2;
    private ArcProgress arcProgress3;
    private BatteryIndicatorGauge batteryindicator;
    TextView percentText;
    private CustomGauge guage3;




    public EnergyBreakDownFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EnergyBreakDownFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EnergyBreakDownFragment newInstance(String param1, String param2) {
        EnergyBreakDownFragment fragment = new EnergyBreakDownFragment();
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

        View v = inflater.inflate(R.layout.fragment_energy_break_down, container, false);

        arcProgress1 = (ArcProgress)v.findViewById(R.id.arc_progress);
        arcProgress1.setBottomText("PV");
        arcProgress1.setProgress(35);
        arcProgress1.setTextSize(35);
        arcProgress1.setTextColor(Color.parseColor("#03324a"));
        arcProgress1.setSuffixTextSize(10);
        arcProgress1.setSuffixText("kWh");
        arcProgress1.setSuffixTextPadding(10);
        arcProgress1.setFinishedStrokeColor(Color.parseColor("#3BB39D"));
        arcProgress1.setUnfinishedStrokeColor(Color.parseColor("#CCD0D2"));

//        batteryindicator = (BatteryIndicatorGauge)v.findViewById(R.id.batteryindicator);
//        batteryindicator.setValue(50, 1000, 300);
//        batteryindicator.getValue();

        percentText = (TextView)v.findViewById(R.id.percenttxt);
        percentText.setText(27 + "%");

        guage3 = (CustomGauge)v.findViewById(R.id.gauge3);
        guage3.setValue(100);

        arcProgress2 = (ArcProgress)v.findViewById(R.id.arc_progress2);
        arcProgress2.setBottomText("GRID");
        arcProgress2.setProgress(0);
        arcProgress2.setTextSize(35);
        arcProgress2.setTextColor(Color.parseColor("#03324a"));
        arcProgress2.setSuffixTextSize(10);
        arcProgress2.setSuffixText("kWh");
        arcProgress2.setSuffixTextPadding(10);
        arcProgress2.setFinishedStrokeColor(Color.parseColor("#3BB39D"));
        arcProgress2.setUnfinishedStrokeColor(Color.parseColor("#CCD0D2"));



        arcProgress3 = (ArcProgress)v.findViewById(R.id.arcProgress3);
        arcProgress3.setBottomText("IN USE");
        arcProgress3.setProgress(55);
        arcProgress3.setTextSize(35);
        arcProgress3.setTextColor(Color.parseColor("#03324a"));
        arcProgress3.setSuffixTextSize(10);
        arcProgress3.setSuffixText("kW");
        arcProgress3.setSuffixTextPadding(10);
        arcProgress3.setFinishedStrokeColor(Color.parseColor("#3BB39D"));
        arcProgress3.setUnfinishedStrokeColor(Color.parseColor("#CCD0D2"));


        return v;
    }

}
