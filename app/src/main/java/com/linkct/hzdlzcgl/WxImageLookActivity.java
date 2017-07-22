package com.linkct.hzdlzcgl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.linkct.hzdlzcgl.domain.WxImageInfo;
import com.linkct.hzdlzcgl.fragment.WxImageDefaultFragment;

import java.util.List;


public class WxImageLookActivity extends FragmentActivity {

    private String imagePath;
    private PhotoView photoView;
    private Info info;
    int flagType;
    private SubsamplingScaleImageView big_imageView;
    private List<WxImageInfo> ImageList;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_image);
        ImageList = (List<WxImageInfo>) getIntent().getSerializableExtra("list");
        index=getIntent().getIntExtra("index",0);

        Fragment demoFragment = Fragment.instantiate(this, WxImageDefaultFragment.class.getName());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, demoFragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
        finish();

    }

    public List<WxImageInfo> getImageList() {
        return ImageList;
    }

    public void setImageList(List<WxImageInfo> imageList) {
        ImageList = imageList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
