package com.eg.yevgeniy.egontrial.Activities.ManageEnergy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eg.yevgeniy.egontrial.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yelena on 5/2/17.
 */

public class AptAdapter extends RecyclerView.Adapter<AptViewHolder> {

    public List<unitEnergy> unitList = new ArrayList<>();

    public AptAdapter(){
        unitList.add(new unitEnergy(7, 45));
        unitList.add(new unitEnergy(15, 7));
        unitList.add(new unitEnergy(7, 45));
        unitList.add(new unitEnergy(15, 7));
    }

    @Override
    public AptViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.apt_card,parent,false);

        return new AptViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AptViewHolder holder, int position) {

        unitEnergy energy = unitList.get(position);
        holder.textView.setText(energy.getAptNumber());
        holder.textView.setText(energy.getEnergyDisplay());

    }

    @Override
    public int getItemCount() {
        return unitList.size();
    }

    public void removeUnit(int position){

        unitList.remove(position);

    }

    public void addUnit(int x, int y){
        unitList.add(new unitEnergy(x, y));
    }

    public int getTotalValue(){
        int total = 0;

        for(int i = 0; i <= unitList.size(); i++){
            total += unitList.get(i).getEnergyAmount();
        }
        return total;
    }
}
