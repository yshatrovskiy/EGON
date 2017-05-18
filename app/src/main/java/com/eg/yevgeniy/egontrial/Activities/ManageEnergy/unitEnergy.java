package com.eg.yevgeniy.egontrial.Activities.ManageEnergy;

/**
 * Created by yelena on 5/2/17.
 */

public class unitEnergy {

    String aptNumber;
    int energyAmount;
    String energyDisplay;

    public unitEnergy(String aptNumber, int energyAmount) {
        this.aptNumber = aptNumber;
        this.energyAmount = energyAmount;
    }

    public String getAptNumber() {
        return "Unit " + aptNumber;
    }

    public int getEnergyAmount() {
        return energyAmount;
    }

    public void setEnergyAmount(int energyAmount) {
        this.energyAmount = energyAmount;
    }

    public String getEnergyDisplay() {
        energyDisplay = energyAmount + " kWh";
        return energyDisplay;
    }
}
