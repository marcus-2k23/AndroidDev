package com.example.task3.models;

public class HostingCost {

    // Declare instance variables to store customer information and hosting costs
    private String customerName;
    private String province;
    private String webSpace;
    private String salesDate;
    double dbCost;
    double stagingCost;
    double planCost;

    // Constructor to initialize the HostingCost object with customer inputs
    public HostingCost(String customerName,String province,String webSpace, String salesDate, double dbCost, double stagingCost, double planCOst){
        this.customerName = customerName;
        this.province = province;
        this.webSpace = webSpace;
        this.salesDate = salesDate;
        this.dbCost = dbCost;
        this.stagingCost = stagingCost;
        this.planCost = planCOst;
    }

    // Method to calculate the total hosting cost
    public String getHostingCost(){
        // Calculating additional costs
        double additionalCost = dbCost + stagingCost;
        double webSpaceCost = 0;
        double totalCost = 0;

        // Calculating web space cost for the selected option
        switch (webSpace){
            case "10GB":
                webSpaceCost=0;
                break;
            case "20GB":
                webSpaceCost=6.5;
                break;
            case "40GB":
                webSpaceCost=8.5;
                break;

        }
        // Calculating the total hosting cost
        totalCost = planCost + additionalCost + webSpaceCost;
        // Returning the hosting cost detail
        return "For" + customerName + "from" + province + "Total Hosting Cost is" + String.valueOf(totalCost) + "Sales Date:" + salesDate;
    }
}
