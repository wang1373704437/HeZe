package com.linkct.hzdlzcgl;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.adapter.MyImageAdapter;
import com.linkct.hzdlzcgl.domain.ImageInfo;
import com.linkct.hzdlzcgl.fragment.ImageDefaultFragment;
import com.linkct.hzdlzcgl.utils.AppToast;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.List;


public class ImageLookNewActivity extends FragmentActivity {

    private String imagePath;
    private PhotoView photoView;
    private Info info;
    int flagType;
    private SubsamplingScaleImageView big_imageView;
    private List<ImageInfo> ImageList;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_image);
        ImageList = (List<ImageInfo>) getIntent().getSerializableExtra("list");
        index=getIntent().getIntExtra("index",0);

        Fragment demoFragment = Fragment.instantiate(this, ImageDefaultFragment.class.getName());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, demoFragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
        finish();

    }

    public List<ImageInfo> getImageList() {
        return ImageList;
    }

    public void setImageList(List<ImageInfo> imageList) {
        ImageList = imageList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
