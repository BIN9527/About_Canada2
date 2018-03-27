package com.example.aboutcanada.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.widget.TextView;

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
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    //Initializes a control
    private void initView() {
        mXrecyclerview = (XRecyclerView) findViewById(R.id.xrecyclerview);
        mTitle = (TextView) findViewById(R.id.title_main);
        mXrecyclerview.setLoadingMoreEnabled(false);
    }

    //Gets and populates the data
    private void initData() {
        DataBean dataBean = getData(this,"data.json",DataBean.class);
        if(dataBean!=null){
            mTitle.setText(dataBean.getTitle());
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

    //Refresh data
    @Override
    public void onRefresh() {
        mDataBeanList.clear();
        DataBean dataBean = getData(this,"data.json",DataBean.class);
        if(dataBean!=null) {
            mTitle.setText(dataBean.getTitle());
            List<DataBean.RowsBean> rows = dataBean.getRows();
            mDataBeanList.addAll(rows);
        }
        mXrecyclerviewAdapter.notifyDataSetChanged();
        mXrecyclerview.refreshComplete();
    }

    @Override
    public void onLoadMore() {

    }

    //Get JSON and parse
    private DataBean getData(Context context, String fileName, Class<DataBean> dataBeanClass) {
        String json = GsonUtil.readLocalJson(context, fileName);
        return GsonUtil.parseJsonToBean(json, dataBeanClass);
    }

}
