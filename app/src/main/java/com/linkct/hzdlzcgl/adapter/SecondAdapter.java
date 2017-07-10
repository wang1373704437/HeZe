package com.linkct.hzdlzcgl.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.domain.WxjlInfo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wlh on 2017/7/3.
 */

public class SecondAdapter extends BaseAdapter {

    private final Context context;
    private List<WxjlInfo> list;
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

    public SecondAdapter(Context context, List<WxjlInfo> list) {
        this.context = context;
        this.list = list;
    }
    public void refreshData(List<WxjlInfo> list){
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
            view = View.inflate(context, R.layout.second_item, null);
            holder = new ViewHolder();
            holder.tv_wxjl1 = (TextView) view
                    .findViewById(R.id.tv_wxjl1);
            holder.tv_wxjl2 = (TextView) view
                    .findViewById(R.id.tv_wxjl2);
            holder.tv_wxjl3 = (TextView) view
                    .findViewById(R.id.tv_wxjl3);
            holder.tv_wxjl4 = (TextView) view
                    .findViewById(R.id.tv_wxjl_content);
            view.setTag(holder);
        }
        WxjlInfo data = list.get(position);

        Spanned temp = Html.fromHtml("<font color=#666666>"
                + "检修内容：" + "</font>");

        String context = data.getWxContent();
        if (TextUtils.isEmpty(key)) {
            holder.tv_wxjl1.setText(data.getWxTime() == null ? "" : data.getWxTime());
            holder.tv_wxjl2.setText(data.getWxPeople() == null ? "" : data.getWxPeople());
            holder.tv_wxjl3.setText(data.getWxSerial() == null ? "" : data.getWxSerial());
            holder.tv_wxjl4.setText(temp);
            holder.tv_wxjl4.append(context == null ? "" : context);
        } else {
            holder.tv_wxjl4.setText(temp);

            if (!TextUtils.isEmpty(data.getWxPeople()))
                holder.tv_wxjl2.setText(setKeyWordColor(data.getWxPeople(), key));
            else
                holder.tv_wxjl2.setText("");

            if (!TextUtils.isEmpty(data.getWxSerial()))
                holder.tv_wxjl3.setText(setKeyWordColor(data.getWxSerial(), key));
            else
                holder.tv_wxjl2.setText("");

            if (!TextUtils.isEmpty(context))
                holder.tv_wxjl4.append(setKeyWordColor(context, key));
            else
                holder.tv_wxjl2.append("");
        }

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
        TextView tv_wxjl1;
        TextView tv_wxjl2;
        TextView tv_wxjl3;
        TextView tv_wxjl4;
    }
}
