package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */
public class Business implements Serializable {

    public String key;
    public String number;
    public String name;
    public String primaryBusiness;
    public String address;
    public String province;

    /**
     * Default constructor required for calls to DataSnapshot.getValue
     */
    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Constructor with parameters
     * @param key
     * @param number
     * @param name
     * @param primaryBusiness
     * @param address
     * @param province
     */
    public Business(String key, String number, String name, String primaryBusiness, String address, String province) {
        this.key = key;
        this.number = number;
        this.name = name;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province = province;
    }

    /**
     * Returns a map of the business's attributes
     * @return attributes
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("key", key);
        result.put("number", number);
        result.put("name", name);
        result.put("primaryBusiness", primaryBusiness);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
