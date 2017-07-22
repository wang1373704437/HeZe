package com.linkct.hzdlzcgl.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.WxImageLookActivity;
import com.linkct.hzdlzcgl.domain.WxImageInfo;
import com.linkct.hzdlzcgl.utils.AppToast;
import com.linkct.hzdlzcgl.utils.FileUtils;
import com.linkct.hzdlzcgl.utils.ViewUtils;

import java.io.File;
import java.io.Serializable;
import java.util.List;


/**
 * Created by wlh on 2017/7/3.
 */

public class GridViewAdapter extends BaseAdapter {

    private final Context context;
    private final List<WxImageInfo> mList;

    public GridViewAdapter(Context context, List<WxImageInfo> list) {
        this.context = context;
        this.mList = list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

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

        final String imagePath = FileUtils.IMAGE_PATH + mList.get(position).getPath();
        File file = new File(imagePath);
        Log.e("MyImageAdapter", "文件大小：" + (file.exists() ? file.length() : ""));
        if (file.exists() && file.length() > 1024 * 1024 * 10) {//大于10M
            Glide.with(context).load(file).placeholder(R.drawable.image_group_default)
                    .override(ViewUtils.dp2px(120, context),
                            ViewUtils.dp2px(90, context))
//                .transform(new GlideRoundTransform(context,20))
                    .thumbnail(0.001f).into(holder.imageView);
        } else if (file.exists()) {
            Glide.with(context).load(file).placeholder(R.drawable.image_group_default)
                    .override(ViewUtils.dp2px(120, context),
                            ViewUtils.dp2px(90, context))
//                .transform(new GlideRoundTransform(context,20))
                    .thumbnail(0.1f).into(holder.imageView);
        } else {
            Glide.with(context).load(R.drawable.image_group_default)
                    .override(ViewUtils.dp2px(120, context),
                            ViewUtils.dp2px(90, context))
                    .thumbnail(0.1f).into(holder.imageView);
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(imagePath)) {
                    AppToast.showShortText(context, "未能识别的图片");
                } else {
                    Intent intent = new Intent(context, WxImageLookActivity.class);
                    MyImageNewAdapter.info = PhotoView.getImageViewInfo(holder.imageView);
                    intent.putExtra("index", position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("list", (Serializable) mList);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    ((Activity) context)
                            .overridePendingTransition(0, 0);
                }
            }
        });
        return view;
    }


    static class ViewHolder {

        ImageView imageView;
    }
}
