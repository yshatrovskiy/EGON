package com.eg.yevgeniy.egontrial.Activities.ManageEnergy;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    TextView availableKwHBox;
    TextView howMuchBox;
    TextView totalToSellorBuy;
    Button plus;
    Button minus;



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

        Button button = (Button)v.findViewById(R.id.button2);
        button.setOnClickListener(test);

                RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.apt_recycler_stations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AptAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);



        return v;
    }



    public String determinePrice(int kwh){

        String finalPrice = "";



        return finalPrice;
    }

    public int determineAvailablekWh(){

        return adapter.getTotalValue();
    }

    View.OnClickListener test = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button2:
                    adapter.removeUnit(0);
                    adapter.notifyDataSetChanged();
                    break;
            }



        }
    };


}
