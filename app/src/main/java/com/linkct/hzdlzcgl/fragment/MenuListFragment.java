package com.linkct.hzdlzcgl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.adapter.MenuAdapter;
import com.linkct.hzdlzcgl.dao.UserDao;
import com.linkct.hzdlzcgl.domain.DataInfo;
import com.linkct.hzdlzcgl.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by wlh on 2017/7/5.
 */

public class MenuListFragment extends SupportFragment {
    private static final String ARG_MENUS = "arg_menus";
    private static final String SAVE_STATE_POSITION = "save_state_position";

    private RecyclerView mRecy;
    private MenuAdapter mAdapter;

    private ArrayList<DataInfo> mMenus;
    private int mCurrentPosition = -1;
    private TextView tv_title;
    private ImageView iv_back;
    private DataInfo mFirstNode;
    private List<DataInfo> parentList;

    public static MenuListFragment newInstance(ArrayList<DataInfo> menus) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_MENUS, menus);

        MenuListFragment fragment = new MenuListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mMenus = (ArrayList<DataInfo>) args.getSerializable(ARG_MENUS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_menu, container, false);
        initView(view);
        return view;
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }

    private void initView(View view) {
        mFirstNode=mMenus.get(0);
        UserDao userDao=new UserDao(_mActivity);
        parentList=userDao.listByDataInfoIdByUUid(mFirstNode.getPid());
                mRecy = (RecyclerView) view.findViewById(R.id.recy);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        if (parentList != null && parentList.size() > 0) {
            tv_title.setText("" + parentList.get(0).getEquipment_name()==null?"":parentList.get(0).getEquipment_name());
        }else{
            tv_title.setText("");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mAdapter = new MenuAdapter(_mActivity);
        mRecy.setAdapter(mAdapter);
        mAdapter.setDatas(mMenus);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                showContent(position);
            }
        });

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(SAVE_STATE_POSITION);
            mAdapter.setItemChecked(mCurrentPosition);
        } else {
            mCurrentPosition = 0;
            mAdapter.setItemChecked(0);
        }
    }

    private void showContent(int position) {
        if (position == mCurrentPosition) {
            return;
        }

        mCurrentPosition = position;

        mAdapter.setItemChecked(position);
        if (position == 0) {
            OperationListFragment operfragment = OperationListFragment.newInstance(mMenus.get(position).getUuid());
            ((DataListFragment) getParentFragment()).switchOperaFragment(operfragment);
        } else {
            ContentFragment fragment = ContentFragment.newInstance(mMenus.get(position).getUuid());
            ((DataListFragment) getParentFragment()).switchContentFragment(fragment);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_STATE_POSITION, mCurrentPosition);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecy.setAdapter(null);
    }
}