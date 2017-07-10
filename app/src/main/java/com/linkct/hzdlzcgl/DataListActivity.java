package com.linkct.hzdlzcgl;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.linkct.hzdlzcgl.adapter.DataListAdapter;
import com.linkct.hzdlzcgl.dao.MyDateDao;
import com.linkct.hzdlzcgl.domain.DataInfo;
import com.linkct.hzdlzcgl.fragment.DataListFragment;

import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

public class DataListActivity extends SupportActivity implements View.OnClickListener {

    private ImageView iv_back;
    private ListView lv_datalist;
    private String pid;
    private MyDateDao mDao;
    private List<DataInfo> list;
    private DataListAdapter adapter;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_data_list);
        pid = getIntent().getStringExtra("pid");
        loadRootFragment(R.id.fl_container, DataListFragment.newInstance());
        DataListFragment fragment = findFragment(DataListFragment.class);
        Bundle newBundle = new Bundle();
        fragment.putNewBundle(newBundle);
        start(fragment, SupportFragment.SINGLETASK);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
