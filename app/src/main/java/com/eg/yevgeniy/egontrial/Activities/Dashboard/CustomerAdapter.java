package com.eg.yevgeniy.egontrial.Activities.Dashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eg.yevgeniy.egontrial.Activities.Dashboard.CustomerViewHolder;
import com.eg.yevgeniy.egontrial.Activities.Dashboard.Person;
import com.eg.yevgeniy.egontrial.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yelena on 4/11/17.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerViewHolder> {


    private List<Person> peopleList = new ArrayList<>();


    public CustomerAdapter() {
        peopleList.add(new Person("15,5€", "31,5€", "31 kWh", "16€", "15kWh", "May"));
        peopleList.add(new Person("A", "A", "A", "A", "A", "Apr"));
        peopleList.add(new Person("E", "E", "E", "E", "E", "Mar"));
        peopleList.add(new Person("A", "B", "C", "D", "E", "May"));
        peopleList.add(new Person("A", "A", "A", "A", "A", "Apr"));
        peopleList.add(new Person("E", "E", "E", "E", "E", "Mar"));
        peopleList.add(new Person("A", "B", "C", "D", "E", "May"));
        peopleList.add(new Person("A", "A", "A", "A", "A", "Apr"));
        peopleList.add(new Person("E", "E", "E", "E", "E", "Mar"));
    }



    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.customer_card, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomerViewHolder holder, int position) {
        Person person = peopleList.get(position);
        holder.textView.setText(person.getKwhGraz());
        holder.textView2.setText(person.getKwhShare());
        holder.textView3.setText(person.getPriceGraz());
        holder.textView4.setText(person.getPriceShare());
        holder.textView5.setText(person.getTotal_price());
        holder.dateView.setText(person.getDate());

    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

}
