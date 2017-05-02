package com.eg.yevgeniy.egontrial.Activities.ManageEnergy;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eg.yevgeniy.egontrial.R;

/**
 * Created by yelena on 5/2/17.
 */

public class AptViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    public TextView textView2;



    public AptViewHolder(View itemView) {
        super(itemView);

        this.textView = (TextView) itemView.findViewById(R.id.textAptNumber);
        this.textView2 = (TextView) itemView.findViewById(R.id.textAptValue);


    }
}
