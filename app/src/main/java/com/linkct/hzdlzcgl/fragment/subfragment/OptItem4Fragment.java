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
import android.widget.SearchView;
import android.widget.TextView;

import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.adapter.FourthlyAdapter;
import com.linkct.hzdlzcgl.dao.UserDao;
import com.linkct.hzdlzcgl.domain.DzdInfo;
import com.linkct.hzdlzcgl.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by YoKeyword on 16/2/7.
 */
public class OptItem4Fragment extends SupportFragment {
    private static final String ARG_NUMBER = "arg_number";

    private Toolbar mToolbar;
    private TextView mTvName;
    private Button mBtnNext, mBtnNextWithFinish;
    private ListView lv_second;
    private ImageView iv_back;
    private String mNumber;
    private SearchView searchView;
    private FourthlyAdapter adapter;
    private List<DzdInfo> dzdList;

    public static OptItem4Fragment newInstance(String number) {
        OptItem4Fragment fragment = new OptItem4Fragment();
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
        View view = inflater.inflate(R.layout.fragment_item4, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        searchView = (SearchView) view.findViewById(R.id.sv_search);
        lv_second = (ListView) view.findViewById(R.id.lv_second);

        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTextSize(13);//字体、提示字体大小


        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewUtils.hideSoftInputView(getActivity());
                pop();
            }
        });

        UserDao userDao = new UserDao(getActivity());
        dzdList = userDao.listDzdByUuid(mNumber);
        if (dzdList == null)
            dzdList = new ArrayList<>();

        adapter = new FourthlyAdapter(getActivity(), dzdList);
        lv_second.setAdapter(adapter);
        TextView emptyView= (TextView) view.findViewById(R.id.tv_listview_empty);
        lv_second.setEmptyView(emptyView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                adapter.setKey(newText);
//                if(TextUtils.isEmpty(newText)){
//                    adapter.refreshData(dzdList);
//                }else{
//                    List<DzdInfo> tempList=new ArrayList<>();
//                    for(DzdInfo dzd:dzdList){
//                        if(dzd.search(newText)){
//                            tempList.add(dzd);
//                        }
//                    }
//                    adapter.refreshData(tempList);
//                }
                return false;
            }
        });
    }
}
