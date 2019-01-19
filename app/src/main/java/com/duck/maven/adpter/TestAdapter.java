package com.duck.maven.adpter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duck.maven.R;

import java.util.List;

public class TestAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public TestAdapter(@Nullable List<String> data) {
        super(R.layout.adpter_string, data);
    }

    @Override protected void convert(BaseViewHolder holder, String obj) {
        holder.setText(R.id.tvTitle, obj).addOnClickListener(R.id.tvTitle);
    }

}
