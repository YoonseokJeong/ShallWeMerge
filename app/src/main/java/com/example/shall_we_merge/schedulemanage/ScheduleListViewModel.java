package com.example.shall_we_merge.schedulemanage;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.shall_we_merge.Util;
import com.example.shall_we_merge.api.ScheduleDataClass;
import com.example.shall_we_merge.api.ShallWeMergeAPI;
import com.example.shall_we_merge.login.KakaoApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleListViewModel extends ViewModel {

    private Application application;
    private ShallWeMergeAPI shallWeMergeAPI = Util.getAPI();
    private String id;
    private List<ScheduleDataClass> data;

    public ScheduleListViewModel(Application application){
        this.application = application;
        id = ((KakaoApplication)application).getId();
        ScheduleDataClass scheduleDataClass = new ScheduleDataClass(id);

        shallWeMergeAPI.getSchedules(scheduleDataClass).enqueue(new Callback<List<ScheduleDataClass>>() {
            @Override
            public void onResponse(Call<List<ScheduleDataClass>> call, Response<List<ScheduleDataClass>> response) {
                data = response.body();
            }

            @Override
            public void onFailure(Call<List<ScheduleDataClass>> call, Throwable t) {
            }
        });
    }


}
