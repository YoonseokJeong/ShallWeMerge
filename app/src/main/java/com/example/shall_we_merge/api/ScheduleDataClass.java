package com.example.shall_we_merge.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleDataClass {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name = "sdasd";

    @SerializedName("date")
    private String date = "sdsdsd";

    @SerializedName("places")
    private List<PlaceDataClass> places;

    public ScheduleDataClass(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public List<PlaceDataClass> getPlaces() {
        return places;
    }

    @Override
    public String toString(){
        return "ID: " + id + ", name: " + name + ", date: " + date + ", places: " + places.toString();
    }
}
