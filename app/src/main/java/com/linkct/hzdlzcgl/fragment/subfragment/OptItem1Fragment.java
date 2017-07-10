package com.linkct.hzdlzcgl.fragment.subfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkct.hzdlzcgl.R;
import com.linkct.hzdlzcgl.dao.UserDao;
import com.linkct.hzdlzcgl.domain.DataInfo;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * Created by YoKeyword on 16/2/7.
 */
public class OptItem1Fragment extends SupportFragment implements View.OnClickListener{
    private static final String ARG_NUMBER = "arg_number";

    private Toolbar mToolbar;
    private TextView mTvName;
    private Button mBtnNext, mBtnNextWithFinish;
    private ImageView iv_back;
    private String mNumber;
    private TextView tv_sp1;
    private TextView tv_sp2;
    private TextView tv_sp6;
    private TextView tv_sp3;
    private TextView tv_sp4;
    private TextView tv_sp5;
    private UserDao userDao;

    public static OptItem1Fragment newInstance(String number) {
        OptItem1Fragment fragment = new OptItem1Fragment();
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
        View view = inflater.inflate(R.layout.fragment_item1, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        iv_back=(ImageView)view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);


        tv_sp1 = (TextView) view.findViewById(R.id.tv_sp1);
        tv_sp2 = (TextView) view.findViewById(R.id.tv_sp2);
        tv_sp3 = (TextView) view.findViewById(R.id.tv_sp3);
        tv_sp4 = (TextView) view.findViewById(R.id.tv_sp4);
        tv_sp5 = (TextView) view.findViewById(R.id.tv_sp5);
        tv_sp6 = (TextView) view.findViewById(R.id.tv_sp6);
        userDao = new UserDao(getActivity());
        List<DataInfo> list = userDao.listByDataInfoIdByUUid(mNumber);
        if (list != null && list.size() > 0) {
            DataInfo data=list.get(0);
            tv_sp1.setText(""+data.getEquipment_model()==null?"":data.getEquipment_model());
            tv_sp2.setText(""+data.getProductiontime()==null?"":data.getProductiontime());
            tv_sp3.setText(""+data.getOpttime()==null?"":data.getOpttime());
            tv_sp4.setText(""+data.getSuppliermf()==null?"":data.getSuppliermf());
            tv_sp5.setText(""+data.getSuppliercontact()==null?"":data.getSuppliercontact());
            tv_sp6.setText(""+data.getSupplierphone()==null?"":data.getSupplierphone());
        }else{
            tv_sp1.setText("");
            tv_sp2.setText("");
            tv_sp3.setText("");
            tv_sp4.setText("");
            tv_sp5.setText("");
            tv_sp6.setText("");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                pop();
                break;
        }
    }
}
