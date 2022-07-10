package com.example.shall_we_merge.login;

import com.google.gson.annotations.SerializedName;

public class LoginDataClass {
    //@SerializedName("name")
    //private String name;

    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    public void setId(String newId){
        this.id = newId;
    }

    public void setPassword(String newPw){
        this.password = newPw;
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
