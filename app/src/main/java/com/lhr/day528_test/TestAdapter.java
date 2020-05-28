package com.lhr.day528_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.lhr.data.TestInfo;

import java.util.ArrayList;
import java.util.List;


public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private Context mContext;
    private List<TestInfo.DataInfo> dataInfos = new ArrayList<>();

    public TestAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addlist(List<TestInfo.DataInfo> dataInfos){
        this.dataInfos.addAll(dataInfos);
        notifyDataSetChanged();
    }
    public void finish(List<TestInfo.DataInfo> dataInfos){
        this.dataInfos.clear();
        this.dataInfos.addAll(dataInfos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item1, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        TestInfo.DataInfo dataInfo = dataInfos.get(position);
        viewHolder.item1_title.setText(dataInfo.title);
        Glide.with(mContext).load(dataInfo.thumbnail).into(viewHolder.item1_image);
        viewHolder.item1_desc.setText(dataInfo.description);

    }

    @Override
    public int getItemCount() {
        return dataInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item1_image;
        TextView item1_title;
        TextView item1_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item1_image = itemView.findViewById(R.id.item1_image);
            item1_title = itemView.findViewById(R.id.item1_title);
            item1_desc = itemView.findViewById(R.id.item1_desc);
        }
    }
}
