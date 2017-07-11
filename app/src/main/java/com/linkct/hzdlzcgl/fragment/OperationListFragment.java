package com.linkct.hzdlzcgl.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.fragment.subfragment.OptItem1Fragment;
import com.linkct.hzdlzcgl.fragment.subfragment.OptItem2Fragment;
import com.linkct.hzdlzcgl.fragment.subfragment.OptItem3Fragment;
import com.linkct.hzdlzcgl.fragment.subfragment.OptItem4Fragment;
import com.linkct.hzdlzcgl.fragment.subfragment.OptItem5Fragment;
import com.linkct.hzdlzcgl.fragment.subfragment.OptItem6Fragment;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by wlh on 2017/7/6.
 */
public class OperationListFragment extends SupportFragment implements View.OnClickListener {
    private static final String ARG_MENU = "arg_menu";

    private String uuid;
    private Button bn_item6;
    private Button bn_item1;
    private Button bn_item2;
    private Button bn_item3;
    private Button bn_item4;
    private Button bn_item5;

    public static OperationListFragment newInstance(String menu) {

        Bundle args = new Bundle();
        args.putString(ARG_MENU, menu);

        OperationListFragment fragment = new OperationListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            uuid = args.getString(ARG_MENU);
        }
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        bn_item1 = (Button) view.findViewById(R.id.bn_item1);
        bn_item2 = (Button) view.findViewById(R.id.bn_item2);
        bn_item3 = (Button) view.findViewById(R.id.bn_item3);
        bn_item4 = (Button) view.findViewById(R.id.bn_item4);
        bn_item5 = (Button) view.findViewById(R.id.bn_item5);
        bn_item6 = (Button) view.findViewById(R.id.bn_item6);

        bn_item1.setOnClickListener(this);
        bn_item2.setOnClickListener(this);
        bn_item3.setOnClickListener(this);
        bn_item4.setOnClickListener(this);
        bn_item5.setOnClickListener(this);
        bn_item6.setOnClickListener(this);
    }

    @Override
    public boolean onBackPressedSupport() {
        // ContentFragment是ShopFragment的栈顶子Fragment,可以在此处理返回按键事件
        return super.onBackPressedSupport();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bn_item1:
                if (getParentFragment() instanceof DataListFragment) {
                    ((DataListFragment) getParentFragment()).start(OptItem1Fragment.newInstance(uuid));
                }
                break;
            case R.id.bn_item2:
                if (getParentFragment() instanceof DataListFragment) {
                    ((DataListFragment) getParentFragment()).start(OptItem2Fragment.newInstance(uuid));
                }
                break;
            case R.id.bn_item3:
                if (getParentFragment() instanceof DataListFragment) {
                    ((DataListFragment) getParentFragment()).start(OptItem3Fragment.newInstance(uuid));
                }
                break;
            case R.id.bn_item4:
                if (getParentFragment() instanceof DataListFragment) {
                    ((DataListFragment) getParentFragment()).start(OptItem4Fragment.newInstance(uuid));
                }
                break;
            case R.id.bn_item5:
                if (getParentFragment() instanceof DataListFragment) {
                    ((DataListFragment) getParentFragment()).start(OptItem5Fragment.newInstance(uuid));
                }
                break;
            case R.id.bn_item6:
                if (getParentFragment() instanceof DataListFragment) {
                    ((DataListFragment) getParentFragment()).start(OptItem6Fragment.newInstance(uuid));
                }
                break;
        }
    }
}
