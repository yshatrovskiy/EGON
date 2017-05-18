package com.eg.yevgeniy.egontrial.Activities.Energy_Generation;

/**
 * Created by yelena on 5/11/17.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                GraphHolderFragment tab1 = new GraphHolderFragment();
                return tab1;
            case 1:
                EnergyBreakDownFragment tab2 = new EnergyBreakDownFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}