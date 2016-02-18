package com.locnd.weatherapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.locnd.weatherapp.R;
import com.locnd.weatherapp.model.WeatherDailyEntity;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

/**
 * Created by Oracle on 12/04/2015.
 */
public class DailyWeatherAdapter extends UltimateViewAdapter {
    private List<WeatherDailyEntity> stringList;
    public DailyWeatherAdapter(List<WeatherDailyEntity> stringList) {
        this.stringList = stringList;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (position < getItemCount() && (customHeaderView != null ? position <= stringList.size() : position < stringList.size()) && (customHeaderView != null ? position > 0 : true)) {
            ((ViewHolder) holder).date.setText(stringList.get(customHeaderView != null ? position - 1 : position).getDate());
            ((ViewHolder) holder).imag.setImageResource(stringList.get(customHeaderView != null ? position - 1 : position).getImag());
            ((ViewHolder) holder).minTemp.setText(stringList.get(customHeaderView != null ? position - 1 : position).getMin()+(char) 0x00B0+"   -");
            ((ViewHolder) holder).maxTemp.setText(stringList.get(customHeaderView != null ? position - 1 : position).getMax()+(char) 0x00B0);
            ((ViewHolder) holder).parent.setBackgroundColor(stringList.get(customHeaderView != null ? position - 1 : position).getbackgroundColor());
            ((ViewHolder) holder).parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(((ViewHolder) holder).parent.getContext(),stringList.get(position).getDesc(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getAdapterItemCount() {
        return stringList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_horizontal_listview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
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
        swapPositions(stringList, from, to);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        ImageView imag;
        TextView minTemp;
        TextView maxTemp;
        TextView desc;
        View parent;
        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            imag = (ImageView) itemView.findViewById(R.id.imag);
            minTemp = (TextView) itemView.findViewById(R.id.minTemp);
            maxTemp = (TextView) itemView.findViewById(R.id.maxTemp);
            parent = itemView;
        }
    }
}
