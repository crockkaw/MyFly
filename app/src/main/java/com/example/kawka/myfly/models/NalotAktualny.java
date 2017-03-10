package com.example.kawka.myfly.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NalotAktualny {

    @SerializedName("items")
    @Expose
    private List<NalotCalkowityItems> items = null;


    public List<NalotCalkowityItems> getItems() {
        return items;
    }

    public void setItems(List<NalotCalkowityItems> items) {
        this.items = items;
    }


}