package com.linkct.hzdlzcgl.fragment.subfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.adapter.WxImagesListAdapter;
import com.linkct.hzdlzcgl.dao.UserDao;
import com.linkct.hzdlzcgl.domain.WxImageInfo;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by YoKeyword on 16/2/7.
 */
public class OptItemWXImageFragment extends SupportFragment {
    private static final String ARG_NUMBER = "arg_number";

    private Toolbar mToolbar;
    private TextView mTvName;
    private Button mBtnNext, mBtnNextWithFinish;

    private String mNumber;
    private ImageView iv_back;
    private TextView tv_supImage_content;
    private UserDao userdao;
    private WxImagesListAdapter adapter;
    private ListView lv_images;

    public static OptItemWXImageFragment newInstance(String number) {
        OptItemWXImageFragment fragment = new OptItemWXImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mNumber = args.getString(ARG_NUMBER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_wximage, container, false);
        userdao=new UserDao(getActivity());
        initView(view);
        return view;
    }

    private void initView(View view) {
        iv_back=(ImageView)view.findViewById(R.id.iv_back);
        tv_supImage_content=(TextView)view.findViewById(R.id.tv_supImage_content);
        lv_images=(ListView)view.findViewById(R.id.lv_images);
        List<WxImageInfo> imagesList=userdao.listWXImagesjlByUuid(mNumber);

        for(WxImageInfo mImagejl:imagesList){
            List<WxImageInfo> iamge=userdao.listWXImagesjlByid(mImagejl.getWximageid());
            if(iamge!=null)
                mImagejl.setImageList(iamge);
            else
                mImagejl.setImageList(new ArrayList<WxImageInfo>());

        }
        if(imagesList==null)
            imagesList=new ArrayList<>();

        adapter=new WxImagesListAdapter(getActivity(),imagesList);
        lv_images.setAdapter(adapter);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }
}
