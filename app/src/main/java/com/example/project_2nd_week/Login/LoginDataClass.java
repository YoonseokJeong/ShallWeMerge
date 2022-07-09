package com.example.project_2nd_week.Login;

import com.google.gson.annotations.SerializedName;

public class LoginDataClass {
    //@SerializedName("name")
    //private String name;

    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    public LoginDataClass(String id, String pw){
        this.id = id;
        this.password = pw;
    }

    /*public String getName(){
        return name;
    }*/

    public String getId(){
        return id;
    }

    public String getPassword(){
        return  password;
    }

    @Override
    public String toString(){
        return  "ID: " + id + ", PW: " + password;
    }

}
