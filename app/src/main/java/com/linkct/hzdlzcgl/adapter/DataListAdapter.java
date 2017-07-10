package com.linkct.hzdlzcgl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.domain.DataInfo;

import java.util.List;

/**
 * Created by wlh on 2017/6/30.
 */

public class DataListAdapter extends BaseAdapter {

    private final Context context;
    private final List<DataInfo> list;

    public DataListAdapter(Context context, List<DataInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        final ViewHolder holder;
        if (convertView != null) {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        } else {
            view = View.inflate(context, R.layout.data_item_view, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) view
                    .findViewById(R.id.tv_name);
            view.setTag(holder);
        }
        DataInfo data = list.get(position);
        holder.tv_name.setText("" + data.getEquipment_name());
        return view;
    }

    static class ViewHolder {
        TextView tv_name;
    }
}
