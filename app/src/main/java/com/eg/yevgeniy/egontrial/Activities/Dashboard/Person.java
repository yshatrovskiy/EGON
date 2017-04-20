package com.eg.yevgeniy.egontrial.Activities.Dashboard;

/**
 * Created by yelena on 4/10/17.
 */




public class Person {

    String month;
    String price;

    public Person(){
    }

    public Person(String month, String price) {
        this.month = month;
        this.price = price;
    }

    public String getMonth() {
        return month;
    }
    public String getPrice() {
        return price;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
