package com.linkct.hzdlzcgl.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bm.library.PhotoView;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.domain.WxImageInfo;
import com.linkct.hzdlzcgl.utils.FileUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.List;
import java.util.Random;


public class SamplePagerNewAdapter extends PagerAdapter {

    private final Random random = new Random();
    private final List<WxImageInfo> list;
    private final Context context;

    public SamplePagerNewAdapter(Context context, List<WxImageInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View image = View.inflate(context, R.layout.activity_image_look, null);
        String imagePath = FileUtils.IMAGE_PATH + list.get(position).getPath();
        PhotoView photoView = (PhotoView) image.findViewById(R.id.img);
        SubsamplingScaleImageView big_imageView = (SubsamplingScaleImageView) image.findViewById(R.id.big_imageView);
        photoView.setMaxScale(16);
        File file = new File(imagePath);
        int flagType = 0;
        if (file.exists() && file.length() > 1024 * 1024 * 5) {//大于5M
            flagType = 1;
            big_imageView.setVisibility(View.VISIBLE);
            photoView.setVisibility(View.GONE);
            big_imageView.setMinimumDpi(40);
            big_imageView.setImage(ImageSource.uri(imagePath));

            big_imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) context).finish();
                    ((Activity) context).overridePendingTransition(0, 0);
                }
            });
        } else if(file.exists()){
            big_imageView.setVisibility(View.GONE);
            photoView.setVisibility(View.VISIBLE);
            flagType = 2;
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage("file://" + imagePath, photoView);
            // 启用图片缩放功能
            photoView.enable();

            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) context).finish();
                    ((Activity) context).overridePendingTransition(0, 0);
                }
            });
        }else{
            big_imageView.setVisibility(View.GONE);
            photoView.setVisibility(View.VISIBLE);
            flagType = 2;
            // 启用图片缩放功能
            photoView.setImageResource(R.drawable.image_group_default);

            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) context).finish();
                    ((Activity) context).overridePendingTransition(0, 0);
                }
            });
        }

        view.addView(image);
        return image;
    }
}