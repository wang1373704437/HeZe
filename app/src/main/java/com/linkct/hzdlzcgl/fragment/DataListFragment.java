package com.linkct.hzdlzcgl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linkct.hzdlzcgl.DataListActivity;
import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.dao.UserDao;
import com.linkct.hzdlzcgl.domain.DataInfo;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by wlh on 2017/7/6.
 */

public class DataListFragment extends SupportFragment {
    public static final String TAG = DataListFragment.class.getSimpleName();

    private Toolbar mToolbar;
    private String pid;
    private TextView tv_title;

    public static DataListFragment newInstance() {
        Bundle args = new Bundle();

        DataListFragment fragment = new DataListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        initView(view, savedInstanceState);
        return view;
    }

    private void initView(View view, Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            UserDao userDao = new UserDao(getActivity());
            pid = ((DataListActivity) getActivity()).getPid();
            ArrayList<DataInfo> listMenus = new ArrayList<>();
            List<DataInfo> listMenus1 = userDao.listByDataInfoIdByUUid(pid);
            List<DataInfo> listMenus2 = userDao.listByDataInfoIdbyPid(pid);

            listMenus.addAll(listMenus1);
            listMenus.addAll(listMenus2);

            MenuListFragment menuListFragment = MenuListFragment.newInstance(listMenus);
            OperationListFragment operationListFragment = OperationListFragment.newInstance(pid);
            loadRootFragment(R.id.fl_list_container, menuListFragment);
            loadRootFragment(R.id.fl_content_container, operationListFragment);
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        // ContentFragment是ShopFragment的栈顶子Fragment,会先调用ContentFragment的onBackPressedSupport方法
//        Toast.makeText(_mActivity, "onBackPressedSupport-->返回false,交给上层处理!", Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 替换加载 内容Fragment
     *
     * @param fragment
     */
    public void switchContentFragment(ContentFragment fragment) {
        SupportFragment contentFragment = findChildFragment(OperationListFragment.class);
        if (contentFragment != null) {
            contentFragment.replaceFragment(fragment, false);
        }
    }

    /**
     * 替换加载 内容Fragment
     *
     * @param fragment
     */
    public void switchOperaFragment(OperationListFragment fragment) {
        SupportFragment contentFragment = findChildFragment(ContentFragment.class);
        if (contentFragment != null) {
            contentFragment.replaceFragment(fragment, true);
        }
    }
}
