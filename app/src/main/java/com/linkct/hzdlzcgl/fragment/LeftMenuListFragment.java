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
import com.linkct.hzdlzcgl.adapter.Constant;
import com.linkct.hzdlzcgl.adapter.LeftMenuAdapter;
import com.linkct.hzdlzcgl.adapter.MenuAdapter;
import com.linkct.hzdlzcgl.app.APP;
import com.linkct.hzdlzcgl.dao.UserDao;
import com.linkct.hzdlzcgl.domain.DataInfo;
import com.linkct.hzdlzcgl.listener.OnItemClickListener;
import com.linkct.hzdlzcgl.utils.AppToast;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by wlh on 2017/7/5.
 */

public class LeftMenuListFragment extends SupportFragment {
    private static final String ARG_MENUS = "arg_menus";
    private static final String ARG_UUID = "arg_uuid";
    private static final String SAVE_STATE_POSITION = "save_state_position";

    private RecyclerView mRecy;
    private LeftMenuAdapter mAdapter;

    private String mMenus;
    private int mCurrentPosition = -1;
    private TextView tv_title;
    private ImageView iv_back;
    private DataInfo mFirstNode;
    private List<DataInfo> parentList;
    private UserDao userDao;
    private String currentUUId;

    public static LeftMenuListFragment newInstance(String menus,String uuid) {

        Bundle args = new Bundle();
        args.putString(ARG_MENUS, menus);
        args.putString(ARG_UUID, menus);
        LeftMenuListFragment fragment = new LeftMenuListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mMenus = args.getString(ARG_MENUS);
            currentUUId = args.getString(ARG_UUID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_left, container, false);
        initView(view);
        return view;
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }

    private void initView(View view) {
        userDao=new UserDao(_mActivity);



        mRecy = (RecyclerView) view.findViewById(R.id.recy);
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }

        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecy.setLayoutManager(manager);
        mAdapter = new LeftMenuAdapter(_mActivity);
        mRecy.setAdapter(mAdapter);

        parentList=userDao.listByDataInfoIdbyPid("0");

        for(DataInfo node:parentList){
            List<DataInfo> tempList = userDao.listByDataInfoIdbyPid(node.getUuid());
            node.setType(Constant.GROUP);
            if(tempList!=null&&tempList.size()>0){
                node.addChildren(tempList);
            }
        }
        mAdapter.setDatas(parentList);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                showContent(position);
            }
        });

        String currentUUid = mMenus;
        int count=0;
        for(DataInfo node:parentList) {
            if(node.getUuid().equals(currentUUid)){
                break;
            }
            count++;
        }
        if(parentList!=null&&count<parentList.size()) {
            mAdapter.setItemChecked(count);
            mAdapter.controllerItem(parentList.get(count));
        }

    }

    private void showContent(int position) {


        if (mAdapter.getAlls().get(position).getType()==Constant.GROUP){
            mAdapter.controllerItem(mAdapter.getAlls().get(position));
        }else{
            String uuid=mAdapter.getAlls().get(position).getUuid();
            ArrayList<DataInfo> listMenus = new ArrayList<>();
            List<DataInfo> listMenus1 = userDao.listByDataInfoIdByUUid(mAdapter.getAlls().get(position).getUuid());
            List<DataInfo> listMenus2 = userDao.listByDataInfoIdbyPid(mAdapter.getAlls().get(position).getUuid());

            listMenus.addAll(listMenus1);
            listMenus.addAll(listMenus2);
            MenuListFragment fragment = MenuListFragment.newInstance(listMenus);
            ((DataListFragment) getParentFragment()).switchMenuFragment(fragment);

            ((DataListFragment) getParentFragment()).switchOperaFragmentNew(uuid);
        }

        mCurrentPosition = position;

        mAdapter.setItemChecked(position);
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