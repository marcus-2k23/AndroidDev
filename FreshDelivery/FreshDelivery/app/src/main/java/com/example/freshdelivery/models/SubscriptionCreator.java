package com.example.mdgfreshdelivery.models;

public class SubscriptionCreator {
    private final String customer_name;
    private final String phone;
    private final String plan;
    private final String planType;
    private final boolean lemonadeChk;
    private final boolean milkChk;
    private final String deliveryTypes;
    private final String subscription_date;

    public SubscriptionCreator(String customer_name, String phone, String plan, String planType, boolean lemonadeChk, boolean milkChk, String deliveryTypes, String subscription_date)
    {

        this.customer_name = customer_name;
        this.phone = phone;

        this.plan = plan;
        this.planType = planType;

        this.lemonadeChk = lemonadeChk;
        this.milkChk = milkChk;

        this.deliveryTypes = deliveryTypes;
        this.subscription_date = subscription_date;
    }
    public String Creator()
    {
        String print_string = "";
        print_string += "Custom Name: " + customer_name + "\n";
        print_string += "Phone: " + phone + "\n";
        print_string += "Subscription Plan: " + plan + "\n";
        print_string += "Plan Type: " + planType + "\n";
        print_string += "Milk Additional : " + (milkChk ? "Yes" : "No") + "\n";
        print_string += "Lemonade Additional : " + (lemonadeChk ? "Yes" : "No") + "\n";
        print_string += "Type of Delivery: " + deliveryTypes + "\n";
        print_string += "Subscription Date: " + subscription_date + "\n";
        return print_string;
    }


}