package com.eg.yevgeniy.egontrial.Activities.Dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eg.yevgeniy.egontrial.R;

/**
 * Created by yelena on 4/11/17.
 */

public class CustomerViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    public TextView textView2;

    public CustomerViewHolder(View itemView){
        super(itemView);

        this.textView = (TextView)itemView.findViewById(R.id.cardView1);
        this.textView2 = (TextView)itemView.findViewById(R.id.cardView2);
    }

}
