package com.example.shall_we_merge.api;

import com.google.gson.annotations.SerializedName;

public class IdDataClass {

    @SerializedName("id")
    private String id;

    @SerializedName("message")
    private String message;

    public IdDataClass(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public String getMessage() {
        return message;
    }

    public boolean idDuplicated(){ return message.equals("에러!"); }

    @Override
    public String toString(){
        return "ID: " + id +  ", MSG: " + message;
    }


}
