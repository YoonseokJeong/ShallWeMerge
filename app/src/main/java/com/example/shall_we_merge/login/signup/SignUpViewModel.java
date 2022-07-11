package com.example.shall_we_merge.login.signup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;


public class SignUpViewModel extends ViewModel {

    private MutableLiveData<String> id = new MutableLiveData<String>("");

    public MutableLiveData<String> getId() {
        return id;
    }

    private MutableLiveData<String> pw = new MutableLiveData<String>("");

    public MutableLiveData<String> getPw() {
        return pw;
    }

    private MutableLiveData<String> pwCheck = new MutableLiveData<String>("");

    private LiveData<Boolean> pwValid = Transformations.switchMap(pw,
            (pw) -> {
                return new MutableLiveData<Boolean>(pw.length() > 7);
            });

    public LiveData<Boolean> getPwValid() { return pwValid; }

    public MutableLiveData<String> getPwCheck() {
        return pwCheck;
    }

    private LiveData<Boolean> pwChecked = Transformations.switchMap(pwCheck,
            (pwCheck) -> {
                if (pwCheck.equals("") || pw.getValue().equals("")) {
                    return new MutableLiveData<Boolean>(true);
                }
                else{
                    return new MutableLiveData<Boolean>(pwCheck.equals(pw.getValue()));
                }
            } );
    
    public LiveData<Boolean> getPwChecked() { return pwChecked; }

    private  LiveData<Boolean> signUpButtonEnabled = Transformations.switchMap(pwChecked,
            (pwChecked) -> {
                if(pwChecked && pwValid.getValue()){
                    return new MutableLiveData<Boolean>(true);
                }
                else{
                    return new MutableLiveData<Boolean>(false);
                }
            });
    public LiveData<Boolean> getSignUpButtonEnabled() { return signUpButtonEnabled; }

    private MutableLiveData<Boolean> dupChecked = new MutableLiveData<Boolean>(false);

    public LiveData<Boolean> getDupChecked() {
        return dupChecked;
    }
    public void setDupChecked(boolean bool){
        dupChecked.setValue(bool);
    }
}
