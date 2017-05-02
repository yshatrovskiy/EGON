package com.eg.yevgeniy.egontrial.Activities.Dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.eg.yevgeniy.egontrial.R;

/**
 * Created by yelena on 4/11/17.
 */

public class CustomerViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;
    public TextView textView2;
    public TextView textView3;
    public TextView textView4;
    public TextView textView5;
    public TextView dateView;

    public CustomerViewHolder(View itemView){
        super(itemView);

        this.textView = (TextView)itemView.findViewById(R.id.cardView1);
        this.textView2 = (TextView)itemView.findViewById(R.id.cardView2);
        this.textView3 = (TextView)itemView.findViewById(R.id.cardView3);
        this.textView4 = (TextView)itemView.findViewById(R.id.cardView4);
        this.textView5 = (TextView)itemView.findViewById(R.id.cardView5);
        this.dateView = (TextView) itemView.findViewById(R.id.dateView);

    }

}
