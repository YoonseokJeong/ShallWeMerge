package com.example.shall_we_merge.schedulemanage;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shall_we_merge.R;
import com.example.shall_we_merge.databinding.FragmentScheduleListBinding;
import com.example.shall_we_merge.login.KakaoApplication;


public class ScheduleListFragment extends Fragment {

    private FragmentScheduleListBinding binding;
    private ScheduleListViewModel scheduleListViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);

        Log.d("전역변수", ((KakaoApplication)getActivity().getApplication()).getId());

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule_list, container, false);

        ScheduleListViewModelFactory factory = new ScheduleListViewModelFactory(getActivity().getApplication());

        scheduleListViewModel = new ViewModelProvider(this, factory).get(ScheduleListViewModel.class);

        binding.setScheduleViewModel(scheduleListViewModel);

        binding.setLifecycleOwner(this);

        ScheduleAdapter adapter = new ScheduleAdapter(new ScheduleDiffUtil());

        binding.scheduleList.setAdapter(adapter);








        return binding.getRoot();
    }
}