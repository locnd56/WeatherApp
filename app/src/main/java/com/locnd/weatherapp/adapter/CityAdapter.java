package com.locnd.weatherapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.locnd.weatherapp.R;
import com.locnd.weatherapp.model.WeatherEntity;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

/**
 * Created by millionsun93 on 13/04/2015.
 */
public class CityAdapter extends UltimateViewAdapter {
    private List<WeatherEntity> list;

    public CityAdapter(List<WeatherEntity> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < getItemCount() && (customHeaderView != null ? position <= list.size() : position < list.size()) && (customHeaderView != null ? position > 0 : true)) {
            ((ViewHolder) holder).name.setText(list.get(customHeaderView != null ? position - 1 : position).getCity());
            ((ViewHolder) holder).status.setText(list.get(customHeaderView != null ? position - 1 : position).getCurrentDay().getCurDesc());
            ((ViewHolder) holder).imag.setImageResource(list.get(customHeaderView != null ? position - 1 : position).getCurrentDay().getImag());
            ((ViewHolder) holder).curTemp.setText(list.get(customHeaderView != null ? position - 1 : position).getCurrentDay().getCurTemp()+(char) 0x00B0);
            ((ViewHolder) holder).minTemp.setText(list.get(customHeaderView != null ? position - 1 : position).getCurrentDay().getMin());
            ((ViewHolder) holder).maxTemp.setText(list.get(customHeaderView != null ? position - 1 : position).getCurrentDay().getMax());
        }
    }

    @Override
    public int getAdapterItemCount() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city_manager, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void insert(WeatherEntity entity, int position) {
        insert(list, entity, position);
    }

    public void remove(int position) {
        remove(list, position);
    }

    public void clear() {
        clear(list);
    }

    @Override
    public void toggleSelection(int pos) {
        super.toggleSelection(pos);
    }

    @Override
    public void setSelected(int pos) {
        super.setSelected(pos);
    }

    @Override
    public void clearSelection(int pos) {
        super.clearSelection(pos);
    }

    public void swapPositions(int from, int to) {
        swapPositions(list, from, to);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView status;
        ImageView imag;
        TextView curTemp;
        TextView minTemp;
        TextView maxTemp;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            status = (TextView) itemView.findViewById(R.id.tv_staus);
            imag = (ImageView) itemView.findViewById(R.id.iv_status);
            curTemp = (TextView) itemView.findViewById(R.id.tv_temp);
            minTemp = (TextView) itemView.findViewById(R.id.tv_min_temp);
            maxTemp = (TextView) itemView.findViewById(R.id.tv_max_temp);
        }
    }
}
