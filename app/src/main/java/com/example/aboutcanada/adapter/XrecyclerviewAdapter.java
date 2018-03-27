package com.example.aboutcanada.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.aboutcanada.R;
import com.example.aboutcanada.bean.DataBean;

import java.util.List;


public class XrecyclerviewAdapter extends RecyclerView.Adapter<XrecyclerviewAdapter.MyViewHolder> {
    private List<DataBean.RowsBean> mList;
    private Context mContext;

    public XrecyclerviewAdapter(List<DataBean.RowsBean> dataBeanList,Context context) {
        mList = dataBeanList;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(mContext).load(mList.get(position).getImageHref()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.progressloading).error(R.mipmap.error).into(holder.mImageView);
        String title = mList.get(position).getTitle();
        String description = mList.get(position).getDescription();
        holder.mTitle.setText(title==null?"":title);
        holder.mContent.setText(description==null?"":description);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mContent;
        ImageView mImageView;
        TextView mTitle;

        public MyViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.image);
            mTitle = (TextView) view.findViewById(R.id.title);
            mContent = (TextView) view.findViewById(R.id.content);
        }

    }
}
