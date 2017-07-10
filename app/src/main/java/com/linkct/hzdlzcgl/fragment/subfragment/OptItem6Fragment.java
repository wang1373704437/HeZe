package com.linkct.hzdlzcgl.fragment.subfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
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
public class OptItem6Fragment extends SupportFragment {
    private static final String ARG_NUMBER = "arg_number";

    private Toolbar mToolbar;
    private TextView mTvName;
    private Button mBtnNext, mBtnNextWithFinish;

    private String mNumber;
    private TextView tv_note;
    private ImageView iv_back;
    private List<DataInfo> list;

    public static OptItem6Fragment newInstance(String number) {
        OptItem6Fragment fragment = new OptItem6Fragment();
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
        View view = inflater.inflate(R.layout.fragment_item6, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_note = (TextView) view.findViewById(R.id.tv_note);
        iv_back = (ImageView) view.findViewById(R.id.iv_back);

        UserDao userDao = new UserDao(_mActivity);
        list = userDao.listByDataInfoIdByUUid(mNumber);
        if (list != null && list.size() > 0) {
            String note = list.get(0).getNote();
            Spanned temp = Html.fromHtml("<font color=#666666>"
                    + "备忘：" + "</font>");
            if (TextUtils.isEmpty(note)) {
                tv_note.setText(temp);
            }else{
                tv_note.setText(temp);
                tv_note.append(note);
            }
        }
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }
}
