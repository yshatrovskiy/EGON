package com.eg.yevgeniy.egontrial.Activities.Dashboard;

import java.util.Calendar;

/**
 * Created by yelena on 4/10/17.
 */




public class Person {

    String priceShare;
    String priceGraz;
    String kwhShare;
    String kwhGraz;
    String total_price;
    String date;


    public void setDate(String date) {
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public Person(){
    }

    public Person(String priceShare, String priceGraz, String kwhShare, String kwhGraz, String total_price, String date) {
        this.priceShare = priceShare;
        this.date = date;
        this.priceGraz = priceGraz;
        this.kwhShare = kwhShare;
        this.kwhGraz = kwhGraz;
        this.total_price = total_price;
    }

    public String getPriceShare() {
        return priceShare;
    }

    public void setPriceShare(String priceShare) {
        this.priceShare = priceShare;
    }

    public String getPriceGraz() {
        return priceGraz;
    }

    public void setPriceGraz(String priceGraz) {
        this.priceGraz = priceGraz;
    }

    public String getKwhShare() {
        return kwhShare;
    }

    public void setKwhShare(String kwhShare) {
        this.kwhShare = kwhShare;
    }

    public String getKwhGraz() {
        return kwhGraz;
    }

    public void setKwhGraz(String kwhGraz) {
        this.kwhGraz = kwhGraz;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
}
