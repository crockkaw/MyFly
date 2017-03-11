package com.example.kawka.myfly.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NalotAktualny {

    @SerializedName("items")
    @Expose
    private List<NalotAktualnyItems> items = null;


    public List<NalotAktualnyItems> getItems() {
        return items;
    }

    public void setItems(List<NalotAktualnyItems> items) {
        this.items = items;
    }


}