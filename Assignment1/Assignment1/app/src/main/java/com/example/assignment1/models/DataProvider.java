package com.example.assignment1.models;

import android.content.Context;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DataProvider {
    private final Context context;
    private final HashMap<String, List<String>> citiesStoresMap = new HashMap<>();

    //  Constructor to initialize the DataProvider with pre-defined city and store data.
    public DataProvider(Context context) {
        initializeData();
        this.context = context;
    }

    // Initializing the data with given city and store data.
    private void initializeData() {
        citiesStoresMap.put("Waterloo", Arrays.asList("65 University Ave E", "415 King St", "585 Weber St"));
        citiesStoresMap.put("London", Arrays.asList("616 Wharncliffe Rd", "1885 Huron St", "670 Wonderland Road", "1181 Highbury Ave"));
        citiesStoresMap.put("Milton", Arrays.asList("900 Steeles Ave", "80 Market Dr", "820 Main St"));
        citiesStoresMap.put("Mississauga", Arrays.asList("144 Dundas St", "30 Eglinton Ave", "6075 Creditview Rd"));
    }

    public ArrayAdapter<String> getCityAdapter() {
        String[] citiesArray = citiesStoresMap.keySet().toArray(new String[0]);
        return new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, citiesArray);
    }


    public List<String> getFlavorList(String selectedBeverage) {
        if ("coffee".equals(selectedBeverage)) {
            return Arrays.asList("None", "Pumpkin Spice", "Chocolate");
        } else if ("tea".equals(selectedBeverage)) {
            return Arrays.asList("None", "Lemon", "Ginger");
        } else {
            return Arrays.asList("None");
        }
    }

    // Getting a list of stores for a specific city.
    public List<String> getStoresForCity(String selectedCity) {
        List<String> stores = citiesStoresMap.get(selectedCity);
        return stores != null ? stores : new ArrayList<>();
    }
}
