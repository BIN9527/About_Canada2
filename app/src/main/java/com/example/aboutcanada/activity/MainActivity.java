package com.example.aboutcanada.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.example.aboutcanada.R;
import com.example.aboutcanada.adapter.XrecyclerviewAdapter;
import com.example.aboutcanada.bean.DataBean;
import com.example.aboutcanada.utils.GsonUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements XRecyclerView.LoadingListener {

    private XRecyclerView mXrecyclerview;
    private List<DataBean.RowsBean> mDataBeanList = new ArrayList<>();
    private XrecyclerviewAdapter mXrecyclerviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mXrecyclerview = (XRecyclerView) findViewById(R.id.xrecyclerview);
        mXrecyclerview.setLoadingMoreEnabled(false);


    }

    private void initData() {
        String json = GsonUtil.readLocalJson(this, "data.json");
        DataBean dataBean = GsonUtil.parseJsonToBean(json, DataBean.class);
        if(dataBean!=null){
            List<DataBean.RowsBean> rows = dataBean.getRows();
            mDataBeanList.addAll(rows);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mXrecyclerview.setLayoutManager(layoutManager);
            layoutManager.setOrientation(OrientationHelper. VERTICAL);
            mXrecyclerviewAdapter = new XrecyclerviewAdapter(mDataBeanList,this);
            mXrecyclerview.setAdapter(mXrecyclerviewAdapter);
        }
        mXrecyclerview.setLoadingListener(this);
    }


    @Override
    public void onRefresh() {
        mDataBeanList.clear();
        String json = GsonUtil.readLocalJson(this, "data.json");
        DataBean dataBean = GsonUtil.parseJsonToBean(json, DataBean.class);
        if(dataBean!=null) {
            List<DataBean.RowsBean> rows = dataBean.getRows();
            mDataBeanList.addAll(rows);
        }
        mXrecyclerviewAdapter.notifyDataSetChanged();
        mXrecyclerview.refreshComplete();
    }

    @Override
    public void onLoadMore() {

    }
}
