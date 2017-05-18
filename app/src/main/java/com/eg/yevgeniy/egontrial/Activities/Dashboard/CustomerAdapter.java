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
        peopleList.add(new Person("74,8€", "94,03€", "106 kWh", "19,16€", "356 kWh", "May"));
        peopleList.add(new Person("75,19€", "94,44€", "107 kWh", "19,25€", "358 kWh", "Apr"));
        peopleList.add(new Person("83,11€", "104,39€", "118 kWh", "21,27€", "395 kWh", "Mar"));
        peopleList.add(new Person("77,61€", "97,48€", "110 kWh", "19.87€", "369 kWh", "Feb"));
        peopleList.add(new Person("90,06€", "113,12€", "128,11 kWh", "23,05€", "428kWh", "Jan"));
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
