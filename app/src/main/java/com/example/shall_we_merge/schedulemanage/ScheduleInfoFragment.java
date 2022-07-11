package com.example.shall_we_merge.schedulemanage;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shall_we_merge.R;
import com.example.shall_we_merge.databinding.FragmentScheduleInfoBinding;
import com.example.shall_we_merge.databinding.FragmentScheduleListBinding;

public class ScheduleInfoFragment extends Fragment {

    private FragmentScheduleInfoBinding binding;
    private ScheduleInfoFragment getFrag(){
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule_info, container, false);

        return binding.getRoot();
    }
}