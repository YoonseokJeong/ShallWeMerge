package com.example.shall_we_merge.api;

import com.google.gson.annotations.SerializedName;

public class AccountDataClass {

    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    @SerializedName("message")
    private String message = null;

    public AccountDataClass(String id, String password){
        this.id = id;
        this.password = password;
    }

    public String getId(){
        return id;
    }

    public String getPassword(){
        return  password;
    }

    public String getMessage() {
        return message;
    }

    public boolean loginSuccessed(){
        return message.equals("로그인 성공!");
    }

    @Override
    public String toString(){
        return  "ID: " + id + ", PW: " + password + ", MSG: " + message;
    }

}
