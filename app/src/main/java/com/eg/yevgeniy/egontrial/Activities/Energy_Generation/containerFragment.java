package com.eg.yevgeniy.egontrial.Activities.Energy_Generation;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eg.yevgeniy.egontrial.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link containerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class containerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public containerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment containerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static containerFragment newInstance(String param1, String param2) {
        containerFragment fragment = new containerFragment();
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

        View v = inflater.inflate(R.layout.fragment_container, container, false);

        TabLayout tabLayout = (TabLayout)v.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)v.findViewById(R.id.pager);
        final com.eg.yevgeniy.egontrial.Activities.Energy_Generation.PagerAdapter adapter =
                new com.eg.yevgeniy.egontrial.Activities.Energy_Generation.PagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        GraphHolderFragment graphHolderFragment = new GraphHolderFragment();
//        getChildFragmentManager().findFragmentById(R.id.containerMain2);
//
//        getChildFragmentManager().beginTransaction().add(R.id.containerMain2, graphHolderFragment).commit();


        return v;
    }

}
