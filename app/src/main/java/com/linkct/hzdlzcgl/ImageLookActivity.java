package com.linkct.hzdlzcgl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.linkct.hzdlzcgl.adapter.MyImageAdapter;
import com.linkct.hzdlzcgl.utils.AppToast;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;


public class ImageLookActivity extends Activity {

    private String imagePath;
    private PhotoView photoView;
    private Info info;
    int flagType;
    private SubsamplingScaleImageView big_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_image_look);
        String imagePath = getIntent().getStringExtra("imagePath");
        photoView = (PhotoView) findViewById(R.id.img);
        big_imageView = (SubsamplingScaleImageView)findViewById(R.id.big_imageView);
        Log.e("ImageLookActivity","大图图片路径："+imagePath);
        photoView.setMaxScale(16);
        File file = new File(imagePath);
        if (!file.exists()) {
            AppToast.showShortText(getApplicationContext(),"异常图片");
            finish();
        }
        if (file.exists() && file.length() > 1024 * 1024 * 5) {//大于5M
            flagType=1;
            big_imageView.setVisibility(View.VISIBLE);
            photoView.setVisibility(View.GONE);
            big_imageView.setMinimumDpi(40);
            big_imageView.setImage(ImageSource.uri(imagePath));

            big_imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    photoView.animaTo(info, new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            overridePendingTransition(0, 0);
                        }
                    });
                }
            });
        } else {
            big_imageView.setVisibility(View.GONE);
            photoView.setVisibility(View.VISIBLE);
            flagType=2;
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage("file://" + imagePath, photoView);
            // 启用图片缩放功能
            photoView.enable();
            info = MyImageAdapter.info;
            photoView.animaFrom(info);

            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    photoView.animaTo(info, new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            overridePendingTransition(0, 0);
                        }
                    });
                }
            });
        }



    }

    @Override
    public void onBackPressed() {
        switch (flagType){
            case 1://大图片
                finish();
                break;
            case 2://小图片
                photoView.animaTo(info, new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        overridePendingTransition(0, 0);

                    }
                });
                break;
        }


    }


}
