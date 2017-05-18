package com.eg.yevgeniy.egontrial.Activities.Energy_Generation;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eg.yevgeniy.egontrial.Activities.Home.HomeActivity;
import com.eg.yevgeniy.egontrial.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GraphHolderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraphHolderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    public GraphHolderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GraphHolderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GraphHolderFragment newInstance(String param1, String param2) {
        GraphHolderFragment fragment = new GraphHolderFragment();
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
        ((HomeActivity)getActivity()).closeDrawer();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((HomeActivity)getActivity()).closeDrawer();



        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ((HomeActivity) getActivity()).setActionBarTitle("Energy Consumption vs. Production");

        View v = inflater.inflate(R.layout.fragment_graph_holder, container, false);

        //Button day = (Button)v.findViewById(R.id.day);
        Button month = (Button)v.findViewById(R.id.month);
        Button year = (Button)v.findViewById(R.id.year);

        //day.setOnClickListener(here);
        month.setOnClickListener(here);
        year.setOnClickListener(here);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.graphMain, new MonthShare())
                .addToBackStack(null)
                .commit();



        // Inflate the layout for this fragment
        return v ;
    }

    private View.OnClickListener here = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();


            if(v.getId() == R.id.month){
                fragmentManager.beginTransaction()
                        .replace(R.id.graphMain, new MonthShare())
                        .addToBackStack(null)
                        .commit();
            }else if(v.getId() == R.id.month){
                fragmentManager.beginTransaction()
                        .replace(R.id.graphMain, new MonthShare())
                        .addToBackStack(null)
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.graphMain, new ShareElectric())
                        .addToBackStack(null)
                        .commit();
            }
        }
    };
}
