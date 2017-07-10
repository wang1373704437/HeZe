package com.linkct.hzdlzcgl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.utils.FileUtils;
import com.linkct.hzdlzcgl.utils.ViewUtils;

import java.io.File;
import java.util.List;

/**
 * Created by wlh on 2017/7/3.
 */

public class GridViewAdapter extends BaseAdapter {

    private final Context context;
    private final List<String> mList;

    public GridViewAdapter(Context context, List<String> list){
        this.context=context;
        this.mList=list;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
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
            view = View.inflate(context, R.layout.recycler_image_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view
                    .findViewById(R.id.iv_iamge_type);
            view.setTag(holder);
        }

        File file = new File(FileUtils.IMAGE_PATH + mList.get(position));
        Glide.with(context).load(file).placeholder(R.drawable.image_group_default)
                .override(ViewUtils.dp2px(120, context),
                        ViewUtils.dp2px(90, context))
//                .transform(new GlideRoundTransform(context,20))
                .thumbnail(0.1f).into(holder.imageView);
        return view;
    }


   static class ViewHolder {

       ImageView imageView;
   }
}
