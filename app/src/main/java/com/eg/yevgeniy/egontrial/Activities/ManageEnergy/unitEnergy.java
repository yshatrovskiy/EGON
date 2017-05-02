package com.eg.yevgeniy.egontrial.Activities.ManageEnergy;

/**
 * Created by yelena on 5/2/17.
 */

public class unitEnergy {

    int aptNumber;
    int energyAmount;
    String energyDisplay;

    public unitEnergy(int aptNumber, int energyAmount) {
        this.aptNumber = aptNumber;
        this.energyAmount = energyAmount;
    }

    public String getAptNumber() {
        return aptNumber + "";
    }

    public void setAptNumber(int aptNumber) {
        this.aptNumber = aptNumber;
    }

    public int getEnergyAmount() {
        return energyAmount;
    }

    public void setEnergyAmount(int energyAmount) {
        this.energyAmount = energyAmount;
    }

    public String getEnergyDisplay() {
        energyDisplay = energyAmount + " €";
        return energyDisplay;
    }
}
