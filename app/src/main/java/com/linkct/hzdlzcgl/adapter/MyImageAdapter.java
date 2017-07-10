package com.linkct.hzdlzcgl.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.linkct.hzdlzcgl.ImageLookActivity;
import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.domain.ImageInfo;
import com.linkct.hzdlzcgl.utils.AppToast;
import com.linkct.hzdlzcgl.utils.FileUtils;
import com.linkct.hzdlzcgl.utils.ViewUtils;

import java.io.File;
import java.util.List;

/**
 * Created by wlh on 2017/7/1.
 */

public class MyImageAdapter extends RecyclerView.Adapter<MyImageAdapter.ViewHolder> {

    private List<ImageInfo> data;
    private Context context;
    public static Info info;

    public MyImageAdapter(Context context, List<ImageInfo> list) {
        this.context = context;
        this.data = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_image_item, null);
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewUtils.dp2px(130,context),ViewUtils.dp2px(100,context));
//        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    //将数据绑定到子View，会自动复用View
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final String imagePath=FileUtils.IMAGE_PATH + data.get(i).getPath();
        File file = new File(imagePath);
        Log.e("MyImageAdapter","文件大小："+(file.exists()?file.length():""));
        if(file.exists()&&file.length()>1024*1024*10){//大于10M
            Glide.with(context).load(file).placeholder(R.drawable.image_group_default)
                    .override(ViewUtils.dp2px(120, context),
                            ViewUtils.dp2px(90, context))
//                .transform(new GlideRoundTransform(context,20))
                    .thumbnail(0.001f).into(viewHolder.imageView);
        }else {
            Glide.with(context).load(file).placeholder(R.drawable.image_group_default)
                    .override(ViewUtils.dp2px(120, context),
                            ViewUtils.dp2px(90, context))
//                .transform(new GlideRoundTransform(context,20))
                    .thumbnail(0.1f).into(viewHolder.imageView);
        }
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(imagePath)){
                    AppToast.showShortText(context,"未能识别的图片");
                }else{
                    Intent intent=new Intent(context, ImageLookActivity.class);
                     info = PhotoView.getImageViewInfo(viewHolder.imageView);
                    intent.putExtra("imagePath",imagePath);
                    context.startActivity(intent);
                    ((Activity) context)
                            .overridePendingTransition(0, 0);
                }
            }
        });
    }

    //RecyclerView显示数据条数
    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        //在布局中找到所含有的UI组件
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_iamge_type);
        }
    }
}
