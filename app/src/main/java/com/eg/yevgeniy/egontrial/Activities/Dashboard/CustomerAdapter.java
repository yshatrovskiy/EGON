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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yelena on 4/11/17.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerViewHolder> {

    private Context mContext;
    private DatabaseReference databaseReference;
    private Map<String, Person> peoples = new HashMap<>();
    private List<Person> peopleList = new ArrayList<>();

    public CustomerAdapter(DatabaseReference postRef){


        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = postRef.child(uid).child("Bills");

        peoples.put("Jan", new Person("One A", "Two B"));
        peoples.put("Feb", new Person("Three A", "Four B"));
        peoples.put("Mar", new Person("Five A", "Six B"));

        //databaseReference = FirebaseDatabase.getInstance().getReference().child("Bills");
        //databaseReference.setValue("test");
        //databaseReference.setValue(peoples);


        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Person person = dataSnapshot.getValue(Person.class);
                peopleList.add(person);
                notifyItemInserted(peopleList.size());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Person person = dataSnapshot.getValue(Person.class);
                peopleList.add(person);
                notifyItemInserted(peopleList.size());

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Person person = dataSnapshot.getValue(Person.class);
                peopleList.add(person);
                notifyItemInserted(peopleList.size()+1);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addChildEventListener(childEventListener);
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
        holder.textView.setText(person.getMonth());
        holder.textView2.setText(person.getPrice());

    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }
}
