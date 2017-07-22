package com.linkct.hzdlzcgl.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.domain.WxImageInfo;
import com.linkct.hzdlzcgl.ui.ImageGridView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wlh on 2017/7/4.
 */

public class WxImagesListAdapter extends BaseAdapter {
    private final Context context;
    private List<WxImageInfo> list;
    private boolean scrollState;
    private String key = "";//搜索关键字

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setScrollState(boolean scrollState) {
        this.scrollState = scrollState;
    }

    public WxImagesListAdapter(Context context, List<WxImageInfo> list) {
        this.context = context;
        this.list = list;
    }

    public void refreshData(List<WxImageInfo> list) {
        this.list = null;
        this.list = list;
        notifyDataSetChanged();
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
        Log.e("SecondAdapter", "SecondAdapter" + position);
        View view;
        final ViewHolder holder;
        if (convertView != null) {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        } else {
            view = View.inflate(context, R.layout.wx_list_image_item
                    , null);
            holder = new ViewHolder();
            holder.tv_dzd1 = (TextView) view
                    .findViewById(R.id.tv_dzd1);
            holder.tv_dzd2 = (TextView) view
                    .findViewById(R.id.tv_dzd2);
            holder.gv_dzd3 = (ImageGridView) view
                    .findViewById(R.id.image_grid_view);
            view.setTag(holder);
        }
        WxImageInfo imageInfo=list.get(position);
        holder.tv_dzd1.setText(imageInfo.getDate());
        holder.tv_dzd2.setText(imageInfo.getPeopleName());
        holder.gv_dzd3.setAdapter(new GridViewAdapter(context, imageInfo.getImageList()));

        return view;
    }

    /**
     * 设置搜索关键字高亮
     *
     * @param content 原文本内容
     * @param keyword 关键字
     */
    private SpannableString setKeyWordColor(String content, String keyword) {
        SpannableString s = new SpannableString(content);
        Pattern p = Pattern.compile(keyword);
        Matcher m = p.matcher(s);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            s.setSpan(new ForegroundColorSpan(Color.BLUE), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }

    static class ViewHolder {
        TextView tv_dzd1;
        TextView tv_dzd2;
        ImageGridView gv_dzd3;
    }
}
