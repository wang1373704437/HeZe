package com.linkct.hzdlzcgl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.adapter.SamplePagerAdapter;
import com.linkct.hzdlzcgl.domain.ImageInfo;

import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ImageDefaultFragment extends Fragment {

    private List<ImageInfo> ImageList;
    private int index;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_image_look_new, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ViewPager viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.ci_indicator);

        ImageList = (List<ImageInfo>) getActivity().getIntent().getSerializableExtra("list");
        index= getActivity().getIntent().getIntExtra("index",0);

        viewpager.setAdapter(new SamplePagerAdapter(getActivity(),ImageList));
        indicator.setViewPager(viewpager);
        viewpager.setCurrentItem(index);
    }
}
