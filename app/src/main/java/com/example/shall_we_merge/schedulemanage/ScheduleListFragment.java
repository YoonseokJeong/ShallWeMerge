package com.example.shall_we_merge.schedulemanage;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shall_we_merge.R;
import com.example.shall_we_merge.Util;
import com.example.shall_we_merge.api.ScheduleDataClass;
import com.example.shall_we_merge.api.ShallWeMergeAPI;
import com.example.shall_we_merge.databinding.FragmentScheduleBinding;
import com.example.shall_we_merge.login.KakaoApplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScheduleListFragment extends Fragment {

    private FragmentScheduleBinding binding;
    private ScheduleListViewModel scheduleListViewModel;
    private String id = ((KakaoApplication)getActivity().getApplication()).getId();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false);

        ScheduleListViewModelFactory factory = new ScheduleListViewModelFactory(getActivity().getApplication());

        scheduleListViewModel = new ViewModelProvider(this, factory).get(ScheduleListViewModel.class);

        binding.setScheduleViewModel(scheduleListViewModel);

        binding.setLifecycleOwner(this);

        ScheduleAdapter adapter = new ScheduleAdapter(new ScheduleDiffUtil());

        binding.scheduleList.setAdapter(adapter);








        return binding.getRoot();
    }
}