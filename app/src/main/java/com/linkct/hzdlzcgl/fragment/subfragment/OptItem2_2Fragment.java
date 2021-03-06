package com.linkct.hzdlzcgl.fragment.subfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.adapter.MyImageNewAdapter;
import com.linkct.hzdlzcgl.dao.UserDao;
import com.linkct.hzdlzcgl.domain.WxImageInfo;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by YoKeyword on 16/2/7.
 */
public class OptItem2_2Fragment extends SupportFragment {
    private static final String ARG_NUMBER = "arg_number";

    private Toolbar mToolbar;
    private TextView mTvName;
    private Button mBtnNext, mBtnNextWithFinish;

    private String mNumber;
    private ImageView iv_back;
    private TextView tv_supImage_content;
    private RecyclerView rcl_images;
    private UserDao userdao;
    private MyImageNewAdapter adapter;

    public static OptItem2_2Fragment newInstance(String number) {
        OptItem2_2Fragment fragment = new OptItem2_2Fragment();
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
        View view = inflater.inflate(R.layout.fragment_item2_2, container, false);
        userdao=new UserDao(getActivity());
        initView(view);
        return view;
    }

    private void initView(View view) {
        iv_back=(ImageView)view.findViewById(R.id.iv_back);
        tv_supImage_content=(TextView)view.findViewById(R.id.tv_supImage_content);
        rcl_images=(RecyclerView)view.findViewById(R.id.rcl_images);
        GridLayoutManager mgr = new GridLayoutManager(getActivity(), 4);
        rcl_images.setLayoutManager(mgr);

        List<WxImageInfo> imageslist=userdao.listWXImagesByUuid(mNumber);
        if(imageslist==null)
            imageslist=new ArrayList<>();
        adapter=new MyImageNewAdapter(getActivity(),imageslist);
        rcl_images.setAdapter(adapter);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }
}
