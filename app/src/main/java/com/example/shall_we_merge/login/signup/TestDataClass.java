package com.example.shall_we_merge.login.signup;

import com.google.gson.annotations.SerializedName;

public class TestDataClass {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("date")
    private String date;

    public TestDataClass(String id, String name, String date){
        this.id = id;
        this.name = name;
        this.date = date;
    }

    /*public String getName(){
        return name;
    }*/

    public String getId(){
        return id;
    }

    public String getName(){
        return  name;
    }

    public String getDate(){
        return  date;
    }

    @Override
    public String toString(){
        return  "ID: " + id + ", name: " + name + ", date: " + date;
    }
}
