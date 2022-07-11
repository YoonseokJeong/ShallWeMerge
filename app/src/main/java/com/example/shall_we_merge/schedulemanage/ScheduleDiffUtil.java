package com.example.shall_we_merge.schedulemanage;

import androidx.recyclerview.widget.DiffUtil;

import com.example.shall_we_merge.api.ScheduleDataClass;

import java.util.List;

public class ScheduleDiffUtil extends DiffUtil.ItemCallback<ScheduleDataClass> {


    @Override
    public boolean areItemsTheSame(ScheduleDataClass mOldSchedule, ScheduleDataClass mNewSchedule) {
        return mOldSchedule.getName().equals(mNewSchedule.getName());
    }

    @Override
    public boolean areContentsTheSame(ScheduleDataClass mOldSchedule, ScheduleDataClass mNewSchedule) {

        return mOldSchedule.getName().equals(mNewSchedule.getName()) && mOldSchedule.getDate().equals(mNewSchedule.getDate())
                && mOldSchedule.getPlaces().equals(mNewSchedule.getPlaces());
    }
}
