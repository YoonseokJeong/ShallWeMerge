package com.example.shall_we_merge.schedulemanage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.shall_we_merge.api.ScheduleDataClass;

import java.util.List;

public class ScheduleListViewModelFactory implements ViewModelProvider.Factory{

    private Application application;

    public ScheduleListViewModelFactory(Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new ScheduleListViewModel(application);
    }
}
