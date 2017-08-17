package be.hogent.Eva2017g5.EVAH5.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sofie.
 */

public class Ingredient {

    @SerializedName("name")
    private String name;
    @SerializedName("amount")
    private int amount;

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
