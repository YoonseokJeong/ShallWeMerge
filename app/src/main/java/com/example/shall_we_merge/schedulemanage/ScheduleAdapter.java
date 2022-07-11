package com.example.shall_we_merge.schedulemanage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shall_we_merge.R;
import com.example.shall_we_merge.api.ScheduleDataClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleAdapter extends ListAdapter<ScheduleDataClass, ScheduleAdapter.ViewHolder> {

    public List<ScheduleDataClass> data = new ArrayList<ScheduleDataClass>(Arrays.asList(new ScheduleDataClass("1"), new ScheduleDataClass("2"), new ScheduleDataClass("3")));

    protected ScheduleAdapter(@NonNull DiffUtil.ItemCallback<ScheduleDataClass> diffCallback) {
        super(diffCallback);
    }

    @Override
    public int getItemCount(){
      return data.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ScheduleDataClass item = data.get(position);
        holder.dateText.setText(item.getDate());
        holder.nameText.setText(item.getName());
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dateText;
        private TextView nameText;

        public ViewHolder(View view){
            super(view);
            dateText = view.findViewById(R.id.dateText);
            nameText = view.findViewById(R.id.nameText);
        }


    }

}

