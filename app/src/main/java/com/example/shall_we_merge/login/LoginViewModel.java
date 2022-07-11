package com.example.shall_we_merge.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel{
    private MutableLiveData<String> id = new MutableLiveData<String>("");

    public MutableLiveData<String> getId() {
        return id;
    }

    private MutableLiveData<String> pw = new MutableLiveData<String>("");

    public MutableLiveData<String> getPw() {
        return pw;
    }

    private LiveData<Boolean> idEmpty = Transformations.switchMap(id,
            (id) -> {
                return new MutableLiveData<Boolean>(id.equals(""));
            } );

    public LiveData<Boolean> getIdEmpty() { return idEmpty; }

    private  LiveData<Boolean> pwEmpty = Transformations.switchMap(pw,
            (pw) -> {
                return new MutableLiveData<Boolean>(pw.equals(""));
            });

    public LiveData<Boolean> getPwEmpty() { return pwEmpty; }
}
