package com.linkct.hzdlzcgl.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.domain.WxjlInfo;

import java.util.List;

/**
 * Created by wlh on 2017/7/4.
 */

public class FifthApapter extends BaseAdapter {
    private final Context context;
    private final List<WxjlInfo> list;
    private boolean scrollState;

    public FifthApapter(Context context, List<WxjlInfo> list) {
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
        Log.e("FourthlyAdapter", "FourthlyAdapter" + position);
        View view;
        final ViewHolder holder;
        if (convertView != null) {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        } else {
            view = View.inflate(context, R.layout.fifth_item, null);
            holder = new ViewHolder();
            holder.tv_wx_people = (TextView) view
                    .findViewById(R.id.tv_wx_people);
            holder.tv_wx_time = (TextView) view
                    .findViewById(R.id.tv_wx_time);
            holder.tv_wx_content = (TextView) view
                    .findViewById(R.id.tv_wx_content);
//            holder.gl_images= (NoScrollGridview) view
//                    .findViewById(R.id.gridview);

            view.setTag(holder);
        }
        WxjlInfo data = list.get(position);
        holder.tv_wx_people.setText("" + data.getWxPeople());
        holder.tv_wx_time.setText("" + data.getWxTime());
        holder.tv_wx_content.setText("" + data.getWxContent());

        return view;
    }

    static class ViewHolder {
        TextView tv_wx_people;
        TextView tv_wx_time;
        TextView tv_wx_content;
//    RecyclerView rcl_images;

    }
}
